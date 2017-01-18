package com.sistema.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.sistema.model.Usuario;
import com.sistema.repository.Usuarios;


public class CadastroUsuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;

	@Transactional
	public void salvar(Usuario usuario) throws NegocioException{
		if (usuarios.pesquisaPorNome(usuario) && (usuario.getCodigo() == null || usuario.getCodigo()==0)) {
			throw new NegocioException("Já existe um cadastro com este nome de cartão: "+usuario.getUsuario());
		} else {
			usuarios.salvar(usuario);
		}	
	}
	
	@Transactional
	public void excluir(Usuario usuario) throws NegocioException{
		usuario= usuarios.pesquisaPorId(usuario.getCodigo());
		usuarios.excluir(usuario);
		
	}
	
	public List<Usuario> consultar(){
		return usuarios.consultar();
	}
	


}
