package com.generation.games_store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.games_store.model.usuario;

public interface usuario_repository extends JpaRepository<usuario, Long> {

	public Optional<usuario> findByUsuario(String usuario);

}
