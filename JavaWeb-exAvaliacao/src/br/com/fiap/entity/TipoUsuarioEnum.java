package br.com.fiap.entity;

public enum TipoUsuarioEnum {	ADMIN(1), PROFESSOR(2), ALUNO(3);
	
	  private int i;
	private TipoUsuarioEnum(int i) {
		this.setI(i);
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}



}

