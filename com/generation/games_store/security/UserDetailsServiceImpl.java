package com.generation.games_store.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.games_store.model.usuario;
import com.generation.games_store.model.usuario;
import com.generation.games_store.repository.usuario_repository;
import com.generation.games_store.repository.usuario_repository;
import com.generation.games_store.security.UserDetailsImpl;

public class UserDetailsServiceImpl {
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		
		Optional<usuario> usuario = usuario_repository.findByUsuario(userName);
		
		if(usuario.isPresent())
			return new UserDetailsImpl(usuario.get());
		else
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
	}

}