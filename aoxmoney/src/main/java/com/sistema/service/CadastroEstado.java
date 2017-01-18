package com.sistema.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.sistema.model.Estado;
import com.sistema.repository.Estados;



public class CadastroEstado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Estados estados;

	@Transactional
	public void salvar(Estado estado) throws NegocioException{
		if (estados.pesquisaPorNome(estado) && (estado.getCodigo() == null || estado.getCodigo()==0)) {
			throw new NegocioException("Já existe um cadastro com este nome de estado: "+estado.getNome());
		} else {
			estados.salvar(estado);
		}	
	}
	
	@Transactional
	public void excluir(Estado estado) throws NegocioException{
		estado = estados.pesquisaPorId(estado.getCodigo());
		estados.excluir(estado);
		
	}
	
	public List<Estado> consultar(){
		return estados.consultar();
	}
	

}