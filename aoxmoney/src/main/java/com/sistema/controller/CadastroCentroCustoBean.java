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

import com.sistema.model.CentroCusto;
import com.sistema.service.CadastroCentroCusto;
import com.sistema.util.FacesMessages;


@Named
@ViewScoped
public class CadastroCentroCustoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private FacesMessages messages;
	
	@Inject
	private CadastroCentroCusto cadastroCentroCusto;
	private CentroCusto centroCustoEdicao = new CentroCusto();
	private CentroCusto centroCustoSelecionado;
	private List<CentroCusto> todosCentrosCustos;
	private List<CentroCusto> filtroCentrosCustos;


	@PostConstruct
	public void prepararNovoCadastro() {
		if(centroCustoEdicao == null){
			centroCustoEdicao = new CentroCusto();

		}
		consultar();
		
	}

	public void salvar() {
		try {
			this.cadastroCentroCusto.salvar(centroCustoEdicao);
			messages.info("Centro de Custo salvo com sucesso!");
			if(centroCustoEdicao.getCodigo() == null){
				centroCustoEdicao = cadastroCentroCusto.retornaCentroCustoPorNome(centroCustoEdicao.getDescricao());
			} else {
				centroCustoEdicao = cadastroCentroCusto.pesquisaPorId(centroCustoEdicao.getCodigo());
			}
			consultar();
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs","frmCadastro"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao salvar Centro de Custo! \n Motivo:" + mensagem.getDetail());
			e.printStackTrace();
		}

	}
	


	
	public void excluir() {
		try {
			this.cadastroCentroCusto.excluir(centroCustoEdicao);
			centroCustoEdicao = null;
			consultar();
			messages.info("Centro de Custo excluído com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao excluir Centro de Custo! \n Motivo:" + mensagem);
		}

	}

	public void consultar() {
		todosCentrosCustos = cadastroCentroCusto.consultar();
		
	}

	public CentroCusto getCentroCustoEdicao() {
		return centroCustoEdicao;
	}

	public void setCentroCustoEdicao(CentroCusto centroCustoEdicao) {
		this.centroCustoEdicao = centroCustoEdicao;
	}

	public CentroCusto getCentroCustoSelecionado() {
		return centroCustoSelecionado;
	}

	public void setCentroCustoSelecionado(CentroCusto centroCustoSelecionado) {
		this.centroCustoSelecionado = centroCustoSelecionado;
	}

	public List<CentroCusto> getTodosCentrosCustos() {
		return todosCentrosCustos;
	}

	public void setTodosCentrosCustos(List<CentroCusto> todosCentrosCustos) {
		this.todosCentrosCustos = todosCentrosCustos;
	}

	public List<CentroCusto> getFiltroCentrosCustos() {
		return filtroCentrosCustos;
	}

	public void setFiltroCentrosCustos(List<CentroCusto> filtroCentrosCustos) {
		this.filtroCentrosCustos = filtroCentrosCustos;
	}

	
	

	
	
}
