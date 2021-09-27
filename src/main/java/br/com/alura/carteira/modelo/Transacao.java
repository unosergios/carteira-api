package br.com.alura.carteira.modelo;


// transacao do carteira-api
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// para colocar o getter e setter para todos os atributos da class Transacao

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"data","quantidade"})
public class Transacao {

// se somente o getter e seeter do ticker pelo lombok
//	@Getter @Setter
	
// vamos utilizar as anotaçoes do bin validation	, mas como os processo ocorrem nas classes dto , vamos como na classe DTo
	
	private String ticker;
	private BigDecimal preco;
	private int quantidade;
	private LocalDate data;
	
	// com essa anotação @JsonIgnore o TipoTransacao não será gerado no JSON pelo
	// Jackson. Mas isto restringe para NUNCA levar este atributo para o JSON
	// seria um processo muito radical, Seria para algo jamias quer ser gerado
	// pelo jackson- essa anotaçãoignora sempre
	
	// o legal é vc ter flexibilidade de quais campos quero mandar
	// pra fazer isso existe uma boa pratica é criar uma outra classe "clone"
	// somente com os campos que eu quero enviar
	// um padrão de projeto para isso é o DTO - Data Transfer Object
	// só serve para transferir dados no api REST
	
	// @JsonIgnore
	private TipoTransacao tipo;

	// toda codificacao abaixo pode ser "escondido" pelo Lombok
	// o Lombok é um biblioteca que tira essa visibilidade no
	// codigo fonte. 
	// não é que deixar de existir , se não o objeto da aplicação não
	// funcionaria . Ele vai estar no bitecode. Só não vai aparecer
	// no codigo fonte
	// tanto o construtor, to string e o getter e setters
	// vamos incorporar mais dependencias no arquivo tom
	
	
	
//	public Transacao() {
//	}
//	
//	public Transacao(String ticker, BigDecimal preco, int quantidade, LocalDate data, TipoTransacao tipo) {
//		this.ticker = ticker;
//		this.preco = preco;
//		this.quantidade = quantidade;
//		this.data = data;
//		this.tipo = tipo;
//	}
//
//	
//	
//
//	
//	@Override
//	public String toString() {
//		return "Transacao [ticker=" + ticker + ", preco=" + preco + ", quantidade=" + quantidade + ", data=" +
//	            data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", tipo=" + tipo + "]";
//	}
//
//	public String getTicker() {
//		return ticker;
//	}
//
//	public void setTicker(String ticker) {
//		this.ticker = ticker;
//	}
//
//	public BigDecimal getPreco() {
//		return preco;
//	}
//
//	public void setPreco(BigDecimal preco) {
//		this.preco = preco;
//	}
//
//	public int getQuantidade() {
//		return quantidade;
//	}
//
//	public void setQuantidade(int quantidade) {
//		this.quantidade = quantidade;
//	}
//
//	public LocalDate getData() {
//		return data;
//	}
//
//	public void setData(LocalDate data) {
//		this.data = data;
//	}
//
//	public TipoTransacao getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(TipoTransacao tipo) {
//		this.tipo = tipo;
//	}

}
