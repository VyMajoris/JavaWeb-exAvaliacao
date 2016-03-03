package br.com.fiap.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "rm"  )
public class Admin extends Usuario {
	
	public Admin() {
		this.setTipo(TipoUsuarioEnum.ADMIN);
	}

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	

	
}
