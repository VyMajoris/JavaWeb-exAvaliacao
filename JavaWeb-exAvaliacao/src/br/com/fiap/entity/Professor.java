package br.com.fiap.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Professor extends Usuario  {

	

	
	private String telefone;
	private String email;
	private String endereco;
	private String nome;
	private String cpf;
	
	public Professor() {
		this.setTipo(TipoUsuarioEnum.PROFESSOR);
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "professor")
	private Collection<Disciplina> disciplinas = new ArrayList<Disciplina>();

	@Temporal(TemporalType.DATE)
	private Date dataNasc;

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
		Professor professor = (Professor) o;
		if (getId() != null ? !getId().equals(professor.getId()) : professor.getId() != null) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	public Collection<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Collection<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
