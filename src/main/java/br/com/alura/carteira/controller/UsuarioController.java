package br.com.alura.carteira.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.carteira.dto.UsuarioDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

// tanto a lista que simula o bco como o processo
// do depara do ModelMapper pode ser colocado em uma classe de Service
// é uma boa pratica. Na realidade isso funciona mas a boa pratica é
// colocar em uma classe service	
	
//	private List<Usuario> usuarios = new ArrayList<>();
//	private ModelMapper modelMapper = new ModelMapper();
	
// usar a anotação @Autowired para o spring dar new nessa classe e todos as
// suas dependencias-- CONCEITO DE INJEÇÂO DE DEPENDENCIA -> @Autowired
// A classe service deve ter uma anotação @Service para fazer o link(entendimento
// para o Spring boot	
	
	@Autowired
	private UsuarioService service;
	
@GetMapping
public List<UsuarioDto> listar() {
	   return service.listar();
	   
// pode ir para a classe service	
//	return usuarios.stream()
//			.map(t -> modelMapper.map(t,UsuarioDto.class))
//			.collect(Collectors.toList());

}

@PostMapping
public void cadastrar(@RequestBody @Valid UsuarioFormDto dto ) {
    service.cadastrar(dto);
	
}	
	
	
}
