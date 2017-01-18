package com.sistema.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.sistema.model.Periodo;

public class Periodos extends GenericDao<Periodo, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Periodos() {
		super(Periodo.class);
	}


	public boolean pesquisaPorPeriodo(Date periodo) {
		Query query = manager.createQuery("From Periodo where periodo = :periodo", Periodo.class);
		query.setParameter("periodo", periodo);
		List<?> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
	
	
	public Periodo retornaPeriodo(Date periodo) {
		Query query = manager.createQuery("From Periodo where periodo = :periodo", Periodo.class);
		query.setParameter("periodo", periodo);
		List<?> resultList = query.getResultList();
		return (Periodo) resultList.get(0);

	}
	
	
	public int quantidade(){
		   
		   List<Periodo> registros;
		   int count = 0;
		   
		   CriteriaBuilder builder = manager.getCriteriaBuilder();
	       CriteriaQuery<Periodo> query = builder.createQuery(Periodo.class);
	       query.from(Periodo.class);
	       registros = manager.createQuery(query).getResultList();
	       
	       for(@SuppressWarnings("unused") Periodo classe : registros){
	    	   count ++;
	       }
	       
	       return  count;
			
	   }

}
