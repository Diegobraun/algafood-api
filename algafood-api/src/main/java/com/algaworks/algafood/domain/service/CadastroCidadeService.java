package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadePrincipalNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade adicionar(Cidade cidade) {
		if (!validarEstadoValido(cidade))
			throw new EntidadeNaoEncontradaException(
					String.format("O estado de id %d não existe!", cidade.getEstado().getId()));
		
		return cidadeRepository.save(cidade);
	}
	
	public boolean validarEstadoValido(Cidade cidade) {
		Optional<Estado> estado = estadoRepository.findById(cidade.getEstado().getId());
		return (estado.isPresent()) ? true : false;
	}

	public Cidade atualizar(Cidade cidade, Long id) {
		Optional<Cidade> cidadeAtual = cidadeRepository.findById(id);
		
		if (!cidadeAtual.isPresent())
			throw new EntidadePrincipalNaoEncontradaException(
					String.format("A cidade de id %d não existe!", id));
		
		if (!validarEstadoValido(cidade))
			throw new EntidadeNaoEncontradaException(
					String.format("O estado de id %d não existe!", cidade.getEstado().getId()));
		
		BeanUtils.copyProperties(cidade, cidadeAtual.get(),"id");
		return cidadeRepository.save(cidadeAtual.get());
	}

	public void remover(Long id) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		
		if (!cidade.isPresent())
			throw new EntidadePrincipalNaoEncontradaException(
					String.format("A cidade de id %d não existe!", id));
		
		cidadeRepository.deleteById(id);
	}
}
