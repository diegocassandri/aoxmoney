package com.sistema.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.sistema.model.Grupo;

import com.sistema.repository.Grupos;


public class CadastroGrupo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Grupos grupos;
	
	@Transactional
	public void salvar(Grupo grupo) throws NegocioException{
		if (grupos.pesquisaPorNome(grupo) && (grupo.getCodigo() == null || grupo.getCodigo()==0)) {
			throw new NegocioException("Já existe um cadastro com este nome de cartão: "+grupo.getDescricao());
		} else {
			grupos.salvar(grupo);
		}	
	}
	
	@Transactional
	public void excluir(Grupo grupo) throws NegocioException{
		grupo = grupos.pesquisaPorId(grupo.getCodigo());
		grupos.excluir(grupo);
		
	}
	
	public List<Grupo> consultar(){
		return grupos.consultar();
	}
	



}
