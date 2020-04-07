package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadePrincipalNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@GetMapping
	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable("cidadeId") Long id){
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		
		if (!cidade.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(cidade.get());
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade){
		try {
			cadastroCidade.adicionar(cidade);
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{cidadeId}")
	public ResponseEntity<?> atualizar(@RequestBody Cidade cidade,@PathVariable("cidadeId") Long id){
		try {
			cadastroCidade.atualizar(cidade,id);	
			return ResponseEntity.ok(cidade);
		}catch (EntidadePrincipalNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Cidade> deletar(@PathVariable("cidadeId") Long id){
		try {
			cadastroCidade.remover(id);
			return ResponseEntity.noContent().build();
		}catch (EntidadePrincipalNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
