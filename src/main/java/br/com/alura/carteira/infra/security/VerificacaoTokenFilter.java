package br.com.alura.carteira.infra.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.UsuarioRepository;

// 


public class VerificacaoTokenFilter extends OncePerRequestFilter {

	// vai ser tirado daqui e colocar na classe SecurityConfiguration
//	@Autowired
//	private UsuarioRepository usuarioRepository;
//	
//	@Autowired
//	private TokenService tokenService;
	
	
	private UsuarioRepository usuarioRepository;
	private TokenService tokenService;
	
	public VerificacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// processo a seguir
		// recupera o token
		// validar o token recebido
		// se o token estiver correto , liberar a requisicao
		
		// a aplicação front end tem que mandar o token no cabecalho com o
		// nome Authorization
		String token =request.getHeader("Authorization");
		
		// validacao do token
		// se o token vier nulo ou vazio
		if (token== null || token.isEmpty()) {
			// significa que não está logado
			filterChain.doFilter(request, response);
			return;
		}
		
		// o token veio carregado
		// tirar o Bearer do token que vem no cabecalho Authorization
		
		token = token.replace("Bearer", "");
		
		// validar o token que chegou
		
		boolean tokenValido = tokenService.isValido(token);
		
		if (tokenValido) {
			Long idUsuario = tokenService.extrairIdUsuario(token);
			
			Usuario logado = usuarioRepository.getById(idUsuario);
					
			Authentication authentication = new UsernamePasswordAuthenticationToken(logado, null, null);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	
		
		
		
		
	}
}
