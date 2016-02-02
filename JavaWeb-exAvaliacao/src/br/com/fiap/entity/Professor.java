package br.com.fiap.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@NamedQueries({
	@NamedQuery(
			name = "findProfessor",
			query = "from Professor p where p.rmProfessor = :rmProfessor and p.senha = :senha"
			)
})


@Entity
public class Professor {


	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int rmProfessor;

	private String senha;

	private String telefone;
	
	private String email;
	
	private String endereco;

	private String nome;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNasc;
	

	@Override
	 public String toString() {
	     return String.format("%s[id=%d]", getClass().getSimpleName(), getRmProfessor());
	 }

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public int getRmProfessor() {
		return rmProfessor;
	}

	public void setRmProfessor(int rmProfessor) {
		this.rmProfessor = rmProfessor;
	}


}
