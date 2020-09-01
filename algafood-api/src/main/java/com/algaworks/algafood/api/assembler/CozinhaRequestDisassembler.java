package com.algaworks.algafood.api.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.request.CozinhaRequest;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaRequestDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Cozinha toDomainObject(CozinhaRequest cozinhaRequest) {
		return modelMapper.map(cozinhaRequest, Cozinha.class);
	}

	public void copyToDomainObject(@Valid CozinhaRequest cozinhaRequest, Cozinha cozinhaAtual) {
		modelMapper.map(cozinhaRequest, cozinhaAtual);
	}
}
