package br.com.fiap.entity;

public enum TipoNotaEnum {	
	PROJETO_1(.30){ 
		@Override
		public String toString() {
		return "Projeto 1";}
		},
	ATIVIDADE_PRATICA(.40){ 
		@Override
		public String toString() {
		return "Atividade Prática";}
		},

	PROJETO_2(.30){ 
		@Override
		public String toString() {
		return "Projeto 2";}
		};

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

