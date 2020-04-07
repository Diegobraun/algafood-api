package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadePrincipalNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@GetMapping
	public List<Estado> listar(){
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable("estadoId") Long id){
		Optional<Estado> estado = estadoRepository.findById(id);
		
		if (!estado.isPresent())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(estado.get());
	}
	
	@PostMapping
	public ResponseEntity<Estado> adicionar(@RequestBody Estado estado){
		estado = cadastroEstado.adicionar(estado);
		return ResponseEntity.status(HttpStatus.CREATED).body(estado);
	}
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable("estadoId") Long id, @RequestBody Estado estado){
		try {
			cadastroEstado.atualizar(id, estado);
			return ResponseEntity.ok(estado);
		}catch(EntidadePrincipalNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> deletar(@PathVariable("estadoId") Long id){
		try {
			cadastroEstado.deletar(id);
			return ResponseEntity.noContent().build();
		}catch(EntidadePrincipalNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}catch(EntidadeEmUsoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
