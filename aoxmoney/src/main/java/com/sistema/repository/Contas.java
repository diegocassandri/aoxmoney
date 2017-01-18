package com.sistema.repository;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.sistema.model.Conta;

public class Contas extends GenericDao<Conta, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Contas() {
		super(Conta.class);
	}


	public boolean pesquisaPorNome(Conta conta) {
		Query query = manager.createQuery("From Conta where nome = :nome", Conta.class);
		query.setParameter("nome", conta.getNome());
		List<?> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
	
	public Conta retornaContaPorNome(String conta) {
		Query query = manager.createQuery("From Conta where nome = :conta", Conta.class);
		query.setParameter("conta", conta);
		List<?> resultList = query.getResultList();
		return (Conta) resultList.get(0);

	}
	
	public int quantidade(){
		   
		   List<Conta> registros;
		   int count = 0;
		   
		   CriteriaBuilder builder = manager.getCriteriaBuilder();
	       CriteriaQuery<Conta> query = builder.createQuery(Conta.class);
	       query.from(Conta.class);
	       registros = manager.createQuery(query).getResultList();
	       
	       for(@SuppressWarnings("unused") Conta classe : registros){
	    	   count ++;
	       }
	       
	       return  count;
			
	   }

}
