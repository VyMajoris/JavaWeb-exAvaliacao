package br.com.fiap.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.fiap.converter.Identifiable;


@Entity
public class Escola extends BaseEntity{


	private String nome;

	private String descricao;

	private String endereco;

	private int salas;


	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="escola")
	private Collection<Curso> cursos = new ArrayList<Curso>();
	
	
	    @Override
	    public String toString() {
	        return "Escola: [id=" + getId() + ", nome=" + nome + "]";
	    }
	
	
	
	
	

	public Collection<Curso> getCursos() {
		return cursos;
	}


	public void setCursos(Collection<Curso> cursos) {
		this.cursos = cursos;
	}


	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSalas() {
		return salas;
	}

	public void setSalas(int salas) {
		this.salas = salas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



}