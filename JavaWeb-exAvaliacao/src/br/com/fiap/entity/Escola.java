package br.com.fiap.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(
			name = "findEscola",
			query = "from Aluno a where a.rm = :rm and a.senha = :senha"
			)
})

@Entity
public class Escola {

	@Id
	private int id;

	private String nome;

	private String descricao;

	private String endereco;

	private Double lat;

	private Double lng;

	private int salas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
