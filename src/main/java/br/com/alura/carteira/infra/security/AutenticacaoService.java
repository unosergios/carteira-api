package br.com.alura.carteira.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.carteira.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	

	// o username que vem é o usuario da tela
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// logica para buscar o usuario na base de dados
		return repository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não cadastrado"));
	}

}
