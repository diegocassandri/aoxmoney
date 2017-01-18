package com.sistema.repository;


import java.util.List;

import javax.persistence.Query;

import com.sistema.model.Grupo;
import com.sistema.model.Usuario;


public class Grupos extends GenericDao<Grupo, Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Grupos() {
		super(Grupo.class);
	}
	

	public boolean pesquisaPorNome(Grupo grupo) {
		Query query = manager.createQuery("From Grupo where descricao = :descricao", Grupo.class);
		query.setParameter("descricao", grupo.getDescricao());
		List<?> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
	
	public Grupo retornaGrupoPorNome(String grupo) {
		Query query = manager.createQuery("From Grupo where descricao = :grupo", Grupo.class);
		query.setParameter("grupo", grupo);
		List<?> resultList = query.getResultList();
		return (Grupo) resultList.get(0);

	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> usariosNaoAssociados(Grupo grupo) {

		Query query = manager.createQuery("Select u From Usuario u Where not exists (Select g from u.grupos g Where g = :grupo)", Usuario.class);
		query.setParameter("grupo", grupo);
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Usuario> usariosAssociados(Grupo grupo) {
		
		Query query = manager.createQuery("Select u From Usuario u Where  exists (Select g from u.grupos g Where g = :grupo)", Usuario.class);
		query.setParameter("grupo", grupo);
		return query.getResultList();
	}
}
