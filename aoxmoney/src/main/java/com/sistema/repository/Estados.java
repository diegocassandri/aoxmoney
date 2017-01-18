package com.sistema.repository;

import java.util.List;

import javax.persistence.Query;

import com.sistema.model.Estado;

public class Estados extends GenericDao<Estado, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Estados() {
		super(Estado.class);
	}


	public boolean pesquisaPorNome(Estado estado) {
		Query query = manager.createQuery("From Estado where nome = :nome", Estado.class);
		query.setParameter("nome", estado.getNome());
		List<?> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

}
