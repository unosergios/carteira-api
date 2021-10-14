package br.com.alura.carteira.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.TransacaoRepository;


@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;
	private ModelMapper modelMapper = new ModelMapper();	
	
	// trocar o list para Page
//	public List<TransacaoDto> listar(Pageable paginacao) {
		// para limitar a qtde utilizamos- por default é 20 registros
		// para alterar este limite para o processo de paginação no
		// front end 
		// tem dois caminhos. Ler do link o parametro ou
		// devolve uma pagina via Page
	
	public Page<TransacaoDto> listar(Pageable paginacao) {
		
	    Page<Transacao> transacoes = transacaoRepository.findAll(paginacao);
		
		return transacoes.map(t -> modelMapper.map(t,TransacaoDto.class));
		}	

	// rodar uma transacao de bco de dados- commit - senao fica sobrepondo
	// mas o modelmapper acaba confundindo o id do usuario_id e o id da
	// transacao . 
	
	// tirar o void e retornar a TransacaoDto - para o retorno do cod 201
	@Transactional
	public TransacaoDto cadastrar(TransacaoFormDto dto ) {
		
	//*	Long idUsuario = dto.getUsuarioId();
	//*	Usuario usuario = usuarioRepository.getById(idUsuario);
		
		
		Transacao transacao = modelMapper.map(dto, Transacao.class);
// pode fazer um ajuste no modelmapper
		// para dizer que na tem id e o bco cria um novo id
		
		transacao.setId(null);
//*		transacao.setUsuario(usuario);
        transacaoRepository.save(transacao);
        return modelMapper.map(transacao,TransacaoDto.class);
       
	}		
	
}
