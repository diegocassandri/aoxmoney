package com.sistema.repository;

import java.util.List;

import javax.persistence.Query;

import com.sistema.model.Cidade;

public class Cidades extends GenericDao<Cidade, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cidades() {
		super(Cidade.class);
	}


	public boolean pesquisaPorNome(Cidade cidade) {
		Query query = manager.createQuery("From Cidade where nome = :nome", Cidade.class);
		query.setParameter("nome", cidade.getNome());
		List<?> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

}