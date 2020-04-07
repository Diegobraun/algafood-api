package com.algaworks.algafood.domain.exception;

public class EntidadePrincipalNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntidadePrincipalNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}
