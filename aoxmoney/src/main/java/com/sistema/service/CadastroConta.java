package com.sistema.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.sistema.model.Conta;
import com.sistema.repository.Contas;



public class CadastroConta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Contas contas;

	@Transactional
	public void salvar(Conta Conta) throws NegocioException{
		if (contas.pesquisaPorNome(Conta) && (Conta.getCodigo() == null || Conta.getCodigo()==0)) {
			throw new NegocioException("Já existe um cadastro com este nome de conta: "+Conta.getNome());
		} else {
			contas.salvar(Conta);
		}	
	}
	
	@Transactional
	public void excluir(Conta Conta) throws NegocioException{
		Conta = contas.pesquisaPorId(Conta.getCodigo());
		contas.excluir(Conta);
		
	}
	
	public List<Conta> consultar(){
		return contas.consultar();
	}

	public Conta retornaContaPorNome(String nome) {
		return contas.retornaContaPorNome(nome);
	}

	public Conta pesquisaPorId(Long codigo) {
		return contas.pesquisaPorId(codigo);
	}
	


}