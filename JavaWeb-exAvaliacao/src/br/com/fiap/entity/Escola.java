package br.com.fiap.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Escola {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int idEscola;

	private String nome;

	private String descricao;

	private String endereco;

	private Double lat;

	private Double lng;

	private int salas;


	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="escola")
	private Collection<Curso> cursos = new ArrayList<Curso>();
	
	@Override
	 public String toString() {
	     return String.format("%s[id=%d]", getClass().getSimpleName(), getIdEscola());
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


	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public int getIdEscola() {
		return idEscola;
	}

	public void setIdEscola(int idEscola) {
		this.idEscola = idEscola;
	}

	public void setLng(Double lng) {
		this.lng = lng;
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
