package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.CozinhaRequestDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaResponseAssembler;
import com.algaworks.algafood.api.model.request.CozinhaRequest;
import com.algaworks.algafood.api.model.response.CozinhaResponse;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CozinhaResponseAssembler cozinhaResponseAssembler;
	
	@Autowired
	private CozinhaRequestDisassembler cozinhaRequestDisassembler;
	
	@GetMapping
	public List<CozinhaResponse> listar(){
		return cozinhaResponseAssembler.toCollectionModel(cozinhaRepository.findAll());
	}
	
	@GetMapping("/{cozinhaId}")
	public CozinhaResponse buscar(@PathVariable("cozinhaId") Long id){
		return cozinhaResponseAssembler.toModel(cadastroCozinhaService.buscarOuFalhar(id));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CozinhaResponse adicionar(@RequestBody @Valid CozinhaRequest cozinhaRequest) {
		return cozinhaResponseAssembler.toModel
				(cadastroCozinhaService.salvar(cozinhaRequestDisassembler.toDomainObject(cozinhaRequest)));
	}
	
	@PutMapping("/{cozinhaId}")
	public Cozinha atualizar(@PathVariable("cozinhaId") Long id,
			@RequestBody @Valid CozinhaRequest cozinhaRequest){
		Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(id);
		
		cozinhaRequestDisassembler.copyToDomainObject(cozinhaRequest, cozinhaAtual);
		
//		BeanUtils.copyProperties(cozinha,cozinhaAtual,"id");
		
		return cadastroCozinhaService.salvar(cozinhaAtual);
	}
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable("cozinhaId") Long id){
		cadastroCozinhaService.excluir(id);
	}
	
}
