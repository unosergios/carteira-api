package br.com.alura.carteira.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude= {"senha"})
@Entity
@Table(name="usuarios")
// 
public class Usuario implements UserDetails {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String login;
	private String senha;
	
	@ManyToMany
	@JoinTable(name="perfis_usuarios",
	    joinColumns = @JoinColumn(name="usuario_id"),
	    inverseJoinColumns = @JoinColumn(name="perfil_id"))
	private List<Perfil> perfis = new ArrayList<>();
	
	// construtor criado para carga de dados nos testes
	public Usuario(String nome, String login, String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}


// cargas feitas pelo UserDetails	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return this.perfis;
	}


	@Override
	public String getPassword() {
		return senha;
	}


	@Override
	public String getUsername() {
		return login;
	}


	@Override
	public boolean isAccountNonExpired() {
		//  true ou false- vamos deixar como true
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		//  true ou false- vamos deixar como true
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		//  true ou false- vamos deixar como true
		return true;
	}


	@Override
	public boolean isEnabled() {
		//  true ou false- vamos deixar como true
		return true;
	}


	public void adicionarPerfil(Perfil perfil) {
		this.perfis.add(perfil);
		
	}

	
	
}
