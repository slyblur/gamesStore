package com.generation.games_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.games_store.model.categoria;


public interface categoria_repository extends JpaRepository<categoria,Long> {
	
	public List<categoria> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

	

}
