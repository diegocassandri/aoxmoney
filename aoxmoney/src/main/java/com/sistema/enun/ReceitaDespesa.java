package com.sistema.enun;

public enum ReceitaDespesa {
	RECEITA("Receita"), 
	DESPESA("Despesa");


	private final String descricao;

	private ReceitaDespesa(String tipo) {
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
