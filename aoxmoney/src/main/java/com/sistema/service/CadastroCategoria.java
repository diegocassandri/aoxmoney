package com.sistema.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.sistema.model.Categoria;
import com.sistema.repository.Categorias;




public class CadastroCategoria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categorias categorias;

	@Transactional
	public void salvar(Categoria categoria) throws NegocioException{
		if (categorias.pesquisaPorNome(categoria) && (categoria.getCodigo() == null || categoria.getCodigo()==0)) {
			throw new NegocioException("Já existe um cadastro com este nome de cartão: "+categoria.getDescricao());
		} else {
			categorias.salvar(categoria);
		}	
	}
	
	@Transactional
	public void excluir(Categoria categoria) throws NegocioException{
		categoria = categorias.pesquisaPorId(categoria.getCodigo());
		categorias.excluir(categoria);
		
	}
	
	public List<Categoria> consultar(){
		return categorias.consultar();
	}
	
	public List<Categoria> todasReceitas(){
		return categorias.todasReceitas();
	}
	
	public List<Categoria> todasDespesas(){
		return categorias.todasDespesas();
	}
	

	public Categoria retornaCategoriaPorNome(String descricao) {
		return categorias.retornaCategoriaPorNome(descricao);
	}

	public Categoria pesquisaPorId(Long codigo) {
		return categorias.pesquisaPorId(codigo) ;
	}
	
	
	

}