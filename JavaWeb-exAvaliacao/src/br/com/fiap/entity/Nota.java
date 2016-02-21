package br.com.fiap.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Nota {

	@EmbeddedId
	private NotaPK notapk;
	private double valor;

	public NotaPK getNotapk() {
		return notapk;
	}

	public void setNotapk(NotaPK notapk) {
		this.notapk = notapk;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
