package com.sistema.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.sistema.service.CadastroEstado;
import com.sistema.util.FacesMessages;
import com.sistema.model.Estado;

@Named
@ViewScoped
public class CadastroEstadoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private FacesMessages messages;

	private Estado estadoEdicao = new Estado();
	
	@Inject
	private CadastroEstado cadastroEstado;
	private Estado estadoSelecionado;
	private List<Estado> todosEstados;
	private List<Estado> filtroEstados;

	@PostConstruct
	public void prepararNovoCadastro() {
		estadoEdicao = new Estado();
	}

	public void salvar() {
		try {
				this.cadastroEstado.salvar(estadoEdicao);
				consultar();
				messages.info("Estado salvo com sucesso!");
				RequestContext.getCurrentInstance().update(Arrays.asList("msgs", "frmCadastro:estado-table"));
				RequestContext.getCurrentInstance().execute("PF('edicaoEstadoDialog').hide()");
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao salvar estado! \n Motivo:" + mensagem.getDetail());
		}

	}

	public void excluir() {
		try {
			this.cadastroEstado.excluir(estadoSelecionado);
			estadoSelecionado = null;

			consultar();

			messages.info("Estado excluído com sucesso!");
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao excluir estado! \n Motivo:" + mensagem);
		}

	}

	public void consultar() {
		todosEstados = cadastroEstado.consultar();
	}

	public Estado getEstadoEdicao() {
		return estadoEdicao;
	}

	public void setEstadoEdicao(Estado estadoEdicao) {
		this.estadoEdicao = estadoEdicao;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public List<Estado> getTodosEstados() {
		return todosEstados;
	}

	public void setTodosEstados(List<Estado> todosEstados) {
		this.todosEstados = todosEstados;
	}

	public List<Estado> getFiltroEstados() {
		return filtroEstados;
	}

	public void setFiltroEstados(List<Estado> filtroEstados) {
		this.filtroEstados = filtroEstados;
	}

}
