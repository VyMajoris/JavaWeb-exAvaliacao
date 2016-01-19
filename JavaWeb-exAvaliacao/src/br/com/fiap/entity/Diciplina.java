package br.com.fiap.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Diciplina {
	@Id
	private String codigo;
	
	private String nome;
	
	private double nota;
	

	
	

}
