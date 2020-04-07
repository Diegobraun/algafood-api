package com.algaworks.algafood.di.modelo;

public class Cliente {

	private String nome;
	private String email;
	private String telefone;
	private boolean ativo = false;
	
	public Cliente(String nome, String email, String telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
	
	public void ativar() {
		this.ativo = true;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getAtivo() {
		return this.ativo;
	}
	
	public String getNome() {
		return this.nome;
	}

	public String getEmail() {
		return this.email;
	}

}
