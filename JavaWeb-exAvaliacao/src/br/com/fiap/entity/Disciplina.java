package br.com.fiap.entity;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.fiap.dao.GenericDao;

@Entity
public class Disciplina {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private String codigo;
	
	private String nome;
	
	private double nota;
	
}
