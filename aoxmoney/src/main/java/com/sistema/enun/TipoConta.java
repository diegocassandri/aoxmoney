package com.sistema.enun;

public enum TipoConta {
	PESSOAL("Pessoal"), 
	CONTABANCO("Conta Bancária");


	private final String descricao;

	private TipoConta(String tipo) {
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
