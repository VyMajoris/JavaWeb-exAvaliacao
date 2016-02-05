package br.com.fiap.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Escola {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idEscola;

	private String nome;

	private String descricao;

	private String endereco;


	private int salas;


	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="escola")
	private Collection<Curso> cursos = new ArrayList<Curso>();
	
	   @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((idEscola == null) ? 0 : idEscola.hashCode());
	        return result;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Escola other = (Escola) obj;
	        if (idEscola == null) {
	            if (other.idEscola != null)
	                return false;
	        } else if (!idEscola.equals(other.idEscola))
	            return false;
	        return true;
	    }
	    @Override
	    public String toString() {
	        return "Classe [id=" + idEscola + ", nome=" + nome + "]";
	    }
	
	
	
	
	
	
	
	
	
	

	public Collection<Curso> getCursos() {
		return cursos;
	}


	public void setCursos(Collection<Curso> cursos) {
		this.cursos = cursos;
	}


	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}




	public Long getIdEscola() {
		return idEscola;
	}

	public void setIdEscola(Long idEscola) {
		this.idEscola = idEscola;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSalas() {
		return salas;
	}

	public void setSalas(int salas) {
		this.salas = salas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}