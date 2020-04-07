package com.algaworks.algafood.domain.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadePrincipalNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Estado adicionar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public Estado atualizar(Long id, Estado estado) {
		Optional<Estado> estadoAtual = estadoRepository.findById(id);
		if (estadoAtual == null)
			throw new EntidadePrincipalNaoEncontradaException(
					String.format("O estado de id %d não foi encontrado!", id));
		
		BeanUtils.copyProperties(estado, estadoAtual.get(),"id");
		return estadoRepository.save(estadoAtual.get());
	}
	
	public void deletar(Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		Stream<Cidade> cidades = cidadeRepository.findAll().stream().
										filter(cidade -> cidade.getEstado() == estado.get());
		
		if (!estado.isPresent())
			throw new EntidadePrincipalNaoEncontradaException(
					String.format("O estado de id %d não foi encontrado", id));
		
		if (cidades.count() > 0)
			throw new EntidadeEmUsoException(
					String.format("O estado de id %d não pode ser deletado,"
							+ " pois possui vinculos com cidade", id));
		
		estadoRepository.deleteById(estado.get().getId());
	}
}
