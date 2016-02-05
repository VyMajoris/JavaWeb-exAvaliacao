package br.com.fiap.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.fiap.helpers.FormatadorData;




@Entity
public class Curso {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idCurso;
	private String nome;
	private String descricaoCompleta;
	private Double duracao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTermino;
	private int vagas;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idEscola")
	private Escola escola = new Escola();

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="cursos")
	private Collection<Disciplina> disciplinas = new ArrayList<Disciplina>();

	
	
	
	
	
	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getIdCurso());
	}
	public Long getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(Long idCurso) {
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
		this.dataInicio = (FormatadorData.formatarDate(dataInicio.toString(), "yyyy-MM-dd", "dd/MM/yyyy"));
	}
	public Date getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino(Date dataTermino) {
		this.dataTermino =(FormatadorData.formatarDate(dataTermino.toString(), "yyyy-MM-dd", "dd/MM/yyyy"));
	}
	public int getVagas() {
		return vagas;
	}
	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
	public Escola getEscola() {
		return escola;
	}
	public void setEscola(Escola escola) {
		this.escola = escola;
	}



}
