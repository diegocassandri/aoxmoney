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

import com.sistema.model.Cidade;
import com.sistema.model.Estado;
import com.sistema.service.CadastroCidade;
import com.sistema.service.CadastroEstado;
import com.sistema.util.FacesMessages;


@Named
@ViewScoped
public class CadastroCidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private FacesMessages messages;

	private Cidade cidadeEdicao = new Cidade();
	@Inject
	private CadastroCidade cadastroCidade;
	@Inject
	private CadastroEstado cadastroEstado;
	private Cidade cidadeSelecionado;
	private List<Cidade> todasCidades;
	private List<Estado> todosEstados;
	private List<Cidade> filtroCidades;
	
	@PostConstruct
	public void prepararNovoCadastro() {
	   cidadeEdicao = new Cidade();
	   todosEstados = cadastroEstado.consultar();
	}

	public void salvar() {
		try {
				this.cadastroCidade.salvar(cidadeEdicao);
				consultar();
				todosEstados = cadastroEstado.consultar();
				messages.info("Cidade salva com sucesso!");
				RequestContext.getCurrentInstance().update(Arrays.asList("msgs", "frmCadastro:cidade-table"));
				RequestContext.getCurrentInstance().execute("PF('edicaoCidadeDialog').hide()");

		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao salvar cidade! \n Motivo:" + mensagem.getDetail());
			RequestContext.getCurrentInstance().update(Arrays.asList("frmCadastro:cidade-table"));

		}

	}


	public void excluir() {
		try {
			this.cadastroCidade.excluir(cidadeSelecionado);
			cidadeSelecionado = null;

			consultar();
			todosEstados = cadastroEstado.consultar();
			messages.info("Cidade excluída com sucesso!");
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("" + e.getStackTrace());
			messages.error("Erro ao excluir Cidade! \n Motivo:" + mensagem);
		}

	}

	public void consultar() {
		todasCidades = cadastroCidade.consultar();
	}

	



	public Cidade getCidadeEdicao() {
		return cidadeEdicao;
	}

	public void setCidadeEdicao(Cidade cidadeEdicao) {
		this.cidadeEdicao = cidadeEdicao;
	}

	public Cidade getCidadeSelecionado() {
		return cidadeSelecionado;
	}

	public void setCidadeSelecionado(Cidade cidadeSelecionado) {
		this.cidadeSelecionado = cidadeSelecionado;
	}

	public List<Cidade> getTodasCidades() {
		return todasCidades;
	}

	public void setTodasCidades(List<Cidade> todasCidades) {
		this.todasCidades = todasCidades;
	}

	public List<Cidade> getFiltroCidades() {
		return filtroCidades;
	}

	public void setFiltroCidades(List<Cidade> filtroCidades) {
		this.filtroCidades = filtroCidades;
	}

	public List<Estado> getTodosEstados() {
		return todosEstados;
	}

	public void setTodosEstados(List<Estado> todosEstados) {
		this.todosEstados = todosEstados;
	}
}
