package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	private static final String MSG_ESTADO_NAO_ENCONTRADO = "O estado de id %d não existe!";

	private static final String MSG_CIDADE_NAO_ENCONTRADA = "A cidade de id %d não existe!";
	
	private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida pois está em uso";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		if (!validarEstadoValido(cidade))
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_ESTADO_NAO_ENCONTRADO, cidade.getEstado().getId()));
		
		return cidadeRepository.save(cidade);
	}
	
	public boolean validarEstadoValido(Cidade cidade) {
		return estadoRepository.findById(cidade.getEstado().getId()).isPresent();
	}

	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_CIDADE_NAO_ENCONTRADA, id));
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, id));
		}
	}
	
	public Cidade buscarOuFalhar(Long id) {
		return cidadeRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_CIDADE_NAO_ENCONTRADA,id)));
	}
	
}
