package br.com.fiap.entity;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.Id;

import br.com.fiap.dao.GenericDao;

@Entity
public class Disciplina {
	@Id
	private String codigo;
	
	private String nome;
	
	private double nota;
	
}
