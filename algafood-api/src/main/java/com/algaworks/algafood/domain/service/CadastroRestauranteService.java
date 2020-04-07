package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadePrincipalNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}
	
	public Restaurante atualizar(Restaurante restaurante, Long id) {
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);
		Optional<Cozinha> cozinha = cozinhaRepository.findById(restaurante.getCozinha().getId());
		
		if (!restauranteAtual.isPresent())
			throw new EntidadePrincipalNaoEncontradaException(
					String.format("Não existe cadastro de restaurante com código %d", id));
		
		if (!cozinha.isPresent())
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com código %d", 
							restaurante.getCozinha().getId()));
		
		BeanUtils.copyProperties(restaurante, restauranteAtual.get(),"id");
		return restauranteRepository.save(restauranteAtual.get());
	}
}
