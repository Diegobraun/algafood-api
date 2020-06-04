package com.algaworks.algafood;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

import  io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	private int quantidadeRestaurantesNoRepositorio;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";
		
		databaseCleaner.clearTables();
		prepararDados();
		quantidadeRestaurantesNoRepositorio = restauranteRepository.findAll().size();
	}
	
	@Test
	public void deveRetornarStatus200AoInserirNovoRestaurante() {
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(getSingleRestaurante("restaurante-padrao.json"))
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatus400AoInserirRestauranteComCozinhaInexistente() {
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(getSingleRestaurante("restaurante-padrao-sem-cozinha.json"))
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void deveRetornarStatus200QuandoConsultarRestaurantes() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarONumeroCorretoDeRestaurantesNoRepositorio() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(quantidadeRestaurantesNoRepositorio));
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretosQuandoConsultarRestauranteExistente() {
		given()
			.accept(ContentType.JSON)
			.pathParam("restauranteId", 1)
		.when()
			.get("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", Matchers.equalTo("restaurante auxiliar"));
	}
	
	@Test
	public void deveRetornarStatus404QuandoConsultarRestauranteInexistente() {
		given()
			.accept(ContentType.JSON)
			.pathParam("restauranteId", 20)
		.when()
			.get("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private String getSingleRestaurante(String path) {
		return ResourceUtils.getContentFromResource("/json/"+path);
	}
	
	private void prepararDados() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("tailandesa");
		
		cozinhaRepository.save(cozinha);
		
		Restaurante restaurante = new Restaurante();
		restaurante.setId(1L);
		restaurante.setNome("restaurante auxiliar");
		restaurante.setCozinha(cozinha);
		restaurante.setTaxaFrete(new BigDecimal(12));
		
		restauranteRepository.save(restaurante);
	}
}
