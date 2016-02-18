package br.com.fiap.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.fiap.converter.BaseEntity;
import br.com.fiap.helpers.FormatadorData;



@NamedQueries({
	@NamedQuery(name = "findCursoPorProfessor", query = "SELECT DISTINCT c " +
		    "FROM Curso c, Disciplina d, Professor p " +
		    "JOIN c.disciplinas cDisciplinas " +
		    "WHERE c.idCurso = cDisciplinas.curso.idCurso AND cDisciplinas.professor.id = :rmProfessor")
})
@Entity
public class Curso implements BaseEntity, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2017152337624237340L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idCurso;
	@NotNull(message = "O curso deve ter um nome")
	private String nome;
	@NotNull(message = "O curso deve ter uma descrição")
	private String descricaoCompleta;
	@NotNull(message = "O curso deve ter uma duração")
	private Double duracao;

	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	private Date dataTermino;
	@NotNull(message = "O curso deve ter uma quantidade de vagas")
	private int vagas;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idEscola")
	private Escola escola = new Escola();

	@OneToMany( fetch=FetchType.EAGER, mappedBy="curso")
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	@ManyToMany( fetch=FetchType.EAGER, mappedBy="curso")
	private List<Aluno> alunos = new ArrayList<Aluno>();

	@Override
	public Long getId() {
		return new Long(idCurso);  
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCurso == null) ? 0 : idCurso.hashCode());
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
		Curso curso = (Curso) o;
		if (idCurso != null ? !idCurso.equals(curso.idCurso) : curso.idCurso != null) {
			return false;
		}
		return true;
	}


	
	
	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getIdCurso());
	}
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
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
	public Escola getEscola() {
		return escola;
	}
	public void setEscola(Escola escola) {
		this.escola = escola;
	}



}
