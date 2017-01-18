package com.sistema.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;


import com.sistema.model.Tela;
import com.sistema.repository.Telas;

public class CadastroTela implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Telas telas;
	
	
	@Transactional
	public void salvar(Tela tela) throws NegocioException{
		if (telas.pesquisaPorNome(tela) && (tela.getCodigo() == null || tela.getCodigo()==0)) {
			throw new NegocioException("Já existe um cadastro com este nome de tela: "+tela.getDescricao());
		} else {
			telas.salvar(tela);
		}	
	}
	
	@Transactional
	public void excluir(Tela tela) throws NegocioException{
		tela = telas.pesquisaPorId(tela.getCodigo());
		telas.excluir(tela);
		
	}
	
	public List<Tela> consultar(){
		return telas.consultar();
	}

	public Tela pesquisaPorId(Long codigo) {
		return telas.pesquisaPorId(codigo) ;
	}

}
