package br.com.fiap.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({ @NamedQuery(name = "findAluno", query = "from Aluno a where a.id = :rmAluno and a.senha = :senha"),
		@NamedQuery(name = "findAlunoPorProfessor", query = "SELECT DISTINCT a "
				+ "FROM Aluno a, Curso c, Disciplina d, Professor p " + "JOIN c.disciplinas cDisciplinas "
				+ "WHERE a.curso.idCurso = c.idCurso AND c.idCurso = cDisciplinas.curso.idCurso AND cDisciplinas.professor.id = :rmProfessor"),
		@NamedQuery(name = "findAlunoPorCurso", query = "SELECT a FROM Aluno a JOIN a.curso b with b.idCurso = :idCurso"),

		@NamedQuery(name = "findAlunoPorDisciplina", query = "SELECT DISTINCT a "
				+ "FROM Aluno a, Curso c, Disciplina d " + "JOIN c.disciplinas cDisciplinas "
				+ "WHERE a.curso.idCurso = c.idCurso AND c.idCurso = cDisciplinas.curso.idCurso AND cDisciplinas.idDisciplina = :idDisciplina"), })

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Aluno extends Usuario implements Serializable {

	private static final long serialVersionUID = -7548628467768295659L;

	private String cpf;
	private String nome;
	private String email;
	private String tel;
	private String endereco;
	
	public Aluno() {
		this.setTipo(TipoUsuarioEnum.ALUNO);
	}


	@Temporal(TemporalType.DATE)
	private Date dataNasc;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCurso")
	private Curso curso;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}
		if (o == null || getClass() != o.getClass()) {

			return false;
		}
		Aluno aluno = (Aluno) o;
		if (getId() != null ? !getId().equals(aluno.getId()) : aluno.getId() != null) {

			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
