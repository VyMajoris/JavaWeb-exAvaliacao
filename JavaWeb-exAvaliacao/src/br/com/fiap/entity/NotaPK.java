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
	@JoinColumn(name = "idDisciplina", nullable = true, columnDefinition = "bigint(20)")
	private Disciplina disciplina;

	private TipoNotaEnum tipo;
	
	@OneToOne
	@JoinColumn(name = "idAluno", nullable = true, columnDefinition = "bigint(20)")
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
	
	@Override
	public boolean equals(Object o) {
        if (this== o) return true;
        if (o ==null|| getClass() != o.getClass()) return false;

        NotaPK that = (NotaPK) o;

        if (disciplina !=null?!disciplina.equals(that.disciplina) : that.disciplina !=null) return false;
        if (aluno !=null?!aluno.equals(that.aluno) : that.aluno !=null)
            return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result;
        result = (disciplina !=null? disciplina.hashCode() : 0);
        result =31* result + (aluno !=null? aluno.hashCode() : 0);
        return result;
    }  





}