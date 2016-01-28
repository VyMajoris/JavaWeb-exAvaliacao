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
	private String idDisciplina;
	
	private String nome;

	private double nota;

	@Override
	 public String toString() {
	     return String.format("%s[id=%d]", getClass().getSimpleName(), getIdDisciplina());
	 }

	public String getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(String idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}
	

}
