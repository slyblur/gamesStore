package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.games_store.model.usuario;
import com.generation.games_store.model.usuariologin;
import com.generation.games_store.model.usuariologin;
import com.generation.games_store.repository.usuario_repository;
import com.generation.games_store.repository.usuario_repository;
import com.generation.games_store.security.JwtService;

@Service
public class Usuarioservice {

	@Autowired
	private usuario_repository usuarioRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public Optional<usuario> cadastrarUsuario(usuario usuario) {
		if (usuario_repository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty();

		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(usuario_repository.save(usuario));

	}

	public Optional<usuario> atualizarUsuario(usuario usuario) {
		if (usuario_repository.findById(usuario.getId()).isPresent()) {
			Optional<usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());

			if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != usuario.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!!!", null);

			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.ofNullable(usuarioRepository.save(usuario));
		}

		return Optional.empty();
	}

	public Optional<usuariologin> autenticarUsuario(Optional<usuariologin> usuariologin) {

		var credenciais = new UsernamePasswordAuthenticationToken(usuariologin.get().getUsuario(),
				usuariologin.get().getSenha());
		Authentication authentication = authenticationManager.authenticate(credenciais);

		if (authentication.isAuthenticated()) {
			Optional<usuario> usuario = usuario_repository.findByUsuario(usuariologin.get().getUsuario());

			if (usuario.isPresent()) {
				usuariologin.get().setId(usuario.get().getId());
				usuariologin.get().setNome(usuario.get().getNome());
				usuariologin.get().setFoto(usuario.get().getFoto());
				usuariologin.get().setToken(gerarToken(usuariologin.get().getUsuario()));
				usuariologin.get().setSenha("");

				return usuariologin;

			}
		}
		return Optional.empty();
	}

	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);
	}

	private String gerarToken(String usuario) {
		return "Bearer " + jwtService.generateToken(usuario);
	}

}