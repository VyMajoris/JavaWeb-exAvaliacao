package br.com.fiap.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;





@NamedQueries({
	@NamedQuery(
			name = "findEscola",
			query = "from Aluno a where a.rm = :rm and a.senha = :senha"
			)
})

@Entity
public class Escola {

	@Id
	private int escolaId;

	private String nome;

	private String descricao;

	private String endereco;

	private Double lat;

	private Double lng;

	private int salas;

	@JoinColumn
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "escola_curso", joinColumns = { 
			@JoinColumn(name = "ESCOLA_ID", nullable = false, updatable = false) }, 
	inverseJoinColumns = { @JoinColumn(name = "CURSO_ID") })
	private List<Curso> listCurso;
	
	

	public List<Curso> getListCurso() {
		return listCurso;
	}

	public void setListCurso(List<Curso> listCurso) {
		this.listCurso = listCurso;
	}

	public int getEscolaId() {
		return escolaId;
	}

	public void setEscolaId(int escolaId) {
		this.escolaId = escolaId;
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
