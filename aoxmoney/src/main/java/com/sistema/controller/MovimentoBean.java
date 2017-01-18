package com.sistema.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.PieChartModel;

import com.sistema.enun.AbertoFechado;
import com.sistema.enun.ReceitaDespesa;
import com.sistema.model.Categoria;
import com.sistema.model.CentroCusto;
import com.sistema.model.Conta;
import com.sistema.model.Movimento;
import com.sistema.model.Periodo;
import com.sistema.service.CadastroCategoria;
import com.sistema.service.CadastroCentroCusto;
import com.sistema.service.CadastroConta;
import com.sistema.service.CadastroMovimento;
import com.sistema.service.CadastroPeriodo;
import com.sistema.service.NegocioException;
import com.sistema.util.FacesMessages;

@Named
@ViewScoped
public class MovimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesMessages messages;
	
	@Inject
	private CadastroMovimento cadastroMovimento;
	
	@Inject
	private CadastroCategoria cadastroCategoria;
	
	@Inject
	private CadastroConta cadastroConta;
	
	@Inject
	private CadastroPeriodo cadastroPeriodo;
	
	@Inject
	private CadastroCentroCusto cadastroCentroCusto;
	
	private Movimento movimentoEdicao = new Movimento();
	private Movimento movimentoSelecionado;
	private List<Movimento> todosMovimentos;
	private List<Movimento> todosCreditos;
	private List<Movimento> todosDebitos;
	private List<Movimento> filtroMovimentos;
	private List<Categoria> todasCategorias;
	private List<Conta> todasContas;
	private List<CentroCusto> todosCentroCustos;
	private List<Periodo> todosPeriodos;
	private Periodo periodoSelecionado;
	
	private Conta contaEdicao;
	
	private PieChartModel modeloDespesas;
	private PieChartModel modeloReceitas;
	
	@PostConstruct
	public void prepararNovoCadastro(){
		if(movimentoEdicao == null){
			movimentoEdicao = new Movimento();
			movimentoEdicao.setStatus(AbertoFechado.ABERTO);
		}
		todasContas = cadastroConta.consultar();
		todosCentroCustos = cadastroCentroCusto.consultar();
		todasCategorias = cadastroCategoria.consultar();
		todosPeriodos =   cadastroPeriodo.consultar();
		consultaMovimentos();
		graficoDespesasCategoria();
		graficoReceitasCategoria();
	}

	public void consultaMovimentos(){
		todosCreditos = cadastroMovimento.todosCreditos();
		todosDebitos = cadastroMovimento.todosDebitos();
	}
	
	public void salvar() {
		try {
			movimentoEdicao.setStatus(AbertoFechado.ABERTO);
			this.cadastroMovimento.salvar(movimentoEdicao);
			messages.info("Movimento salvo com sucesso!");
			if(movimentoEdicao.getCodigo() == null){
				movimentoEdicao = cadastroMovimento.retornaMovimentoPorNome(movimentoEdicao.getDescricao());
			} else {
				movimentoEdicao = cadastroMovimento.pesquisaPorId(movimentoEdicao.getCodigo());
			}
			
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs","frmCadastro"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao salvar Movimento! \n Motivo:" + mensagem.getDetail());
			e.printStackTrace();
		}

	}
	
	public void pagarReceber() throws NegocioException{
		movimentoEdicao.setStatus(AbertoFechado.FECHADO);
		movimentoEdicao.setConfirmado(true);
		this.cadastroMovimento.salvar(movimentoEdicao);
		contaEdicao = movimentoEdicao.getConta();
		if(movimentoEdicao.getTipo() == ReceitaDespesa.DESPESA){
			contaEdicao.setSaldo(contaEdicao.getSaldo().subtract(movimentoEdicao.getValor()));
		} else{
			contaEdicao.setSaldo(contaEdicao.getSaldo().add(movimentoEdicao.getValor()));
		}
		try {
			cadastroConta.salvar(contaEdicao);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
		messages.info("Movimento confirmado com sucesso!");
		RequestContext.getCurrentInstance().update(Arrays.asList("msgs","frmCadastro"));
	}
	

	public void consultar(){
		todosMovimentos = cadastroMovimento.consultar();
	}

	public List<Date> formataDatas(List<Date> datas){
		DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
		for(Date data : datas){
			dateFormat.format(data);
		}
		return datas;
		
	}
	
	public void selecionaPeriodo(AjaxBehaviorEvent event) {
		periodoSelecionado = (Periodo) event.getSource();
	}
	
	public void excluir() {
		try {
			this.cadastroMovimento.excluir(movimentoEdicao);
			movimentoEdicao = null;
			consultar();
			messages.info("Cartão excluído com sucesso!");
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			messages.error("Erro ao excluir Cartão! \n Motivo:" + mensagem);
		}

	}
	
	public void marcarConfirmacao(){
		if(movimentoEdicao.isConfirmado()){
			movimentoEdicao.setStatus(AbertoFechado.FECHADO);
		} else {
			movimentoEdicao.setStatus(AbertoFechado.ABERTO);
		}
	}
	
	public void listaCategorias(AjaxBehaviorEvent event){
		if(movimentoEdicao.getTipo() == ReceitaDespesa.DESPESA){
			todasCategorias = cadastroCategoria.todasDespesas();
		} else{
			todasCategorias = cadastroCategoria.todasReceitas();
		}
	}
	
	public void graficoDespesasCategoria(){
		modeloDespesas = new PieChartModel();
		
		for(Movimento despesa : todosDebitos){
			modeloDespesas.set(despesa.getDescricao() + " R$ "  + despesa.getValor(),despesa.getValor());
		}
              
		modeloDespesas.setLegendPosition("s");
	}
	
	public void graficoReceitasCategoria(){
		modeloReceitas = new PieChartModel();
		
		for(Movimento despesa : todosCreditos){
			modeloReceitas.set(despesa.getDescricao() + " R$ " +  despesa.getValor(),despesa.getValor());
		}
              
		modeloReceitas.setLegendPosition("s");
	}
	
	public ReceitaDespesa[] getTipo() {
		return ReceitaDespesa.values();
	}
	
	public AbertoFechado[] getStatus() {
		return AbertoFechado.values();
	}
	

	public Movimento getMovimentoEdicao() {
		return movimentoEdicao;
	}

	public void setMovimentoEdicao(Movimento movimentoEdicao) {
		this.movimentoEdicao = movimentoEdicao;
	}

	public Movimento getMovimentoSelecionado() {
		return movimentoSelecionado;
	}

	public void setMovimentoSelecionado(Movimento movimentoSelecionado) {
		this.movimentoSelecionado = movimentoSelecionado;
	}

	public List<Movimento> getTodosMovimentos() {
		return todosMovimentos;
	}

	public void setTodosMovimentos(List<Movimento> todosMovimentos) {
		this.todosMovimentos = todosMovimentos;
	}

	public List<Movimento> getFiltroMovimentos() {
		return filtroMovimentos;
	}

	public void setFiltroMovimentos(List<Movimento> filtroMovimentos) {
		this.filtroMovimentos = filtroMovimentos;
	}

	public List<Categoria> getTodasCategorias() {
		return todasCategorias;
	}

	public void setTodasCategorias(List<Categoria> todasCategorias) {
		this.todasCategorias = todasCategorias;
	}

	public List<Conta> getTodasContas() {
		return todasContas;
	}

	public void setTodasContas(List<Conta> todasContas) {
		this.todasContas = todasContas;
	}

	public List<CentroCusto> getTodosCentroCustos() {
		return todosCentroCustos;
	}

	public void setTodosCentroCustos(List<CentroCusto> todosCentroCustos) {
		this.todosCentroCustos = todosCentroCustos;
	}

	public List<Movimento> getTodosCreditos() {
		return todosCreditos;
	}

	public void setTodosCreditos(List<Movimento> todosCreditos) {
		this.todosCreditos = todosCreditos;
	}

	public List<Movimento> getTodosDebitos() {
		return todosDebitos;
	}

	public void setTodosDebitos(List<Movimento> todosDebitos) {
		this.todosDebitos = todosDebitos;
	}

	public PieChartModel getModeloDespesas() {
		return modeloDespesas;
	}

	public void setModeloDespesas(PieChartModel modeloDespesas) {
		this.modeloDespesas = modeloDespesas;
	}

	public PieChartModel getModeloReceitas() {
		return modeloReceitas;
	}

	public void setModeloReceitas(PieChartModel modeloReceitas) {
		this.modeloReceitas = modeloReceitas;
	}

	public List<Periodo> getTodosPeriodos() {
		return todosPeriodos;
	}

	public void setTodosPeriodos(List<Periodo> todosPeriodos) {
		this.todosPeriodos = todosPeriodos;
	}

	public Periodo getPeriodoSelecionado() {
		return periodoSelecionado;
	}

	public void setPeriodoSelecionado(Periodo periodoSelecionado) {
		this.periodoSelecionado = periodoSelecionado;
	}
	
	
	
	

}
