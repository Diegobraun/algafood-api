package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado","Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso","Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	MENSAGEM_COM_PROPRIEDADE_INVALIDA("/propriedade-invalida","Propriedade inválida"),
	MENSAGEM_COM_PROPRIEDADE_INEXISTENTE("/propriedade-inexistente","Propriedade inexistente"),
	PARAMETRO_INVALIDO("/parametro-invalido","Parâmetro inválido"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
}
