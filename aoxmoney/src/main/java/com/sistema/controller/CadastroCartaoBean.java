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

import com.sistema.enun.TipoLancamento;
import com.sistema.model.Cartao;
import com.sistema.service.CadastroCartao;
import com.sistema.util.FacesMessages;

@Named
@ViewScoped
public class CadastroCartaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private FacesMessages messages;
	
	@Inject
	private CadastroCartao cadastroCartao;
	
	private Cartao cartaoEdicao = new Cartao();
	private Cartao cartaoSelecionado;
	private List<Cartao> todosCartoes;
	private List<Cartao> filtroCartoes;


	@PostConstruct
	public void prepararNovoCadastro() {
		if(cartaoEdicao == null){
			cartaoEdicao = new Cartao();

		}
		
	}

	public void salvar() {
		try {
			this.cadastroCartao.salvar(cartaoEdicao);
			messages.info("Cartão salvo com sucesso!");
			if(cartaoEdicao.getCodigo() == null){
				cartaoEdicao = cadastroCartao.retornaCartaoPorNome(cartaoEdicao.getDescricao());
			} else {
				cartaoEdicao = cadastroCartao.pesquisaPorId(cartaoEdicao.getCodigo());
			}
			
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs","frmCadastro"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao salvar Cartão! \n Motivo:" + mensagem.getDetail());
			e.printStackTrace();
		}

	}
	


	
	public void excluir() {
		try {
			this.cadastroCartao.excluir(cartaoEdicao);
			cartaoEdicao = null;
			consultar();
			messages.info("Cartão excluído com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao excluir Cartão! \n Motivo:" + mensagem);
		}

	}

	public void consultar() {
		todosCartoes = cadastroCartao.consultar();
		
	}
	
	public List<TipoLancamento> getTipoList() {
		return Arrays.asList(TipoLancamento.values());
	}
	
	public TipoLancamento[] getTipoLancamentos() {
		return TipoLancamento.values();
	}

	public Cartao getCartaoEdicao() {
		return cartaoEdicao;
	}

	public void setCartaoEdicao(Cartao cartaoEdicao) {
		this.cartaoEdicao = cartaoEdicao;
	}

	public Cartao getCartaoSelecionado() {
		return cartaoSelecionado;
	}

	public void setCartaoSelecionado(Cartao cartaoSelecionado) {
		this.cartaoSelecionado = cartaoSelecionado;
	}

	public List<Cartao> getTodosCartoes() {
		return todosCartoes;
	}

	public void setTodosCartoes(List<Cartao> todosCartoes) {
		this.todosCartoes = todosCartoes;
	}

	public List<Cartao> getFiltroCartoes() {
		return filtroCartoes;
	}

	public void setFiltroCartoes(List<Cartao> filtroCartoes) {
		this.filtroCartoes = filtroCartoes;
	}
}
