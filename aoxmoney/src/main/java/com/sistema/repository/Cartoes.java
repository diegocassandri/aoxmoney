package com.sistema.repository;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.sistema.model.Cartao;

public class Cartoes extends GenericDao<Cartao, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cartoes() {
		super(Cartao.class);
	}


	public boolean pesquisaPorNome(Cartao cartao) {
		Query query = manager.createQuery("From Cartao where descricao = :descricao", Cartao.class);
		query.setParameter("descricao", cartao.getDescricao());
		List<?> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
	
	public Cartao retornaCartaoPorNome(String cartao) {
		Query query = manager.createQuery("From Cartao where descricao = :descricao", Cartao.class);
		query.setParameter("descricao", cartao);
		List<?> resultList = query.getResultList();
		return (Cartao) resultList.get(0);

	}
	
	public int quantidade(){
		   
		   List<Cartao> registros;
		   int count = 0;
		   
		   CriteriaBuilder builder = manager.getCriteriaBuilder();
	       CriteriaQuery<Cartao> query = builder.createQuery(Cartao.class);
	       query.from(Cartao.class);
	       registros = manager.createQuery(query).getResultList();
	       
	       for(@SuppressWarnings("unused") Cartao classe : registros){
	    	   count ++;
	       }
	       
	       return  count;
			
	   }

}
