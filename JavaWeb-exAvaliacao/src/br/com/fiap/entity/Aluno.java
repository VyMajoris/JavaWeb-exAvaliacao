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
			name = "findAluno",
			query = "from Aluno a where a.rmAluno = :rmAluno and a.senha = :senha"
			)
})

@Entity
public class Aluno {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int rmAluno;

	private String senha;

	private String cpf;

	private String nome;

	private String email;
	private String tel;
	private String endereco;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNasc;

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getRmAluno());
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public int getRmAluno() {
		return rmAluno;
	}

	public void setRmAluno(int rmAluno) {
		this.rmAluno = rmAluno;
	}



}

