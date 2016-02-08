package br.com.fiap.entity;

import java.io.Serializable;
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
public class Aluno implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long rmAluno;

	private String senha;

	private String cpf;

	private String nome;

	private String email;
	private String tel;
	private String endereco;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNasc;
	
	
	
	
	
	
	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((rmAluno == null) ? 0 : rmAluno.hashCode());
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
	        Aluno aluno = (Aluno) o;
	        if (rmAluno != null ? !rmAluno.equals(aluno.rmAluno) : aluno.rmAluno != null) {
	        	
	        	return false;
	        }
	        return true;
	    }

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


	public Long getRmAluno() {
		return rmAluno;
	}


	public void setRmAluno(Long rmAluno) {
		this.rmAluno = rmAluno;
	}


}

