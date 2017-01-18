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

import com.sistema.model.Periodo;
import com.sistema.service.CadastroPeriodo;
import com.sistema.util.FacesMessages;

@Named
@ViewScoped
public class CadastroPeriodoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private FacesMessages messages;
	
	@Inject
	private CadastroPeriodo cadastroPeriodo;
	
	private Periodo periodoEdicao = new Periodo();
	private Periodo periodoSelecionado;
	private List<Periodo> todosPeriodos;
	private List<Periodo> filtroPeriodos;


	@PostConstruct
	public void prepararNovoCadastro() {
		if(periodoEdicao == null){
			periodoEdicao = new Periodo();

		}
		
	}

	public void salvar() {
		try {
			this.cadastroPeriodo.salvar(periodoEdicao);
			messages.info("Período salvo com sucesso!");
			if(periodoEdicao.getCodigo() == null){
				periodoEdicao = cadastroPeriodo.pesquisaPorPeriodo(periodoEdicao.getPeriodo());
			} else {
				periodoEdicao = cadastroPeriodo.pesquisaPorId(periodoEdicao.getCodigo());
			}
			
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs","frmCadastro"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao salvar Período! \n Motivo:" + mensagem.getDetail());
			e.printStackTrace();
		}

	}
	


	
	public void excluir() {
		try {
			this.cadastroPeriodo.excluir(periodoEdicao);
			periodoEdicao = null;
			consultar();
			messages.info("Período excluído com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao excluir Cartão! \n Motivo:" + mensagem);
		}

	}

	public void consultar() {
		todosPeriodos = cadastroPeriodo.consultar();
		
	}

	public Periodo getPeriodoEdicao() {
		return periodoEdicao;
	}

	public void setPeriodoEdicao(Periodo periodoEdicao) {
		this.periodoEdicao = periodoEdicao;
	}

	public Periodo getPeriodoSelecionado() {
		return periodoSelecionado;
	}

	public void setPeriodoSelecionado(Periodo periodoSelecionado) {
		this.periodoSelecionado = periodoSelecionado;
	}

	public List<Periodo> getTodosPeriodos() {
		return todosPeriodos;
	}

	public void setTodosPeriodos(List<Periodo> todosPeriodos) {
		this.todosPeriodos = todosPeriodos;
	}

	public List<Periodo> getFiltroPeriodos() {
		return filtroPeriodos;
	}

	public void setFiltroPeriodos(List<Periodo> filtroPeriodos) {
		this.filtroPeriodos = filtroPeriodos;
	}
	
	
}
