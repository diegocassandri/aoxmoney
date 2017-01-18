package com.sistema.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.sistema.model.Cartao;
import com.sistema.repository.Cartoes;

import lombok.Getter;



public class CadastroCartao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	@Getter
	private Cartoes cartoes;

	@Transactional
	public void salvar(Cartao cartao) throws NegocioException{
		if (cartoes.pesquisaPorNome(cartao) && (cartao.getCodigo() == null || cartao.getCodigo()==0)) {
			throw new NegocioException("Já existe um cadastro com este nome de cartão: "+cartao.getDescricao());
		} else {
			cartoes.salvar(cartao);
		}	
	}
	
	@Transactional
	public void excluir(Cartao cartao) throws NegocioException{
		cartao = cartoes.pesquisaPorId(cartao.getCodigo());
		cartoes.excluir(cartao);
		
	}
	
	public List<Cartao> consultar(){
		return cartoes.consultar();
	}

	public Cartao retornaCartaoPorNome(String descricao) {
		return cartoes.retornaCartaoPorNome(descricao);
	}

	public Cartao pesquisaPorId(Long codigo) {
		return cartoes.pesquisaPorId(codigo) ;
	}
	
	
	

}