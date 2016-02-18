package br.com.fiap.entity;

import java.io.Serializable;

public class Admin extends Usuario implements Serializable {
	
	private static final long serialVersionUID = 5034770706839623249L;

	public Admin() {
		this.setTipo(TipoUsuarioEnum.ADMIN);
	}

}
