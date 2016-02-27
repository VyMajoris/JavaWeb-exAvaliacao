package br.com.fiap.entity;

import java.io.Serializable;

public class Admin extends Usuario {
	
	

	public Admin() {
		this.setTipo(TipoUsuarioEnum.ADMIN);
	}

}
