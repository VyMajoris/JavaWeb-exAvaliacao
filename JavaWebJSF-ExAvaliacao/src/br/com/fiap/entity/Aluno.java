package br.com.fiap.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
public class Aluno {

	@Id
	private Integer codigo;
	
	private String cpf;
	
	private String nome;
	
	private Date dataNasc;
	
	private Curso curso;
	
	private Diciplina diciplinas;
	
	/*
	 * 	TINYBLOB => 255 Bytes
		BLOB => 64 Kilobytes
		MEDIUMBLOB => 16 Megabytes
		LONGBLOB => 4 Gigabits 
	 */
	@Lob
	private byte[] foto;
	
	
}

