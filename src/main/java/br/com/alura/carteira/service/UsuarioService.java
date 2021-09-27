package br.com.alura.carteira.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.alura.carteira.dto.UsuarioDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.modelo.Usuario;

// Na classe service é para isolar as regras da aplicação por exemplo gerar
// a senha

@Service
public class UsuarioService {

	private List<Usuario> usuarios = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();	
	
	public List<UsuarioDto> listar() {
		return usuarios.stream()
				.map(t -> modelMapper.map(t,UsuarioDto.class))
					.collect(Collectors.toList());
		}	

	public void cadastrar(UsuarioFormDto dto ) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);
	//  a senha poderia ser gerado aqui e não digitado
		
		String senha = new Random().nextInt(999999)+ " ";  // soma com um strin para transforma em string
		usuario.setSenha(senha);     		               // coloca uma ramge 999999 para gerar uma

        System.out.println(usuario.getSenha());
		usuarios.add(usuario);                             // senha com 6 numeros
	}		
	
}
