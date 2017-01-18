package com.sistema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.sistema.enun.ReceitaDespesa;
import com.sistema.model.Categoria;
import com.sistema.service.CadastroCategoria;
import com.sistema.util.FacesMessages;




@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private FacesMessages messages;
	
	@Inject
	private CadastroCategoria cadastroCategoria;
	
	private Categoria categoriaEdicao = new Categoria();
	private Categoria categoriaSelecionado;
	private List<Categoria> todasCategorias;
	private List<Categoria> todasReceitas = new ArrayList<Categoria>();
	private List<Categoria> todasDespesas = new ArrayList<Categoria>();
	private List<Categoria> filtroCategorias;


	@PostConstruct
	public void prepararNovoCadastro() {
		if(categoriaEdicao == null){
			categoriaEdicao = new Categoria();

		}
		
	}

	public void salvar() {
		try {
			this.cadastroCategoria.salvar(categoriaEdicao);
			messages.info("Cartão salvo com sucesso!");
			if(categoriaEdicao.getCodigo() == null){
				categoriaEdicao = cadastroCategoria.retornaCategoriaPorNome(categoriaEdicao.getDescricao());
			} else {
				categoriaEdicao = cadastroCategoria.pesquisaPorId(categoriaEdicao.getCodigo());
			}
			
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs","frmCadastro"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao salvar Categoria! \n Motivo:" + mensagem.getDetail());
			e.printStackTrace();
		}

	}
	


	
	public void excluir() {
		try {
			this.cadastroCategoria.excluir(categoriaEdicao);
			categoriaEdicao = null;
			consultar();
			messages.info("Categoria excluída com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao excluir Cartão! \n Motivo:" + mensagem);
		}

	}

	public void consultar() {
		todasCategorias = cadastroCategoria.consultar();
		for(Categoria categoria : todasCategorias){
			if(categoria.getTipo() == ReceitaDespesa.RECEITA){
				todasReceitas.add(categoria);
			} else {
				todasDespesas.add(categoria);
			}
		}
		
	}
	
	
	public ReceitaDespesa[] getReceitaDespesa() {
		return ReceitaDespesa.values();
	}

	public Categoria getCategoriaEdicao() {
		return categoriaEdicao;
	}

	public void setCategoriaEdicao(Categoria categoriaEdicao) {
		this.categoriaEdicao = categoriaEdicao;
	}

	public Categoria getCategoriaSelecionado() {
		return categoriaSelecionado;
	}

	public void setCategoriaSelecionado(Categoria categoriaSelecionado) {
		this.categoriaSelecionado = categoriaSelecionado;
	}

	public List<Categoria> getTodasCategorias() {
		return todasCategorias;
	}

	public void setTodasCategorias(List<Categoria> todasCategorias) {
		this.todasCategorias = todasCategorias;
	}

	public List<Categoria> getFiltroCategorias() {
		return filtroCategorias;
	}

	public void setFiltroCategorias(List<Categoria> filtroCategorias) {
		this.filtroCategorias = filtroCategorias;
	}

	public List<Categoria> getTodasReceitas() {
		return todasReceitas;
	}

	public void setTodasReceitas(List<Categoria> todasReceitas) {
		this.todasReceitas = todasReceitas;
	}

	public List<Categoria> getTodasDespesas() {
		return todasDespesas;
	}

	public void setTodasDespesas(List<Categoria> todasDespesas) {
		this.todasDespesas = todasDespesas;
	}

	
	
}
