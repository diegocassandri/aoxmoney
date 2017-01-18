package com.sistema.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;


import com.sistema.model.Periodo;
import com.sistema.repository.Periodos;

import lombok.Getter;



public class CadastroPeriodo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	@Getter
	private Periodos periodos;

	@Transactional
	public void salvar(Periodo periodo) throws NegocioException{
		if (periodos.pesquisaPorPeriodo(periodo.getPeriodo()) && (periodo.getCodigo() == null || periodo.getCodigo()==0)) {
			throw new NegocioException("Já existe um período neste mês: "+periodo.getPeriodo());
		} else {
			periodos.salvar(periodo);
		}	
	}
	
	@Transactional
	public void excluir(Periodo periodo) throws NegocioException{
		periodo = periodos.pesquisaPorId(periodo.getCodigo());
		periodos.excluir(periodo);
		
	}
	
	public List<Periodo> consultar(){
		return periodos.consultar();
	}

	public Periodo pesquisaPorPeriodo(Date data) {
		return periodos.retornaPeriodo(data);
	}


	public Periodo pesquisaPorId(Long codigo) {
		return periodos.pesquisaPorId(codigo) ;
	}
	
	
	

}