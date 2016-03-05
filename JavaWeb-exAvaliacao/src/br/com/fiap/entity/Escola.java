package br.com.fiap.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
public class Escola extends BaseEntity{
	
	private String nome;
	private String descricao;
	private String endereco;
	private String latLng;
	
	private int salas;


	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER, mappedBy="escola")
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

	public String getLatLng() {
		return latLng;
	}

	public void setLatLng(String latLng) {
		this.latLng = latLng;
	}



}