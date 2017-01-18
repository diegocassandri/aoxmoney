package com.sistema.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.sistema.model.CentroCusto;
import com.sistema.repository.CentrosCustos;



public class CadastroCentroCusto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private CentrosCustos centrosCustos;

	@Transactional
	public void salvar(CentroCusto centroCusto) throws NegocioException{
		if (centrosCustos.pesquisaPorNome(centroCusto) && (centroCusto.getCodigo() == null || centroCusto.getCodigo()==0)) {
			throw new NegocioException("Já existe um cadastro com este nome de cartão: "+centroCusto.getDescricao());
		} else {
			centrosCustos.salvar(centroCusto);
		}	
	}
	
	@Transactional
	public void excluir(CentroCusto centroCusto) throws NegocioException{
		centroCusto = centrosCustos.pesquisaPorId(centroCusto.getCodigo());
		centrosCustos.excluir(centroCusto);
		
	}
	
	public List<CentroCusto> consultar(){
		return centrosCustos.consultar();
	}

	public CentroCusto pesquisaPorId(Long codigo) {
		return centrosCustos.pesquisaPorId(codigo);
	}

	public CentroCusto retornaCentroCustoPorNome(String descricao) {
		return centrosCustos.retornaCentroCustoPorNome(descricao);
	}
	
	

}