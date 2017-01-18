package com.sistema.repository;

import java.util.List;

import javax.persistence.Query;

import com.sistema.model.Tela;



public class Telas extends GenericDao<Tela, Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean pesquisaPorNome(Tela tela) {
		Query query = manager.createQuery("From Tela where descricao = :tela", Tela.class);
		query.setParameter("tela", tela.getDescricao());
		List<?> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<Tela> raizes() {

		return (List<Tela>) manager.createQuery("from Tela where telaPai is null", Tela.class).getResultList();
		 
	}

}
