package com.algaworks.algafood.api.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIdRequest {
	
	@NotNull
	private Long id;
}
