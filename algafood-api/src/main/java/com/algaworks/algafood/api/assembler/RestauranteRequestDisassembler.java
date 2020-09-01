package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.request.RestauranteRequest;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteRequestDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteRequest restauranteRequest) {
		return modelMapper.map(restauranteRequest, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteRequest restauranteRequest, Restaurante restaurante) {
		// Para evitar exception causada por alteração de id de cozinha
		restaurante.setCozinha(new Cozinha());
		
		modelMapper.map(restauranteRequest, restaurante);
	}
}
