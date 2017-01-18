package com.sistema.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sistema.repository.Cartoes;
import com.sistema.repository.CentrosCustos;
import com.sistema.repository.Contas;
import com.sistema.repository.Usuarios;

@Named
@ViewScoped
public class DashboardController  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Contas contas;
		
	@Inject
	private Cartoes cartoes;
	
	
	@Inject
	private CentrosCustos centrosCustos;
	
	@Inject
	private Usuarios usuarios;
	
	
	private Long quantidadeContas;
	private Long quantidadeCartoes;
	private Long quantidadeCentroDeCustos;
	private Long quantidadeUsuarios;
	
	 @PostConstruct
	 public void initialize() {
		 quantidadeContas = contas.count();
		 quantidadeCartoes = cartoes.count();
		 quantidadeCentroDeCustos = centrosCustos.count();
		 quantidadeUsuarios = usuarios.count();
	 }

	public Long getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(Long quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	public Long getQuantidadeCartoes() {
		return quantidadeCartoes;
	}

	public void setQuantidadeCartoes(Long quantidadeCartoes) {
		this.quantidadeCartoes = quantidadeCartoes;
	}

	public Long getQuantidadeCentroDeCustos() {
		return quantidadeCentroDeCustos;
	}

	public void setQuantidadeCentroDeCustos(Long quantidadeCentroDeCustos) {
		this.quantidadeCentroDeCustos = quantidadeCentroDeCustos;
	}

	public Long getQuantidadeUsuarios() {
		return quantidadeUsuarios;
	}

	public void setQuantidadeUsuarios(Long quantidadeUsuarios) {
		this.quantidadeUsuarios = quantidadeUsuarios;
	}

	 
	 
	
	 
	 
}
