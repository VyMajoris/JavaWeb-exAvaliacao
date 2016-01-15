package br.com.fiap.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Curso {

	@Id
	private int codigo;
	
	private String nome;
	
	private String descricao;
	
	private Aluno alunos;
	
	private Professor professores;
	
	private Diciplina diciplinas;
}
