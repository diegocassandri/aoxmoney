package com.sistema.enun;

public enum TipoLancamento {
	CREDITO("Crédito"), 
	DEBITO("Débito");


	private final String descricao;

	private TipoLancamento(String tipo) {
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
