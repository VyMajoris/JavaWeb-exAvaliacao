package br.com.fiap.entity;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;



@NamedQueries({

	@NamedQuery(name = "findDisciplinaPorAluno", query = "SELECT DISTINCT d "
			+ "FROM Aluno a, Curso c, Disciplina d " + "JOIN c.disciplinas cDisciplinas "
			+ "WHERE a.curso.id = c.id AND c.id = cDisciplinas.curso.id AND a.rm = :rmAluno") } )
@Entity
public class Disciplina extends BaseEntity {

	private String nome;
	private String descricao;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "idProfessor", nullable = true, columnDefinition = "bigint(20)")
	private Professor professor;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "idCurso", nullable = true, columnDefinition = "bigint(20)")
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

		if (this == o){

			return true;
		} 
		if (o == null || getClass() != o.getClass()){

			return false;
		}
		Disciplina disciplina = (Disciplina) o;
		if (getId() != null ? !getId().equals(disciplina.getId()) : disciplina.getId() != null) {

			return false;
		}
		return true;
	}



	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}



	public Professor getProfessor() {
		return professor;
	}



	public void setProfessor(Professor professor) {
		this.professor = professor;
	}


	public Curso getCurso() {
		return curso;
	}


	public void setCurso(Curso curso) {
		this.curso = curso;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}






}
