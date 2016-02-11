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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.fiap.converter.BaseEntity;
import br.com.fiap.helpers.FormatadorData;


@NamedQueries({
	@NamedQuery(
			name = "findProfessor",
			query = "from Professor p where p.rmProfessor = :rmProfessor and p.senha = :senha"
			)
})


@Entity
public class Professor implements BaseEntity, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	private Long rmProfessor;
	private String senha;
	private String telefone;
	private String email;
	private String endereco;
	private String nome;
	private String cpf;
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="professor")
	private Collection<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	@Temporal(TemporalType.DATE)
	private Date dataNasc;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rmProfessor == null) ? 0 : rmProfessor.hashCode());
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
		Professor professor = (Professor) o;
		if (rmProfessor != null ? !rmProfessor.equals(professor.rmProfessor) : professor.rmProfessor != null) {
			return false;
		}
		return true;
	}




	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getRmProfessor());
	}

	public Collection<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Collection<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Long getRmProfessor() {
		return rmProfessor;
	}

	public void setRmProfessor(Long rmProfessor) {
		this.rmProfessor = rmProfessor;
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


	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return new Long(rmProfessor);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
