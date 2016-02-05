package br.com.fiap.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Disciplina {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idDisciplina;

	private String nome;
	private String descricao;



	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idProfessor")
	private Professor professor = new Professor();

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Collection<Curso> cursos = new ArrayList<Curso>();

	



	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getIdDisciplina());
	}



	public Professor getProfessor() {
		return professor;
	}



	public void setProfessor(Professor professor) {
		this.professor = professor;
	}



	public Collection<Curso> getCursos() {
		return cursos;
	}



	public void setCursos(Collection<Curso> cursos) {
		this.cursos = cursos;
	}



	public Long getIdDisciplina() {
		return idDisciplina;
	}



	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}




}
