package br.com.alura.carteira.service;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.carteira.dto.UsuarioDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.UsuarioRepository;

// Na classe service é para isolar as regras da aplicação por exemplo gerar
// a senha

@Service
public class UsuarioService {

//	private List<Usuario> usuarios = new ArrayList<>();
	@Autowired
	private UsuarioRepository usuarioRepository;
	private ModelMapper modelMapper = new ModelMapper();	
	
	public Page<UsuarioDto> listar(Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
//		return usuarios.stream()
//             .map(t -> modelMapper.map(t,UsuarioDto.class))
//					.collect(Collectors.toList());
		return usuarios.map(t -> modelMapper.map(t,UsuarioDto.class));		
		
		}	

	public void cadastrar(UsuarioFormDto dto ) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);
	//  a senha poderia ser gerado aqui e não digitado
		
		String senha = new Random().nextInt(999999)+ " ";  // soma com um strin para transforma em string
		usuario.setSenha(senha);     		               // coloca uma ramge 999999 para gerar uma

        System.out.println(usuario.getSenha());
//		usuarios.add(usuario); 
		// senha com 6 numeros

        usuarioRepository.save(usuario);
	}		
	
}
