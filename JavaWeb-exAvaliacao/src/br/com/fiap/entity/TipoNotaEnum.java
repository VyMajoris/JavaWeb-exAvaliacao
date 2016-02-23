package br.com.fiap.entity;

public enum TipoNotaEnum {	PROJETO_1(30), ATIVIDADE_PRATICA(30),  PROJETO_2(40);
	
	
	
	
	  private int fator;
	  
	private TipoNotaEnum(int fator) {
		this.setFator(fator);
	}
	public int getFator() {
		return fator;
	}
	public void setFator(int fator) {
		this.fator = fator;
	}



}

