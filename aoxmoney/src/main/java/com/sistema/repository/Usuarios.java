package com.sistema.repository;


import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.sistema.model.Grupo;
import com.sistema.model.Usuario;


public class Usuarios extends GenericDao<Usuario, Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Usuarios() {
		super(Usuario.class);
	}
	

	public boolean pesquisaPorNome(Usuario usuario) {
		Query query = manager.createQuery("From Usuario where usuario = :usuario", Usuario.class);
		query.setParameter("usuario", usuario.getUsuario());
		List<?> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
	
	public Usuario retornaUsuarioPorNome(String usuario) {

		Query query = manager.createQuery("From Usuario where usuario = :usuario", Usuario.class);
		query.setParameter("usuario", usuario);
		List<?> resultList = query.getResultList();
		return (Usuario) resultList.get(0);

	}
	
	@SuppressWarnings("unchecked")
	public List<Grupo> gruposNaoAssociados(Usuario usuario) {

		Query query = manager.createQuery(
				"Select g From Grupo g Where not exists (Select u from g.usuarios u Where u = :usuario)", Grupo.class);
		query.setParameter("usuario", usuario);
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Grupo> gruposAssociados(Usuario usuario) {
		Query query = manager.createQuery(
				"Select g From Grupo g Where  exists (Select u from g.usuarios u Where u = :usuario)", Grupo.class);
		query.setParameter("usuario", usuario);
		return query.getResultList();
	}
	
	public Usuario login(String username, String password) {
        try {
            return (Usuario) this.manager.createNamedQuery("Usuario.findByUserPassword").setParameter("usuario", username).setParameter("senha", password).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
	
	public int quantidade(){
		   
		   List<Usuario> registros;
		   int count = 0;
		   
		   CriteriaBuilder builder = manager.getCriteriaBuilder();
	       CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
	       query.from(Usuario.class);
	       registros = manager.createQuery(query).getResultList();
	       
	       for(@SuppressWarnings("unused") Usuario classe : registros){
	    	   count ++;
	       }
	       
	       return  count;
			
	   }

}
