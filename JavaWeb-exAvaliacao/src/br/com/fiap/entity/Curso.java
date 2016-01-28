package br.com.fiap.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




@Entity
public class Curso {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int idCurso;
	private String nome;
	private String descricaoCompleta;
	private Double duracao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTermino;
	private int vagas;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "listCurso")
	private List<Escola> listEscola = new ArrayList<Escola>();



	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getIdCurso());
	}
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricaoCompleta() {
		return descricaoCompleta;
	}
	public void setDescricaoCompleta(String descricaoCompleta) {
		this.descricaoCompleta = descricaoCompleta;
	}
	public Double getDuracao() {
		return duracao;
	}
	public void setDuracao(Double duracao) {
		this.duracao = duracao;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}
	public int getVagas() {
		return vagas;
	}
	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
	public List<Escola> getListEscola() {
		return listEscola;
	}
	public void setListEscola(List<Escola> listEscola) {
		this.listEscola = listEscola;
	}



}
