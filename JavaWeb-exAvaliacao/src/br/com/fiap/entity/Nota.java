package br.com.fiap.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
@NamedQueries({ 


	@NamedQuery(name = "findNotaPorAlunoEDisciplina", query = "SELECT n "
			+ "FROM Nota n "
			+ "WHERE n.notapk.aluno.id = :idAluno AND n.notapk.disciplina.id = :idDisciplina AND n.notapk.tipo = :tipo"),

	@NamedQuery(name = "findNotaPorDisciplinaETipo", query = "SELECT n "
			+ "FROM Nota n "
			+ "WHERE n.notapk.disciplina.id = :idDisciplina and n.notapk.tipo = :tipo"),

	@NamedQuery(name = "deleteNotaPorAlunoEDisciplina", query = "DELETE FROM Nota n WHERE n.notapk.aluno.id = :idAluno AND n.notapk.disciplina.id = :idDisciplina")








}

		)
@Entity
public class Nota {

	@EmbeddedId
	private NotaPK notapk;
	private Double valor;

	public NotaPK getNotapk() {
		return notapk;
	}

	public void setNotapk(NotaPK notapk) {
		this.notapk = notapk;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}









}