package com.sistema.enun;

public enum AbertoFechado {
	ABERTO("Aberto"), 
	FECHADO("Fechado");


	private final String descricao;

	private AbertoFechado(String tipo) {
	    	descricao = tipo;
	    }

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : descricao.equals(otherName);
	}

	public String toString() {
		return this.descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
