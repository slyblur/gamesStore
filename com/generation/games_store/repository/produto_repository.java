package com.generation.games_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.games_store.model.produto;



public interface produto_repository extends JpaRepository< produto, Long> {

	public List<produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
