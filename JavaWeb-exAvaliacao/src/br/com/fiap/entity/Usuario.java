package br.com.fiap.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.com.fiap.converter.BaseEntity;

@NamedQueries({
	@NamedQuery(name = "findUsuario", query = "from Usuario u where u.id = :id and u.senha = :senha") })

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Usuario implements Login, BaseEntity{
	
	@Id
	private Long id;
	private String senha;
	private TipoUsuarioEnum tipo;
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public boolean isLogado() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean logaUsuario() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deslogaUsuario() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoUsuarioEnum getTipo() {
		return tipo;
	}
	public void setTipo(TipoUsuarioEnum tipo) {
		this.tipo = tipo;
	}

	
}
