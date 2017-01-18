package com.sistema.repository;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.sistema.model.CentroCusto;

public class CentrosCustos extends GenericDao<CentroCusto, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CentrosCustos() {
		super(CentroCusto.class);
	}


	public boolean pesquisaPorNome(CentroCusto centroCusto) {
		Query query = manager.createQuery("From CentroCusto where descricao = :descricao", CentroCusto.class);
		query.setParameter("descricao", centroCusto.getDescricao());
		List<?> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
	
	public CentroCusto retornaCentroCustoPorNome(String cartao) {
		Query query = manager.createQuery("From CentroCusto where descricao = :descricao", CentroCusto.class);
		query.setParameter("descricao", cartao);
		List<?> resultList = query.getResultList();
		return (CentroCusto) resultList.get(0);

	}
	
	public int quantidade(){
		   
		   List<CentroCusto> registros;
		   int count = 0;
		   
		   CriteriaBuilder builder = manager.getCriteriaBuilder();
	       CriteriaQuery<CentroCusto> query = builder.createQuery(CentroCusto.class);
	       query.from(CentroCusto.class);
	       registros = manager.createQuery(query).getResultList();
	       
	       for(@SuppressWarnings("unused") CentroCusto classe : registros){
	    	   count ++;
	       }
	       
	       return  count;
			
	   }

}
