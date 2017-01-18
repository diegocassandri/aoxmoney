package com.sistema.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Valid;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.sistema.service.NegocioException;


public abstract class GenericDao <T, I extends Serializable> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	protected EntityManager manager;

	protected Class<T> persistedClass;

	protected GenericDao() {
		   
	}
	

	protected GenericDao(Class<T> persistedClass) {
	     this();
	     this.persistedClass = persistedClass;
	 }


    protected Criteria createCriteria() {
        return this.getSession().createCriteria(this.persistedClass);
    }


    protected Session getSession() {
        return (Session) manager.getDelegate();
    }
	
	 public void  salvar(@Valid T entity) throws NegocioException {
	     try{
	    	 manager.merge(entity);
	    	 manager.flush();
	     }catch(Exception e){
	    	 throw new NegocioException("Erro ao salvar" + e.getMessage()); 
	     }
	     
	   }

	   
	   public void excluir(@Valid T entity) throws NegocioException {
		     try{
		    	 manager.remove(entity);
		     }catch(Exception e){
		    	 throw new NegocioException("Erro ao Excluir" + e.getMessage());
		     }
	   }

	   public List<T> consultar() {
	       CriteriaBuilder builder = manager.getCriteriaBuilder();
	       CriteriaQuery<T> query = builder.createQuery(persistedClass);
	       query.from(persistedClass);
	       return manager.createQuery(query).getResultList();
	   }
	   
	   public Long count() { 
	        final CriteriaBuilder builder = manager.getCriteriaBuilder();
	        final CriteriaQuery<Long> query = builder.createQuery(Long.class);
	        query.select(builder.count(query.from(this.persistedClass)));
	        return manager.createQuery(query).getSingleResult();
	    }
	   	   

	   public T pesquisaPorId(I id) {
	       return manager.find(persistedClass, id);
	   }
}
