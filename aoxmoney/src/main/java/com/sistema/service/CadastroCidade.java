package com.sistema.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.sistema.model.Cidade;
import com.sistema.repository.Cidades;

public class CadastroCidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cidades cidades;


	@Transactional
	public void salvar(Cidade Cidade) throws NegocioException{
		if (cidades.pesquisaPorNome(Cidade) && (Cidade.getCodigo() == null || Cidade.getCodigo()==0)) {
			throw new NegocioException("Já existe um cadastro com este nome de cartão: "+Cidade.getNome());
		} else {
			cidades.salvar(Cidade);
		}	
	}
	
	@Transactional
	public void excluir(Cidade Cidade) throws NegocioException{
		Cidade = cidades.pesquisaPorId(Cidade.getCodigo());
		cidades.excluir(Cidade);
		
	}
	
	public List<Cidade> consultar(){
		return cidades.consultar();
	}
	


}