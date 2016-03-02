package br.com.fiap.entity;

public enum TipoNotaEnum {	PROJETO_1(.30), ATIVIDADE_PRATICA(.30),  PROJETO_2(.40);
	
	
	
	
	  private Double fator;
	  
	private TipoNotaEnum(Double fator) {
		this.setFator(fator);
	}
	public Double getFator() {
		return fator;
	}
	public void setFator(Double fator) {
		this.fator = fator;
	}



}

