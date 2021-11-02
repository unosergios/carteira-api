package br.com.alura.carteira.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.carteira.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

 
	@Value("${jjwt.secret}")
	private String secret;
	
// vamos utilizar o jjwt para gerar o token
	
	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();

// dentro de senha esta a senha do spring		
		return Jwts.builder()
				.setSubject(logado.getId().toString())
//				.signWith(SignatureAlgorithm.HS256, "BpxMSNY8MuLjhAu3wyYp5VWwDA73U8vNUJh88DePmDdNZJ6AsZzZxWXJ4TBX26Pc9cvXfmAtYvj3udp9CtX2bMc3n7JDWS88SdeevvXCs9GTWRbGaTwaKGedEDAZbCTXXfzsYcsRqfETJWDVJaK3ZT6fJMVnACeeXTgFzJND98Ac4tmPzDtKUdrhHpphKmNvwcaqCGnFMyrp9bKnUvDhKNYhARSu4qNUYfngS6ARCJk7PBBLBWH5daLWHg7vgynt")
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	
	// metodo para verificar se o token é valido
	
	public boolean isValido(String token) {
		// o Jwts não tem um metodo para voltar o boolean, mas volta exception
		// então usamos o try catch para voltar o boolean no isValido construido
 
	  	try {
			Jwts
			 .parser()
			 .setSigningKey(secret)
			 .parseClaimsJws(token);
			 return true;
	} catch (Exception e) {
		   return false;
	}
	
}


	public Long extrairIdUsuario(String token) {
		Claims claims=	Jwts
			 .parser()
			 .setSigningKey(secret)
			 .parseClaimsJws(token)
			 .getBody();
		return Long.parseLong(claims.getSubject()) ;
	}
}
