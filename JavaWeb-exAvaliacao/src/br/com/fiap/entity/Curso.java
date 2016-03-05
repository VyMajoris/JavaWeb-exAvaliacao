									package br.com.fiap.entity;

import java.io.Serializable;
import java.util.ArrayList;

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




@NamedQueries({
	@NamedQuery(name = "findCursoPorProfessor", query = "SELECT DISTINCT c " +
		    "FROM Curso c, Disciplina d, Professor p " +
		    "JOIN c.disciplinas cDisciplinas " +
		    "WHERE c.id = cDisciplinas.curso.id AND cDisciplinas.professor.rm = :rmProfessor")
})
@Entity
public class Curso extends BaseEntity {

	/**
	 * 
	 */

	private String nome;

	private String descricaoCompleta;

	private Double duracao;

	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	private Date dataTermino;

	private int vagas;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "idEscola", updatable=true,  nullable=true, columnDefinition = "bigint(20)")
	private Escola escola = new Escola();

	@OneToMany( fetch=FetchType.EAGER, mappedBy="curso")
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	@ManyToMany( fetch=FetchType.EAGER, mappedBy="curso")
	private List<Aluno> alunos = new ArrayList<Aluno>();

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
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
