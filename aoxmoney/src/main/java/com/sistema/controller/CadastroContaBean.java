package com.sistema.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.sistema.enun.TipoConta;
import com.sistema.model.Conta;
import com.sistema.service.CadastroConta;
import com.sistema.util.FacesMessages;

@Named
@ViewScoped
public class CadastroContaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private FacesMessages messages;
	@Inject
	private CadastroConta cadastroConta;
	private Conta contaEdicao = new Conta();
	private Conta contaSelecionado;
	private List<Conta> todasContas;
	private List<Conta> filtroContas;


	@PostConstruct
	public void prepararNovoCadastro() {
		if(contaEdicao == null){
			contaEdicao = new Conta();
			contaEdicao.setSaldo(BigDecimal.ZERO);

		}
		
	}

	public void salvar() {
		try {
			this.cadastroConta.salvar(contaEdicao);
			messages.info("Conta salva com sucesso!");
			if(contaEdicao.getCodigo() == null){
				contaEdicao = cadastroConta.retornaContaPorNome(contaEdicao.getNome());
			} else {
				contaEdicao = cadastroConta.pesquisaPorId(contaEdicao.getCodigo());
			}
			
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs","frmCadastro"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao salvar conta! \n Motivo:" + mensagem.getDetail());
			e.printStackTrace();
		}

	}
	


	
	public void excluir() {
		try {
			this.cadastroConta.excluir(contaEdicao);
			contaEdicao = null;
			consultar();
			messages.info("Conta excluída com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("" + e.getStackTrace());
			messages.error("Erro ao excluir conta! \n Motivo:" + mensagem);
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs"));
		}

	}

	public void consultar() {
		todasContas = cadastroConta.consultar();
		
	}

	public TipoConta[] getTipoContas() {
		return TipoConta.values();
	}
	
	
	public Conta getContaEdicao() {
		return contaEdicao;
	}

	public void setContaEdicao(Conta contaEdicao) {
		this.contaEdicao = contaEdicao;
	}

	public Conta getContaSelecionado() {
		return contaSelecionado;
	}

	public void setContaSelecionado(Conta contaSelecionado) {
		this.contaSelecionado = contaSelecionado;
	}

	public List<Conta> getTodasContas() {
		return todasContas;
	}

	public void setTodasContas(List<Conta> todasContas) {
		this.todasContas = todasContas;
	}

	public List<Conta> getFiltroContas() {
		return filtroContas;
	}

	public void setFiltroContas(List<Conta> filtroContas) {
		this.filtroContas = filtroContas;
	}

	

	
	
}
