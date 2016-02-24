package br.com.fiap.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

import br.com.fiap.converter.BaseEntity;


@Entity
public class Disciplina implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idDisciplina;
	private String nome;
	private String descricao;

	@ManyToOne(fetch=FetchType.EAGER)
	private Professor professor;

	@ManyToOne(fetch=FetchType.EAGER)
	private Curso curso;
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idDisciplina == null) ? 0 : idDisciplina.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object o) {
    
        if (this == o){
        
        	return true;
        } 
        if (o == null || getClass() != o.getClass()){
        	
        	 return false;
        }
        Disciplina disciplina = (Disciplina) o;
        if (idDisciplina != null ? !idDisciplina.equals(disciplina.idDisciplina) : disciplina.idDisciplina != null) {
        	
        	return false;
        }
        return true;
    }
	



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


	public Curso getCurso() {
		return curso;
	}


	public void setCurso(Curso curso) {
		this.curso = curso;
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


	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return new Long(idDisciplina);
	}




}
