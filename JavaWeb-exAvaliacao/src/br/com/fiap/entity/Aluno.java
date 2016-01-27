package br.com.fiap.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
	name = "findAluno",
	query = "from Aluno a where a.rm = :rm and a.senha = :senha"
	)
})

@Entity
public class Aluno {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int rm;
	
	private String senha;
	
	private String cpf;
	
	private String nome;
	
	private String email;
	private String tel;
	private String endereco;
	
	private Date dataNasc;

	public int getRm() {
		return rm;
	}

	public void setRm(int rm) {
		this.rm = rm;
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
	

	
	/*
	 * 	TINYBLOB => 255 Bytes
		BLOB => 64 Kilobytes
		MEDIUMBLOB => 16 Megabytes
		LONGBLOB => 4 Gigabits 
	 */

	
	
}

