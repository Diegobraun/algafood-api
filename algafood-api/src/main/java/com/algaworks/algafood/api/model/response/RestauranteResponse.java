package com.algaworks.algafood.api.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResponse {
	private Long id;
	private String nome;
	private BigDecimal precoFrete;
	private CozinhaResponse cozinha;
	
	
	private String nomeCozinha;
	private Long idCozinha;
}
