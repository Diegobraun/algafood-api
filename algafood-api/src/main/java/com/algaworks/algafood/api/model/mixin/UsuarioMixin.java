package com.algaworks.algafood.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.algaworks.algafood.domain.model.Grupo;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsuarioMixin {
	@JsonIgnore
	private List<Grupo> grupos = new ArrayList<>();
}
