package br.com.fiap.entity;

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




@Entity
public class Curso {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int cursoId;
	private String nome;
	private String descricaoCompleta;
	private Double duracao;
	private Date dataInicio;
	private Date dataTermino;
	private int vagas;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "listCurso")
	private List<Escola> listEscola;
	

	public int getCursoId() {
		return cursoId;
	}
	public void setCursoId(int cursoId) {
		this.cursoId = cursoId;
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
