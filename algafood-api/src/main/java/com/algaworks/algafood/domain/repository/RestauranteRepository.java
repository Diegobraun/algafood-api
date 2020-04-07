package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;


@Repository
public interface RestauranteRepository 
		extends JpaRepository<Restaurante, Long>,RestauranteRepositoryQueries{
	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
	
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);
	
	Optional<Restaurante> findFirstByNomeContaining(String nome);
	
	List<Restaurante> findTop2ByNomeContaining(String nome);
	
	Integer countByCozinhaId(Long cozinha);
	
}
