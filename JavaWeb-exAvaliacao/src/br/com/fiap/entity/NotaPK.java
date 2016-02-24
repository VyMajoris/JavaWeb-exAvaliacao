package br.com.fiap.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class NotaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "idDisciplina")
	private Disciplina disciplina;

	private TipoNotaEnum tipo;
	
	@OneToOne
	@JoinColumn(name = "idAluno")
	private Aluno aluno;

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}


	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public TipoNotaEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoNotaEnum tipo) {
		this.tipo = tipo;
	}





}
