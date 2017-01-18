package com.sistema.repository;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


import com.sistema.model.Movimento;


public class Movimentos extends GenericDao<Movimento, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Movimentos() {
		super(Movimento.class);
	}


	public boolean pesquisaPorNome(Movimento movimento) {
		Query query = manager.createQuery("From Movimento where descricao = :descricao", Movimento.class);
		query.setParameter("descricao", movimento.getDescricao());
		List<?> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
	
	public Movimento retornaMovimentoPorNome(String movimento) {
		Query query = manager.createQuery("From Movimento where descricao = :descricao", Movimento.class);
		query.setParameter("descricao", movimento);
		List<?> resultList = query.getResultList();
		return (Movimento) resultList.get(0);

	}
	
	@SuppressWarnings("unchecked")
	public List<Movimento> todosCreditos() {

		Query query = manager.createQuery("From Movimento where tipo = 0 and confirmado = 'F' ", Movimento.class);
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Movimento> todosDebitos() {

		Query query = manager.createQuery("From Movimento where tipo = 1 and confirmado = 'F' ", Movimento.class);
		return query.getResultList();

	}
	
	public int quantidade(){
		   
		   List<Movimento> registros;
		   int count = 0;
		   
		   CriteriaBuilder builder = manager.getCriteriaBuilder();
	       CriteriaQuery<Movimento> query = builder.createQuery(Movimento.class);
	       query.from(Movimento.class);
	       registros = manager.createQuery(query).getResultList();
	       
	       for(@SuppressWarnings("unused") Movimento classe : registros){
	    	   count ++;
	       }
	       
	       return  count;
			
	   }

}
