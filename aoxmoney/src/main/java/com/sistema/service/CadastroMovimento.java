package com.sistema.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.sistema.model.Movimento;
import com.sistema.repository.Movimentos;

import lombok.Getter;



public class CadastroMovimento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	@Getter
	private Movimentos movimentos;

	@Transactional
	public void salvar(Movimento movimento) throws NegocioException{
	
			movimentos.salvar(movimento);
		
	}
	
	@Transactional
	public void excluir(Movimento movimento) throws NegocioException{
		movimento = movimentos.pesquisaPorId(movimento.getCodigo());
		movimentos.excluir(movimento);
		
	}
	
	public List<Movimento> consultar(){
		return movimentos.consultar();
	}
	
	public List<Movimento> todosCreditos(){
		return movimentos.todosCreditos();
	}

	public List<Movimento> todosDebitos(){
		return movimentos.todosDebitos();
	}

	
	public Movimento retornaMovimentoPorNome(String descricao) {
		return movimentos.retornaMovimentoPorNome(descricao);
	}

	public Movimento pesquisaPorId(Long codigo) {
		return movimentos.pesquisaPorId(codigo) ;
	}
	
	
	

}