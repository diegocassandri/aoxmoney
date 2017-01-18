package com.sistema.repository;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.sistema.model.Categoria;
public class Categorias extends GenericDao<Categoria, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Categorias() {
		super(Categoria.class);
	}


	public boolean pesquisaPorNome(Categoria categoria) {
		Query query = manager.createQuery("From Categoria where descricao = :categoria", Categoria.class);
		query.setParameter("categoria", categoria.getDescricao());
		List<?> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
	
	public Categoria retornaCategoriaPorNome(String categoria) {
		Query query = manager.createQuery("From Categoria where descricao = :descricao", Categoria.class);
		query.setParameter("descricao", categoria);
		List<?> resultList = query.getResultList();
		return (Categoria) resultList.get(0);

	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> todasReceitas() {

		Query query = manager.createQuery("From Categoria where tipo = 0 ", Categoria.class);
		return query.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> todasDespesas() {

		Query query = manager.createQuery("From Categoria where tipo = 1 ", Categoria.class);
		return query.getResultList();

	}
	
	public int quantidade(){
		   
		   List<Categoria> registros;
		   int count = 0;
		   
		   CriteriaBuilder builder = manager.getCriteriaBuilder();
	       CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);
	       query.from(Categoria.class);
	       registros = manager.createQuery(query).getResultList();
	       
	       for(@SuppressWarnings("unused") Categoria classe : registros){
	    	   count ++;
	       }
	       
	       return  count;
			
	   }

}
