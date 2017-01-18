package com.sistema.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.sistema.enun.AbertoFechado;
import com.sistema.enun.ReceitaDespesa;



@Entity
@Table(name = "movimento")
public class Movimento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@NotEmpty
	@Column(nullable = false, length = 250)
	private String descricao;
	
	@NotNull
	private ReceitaDespesa tipo;
	
	@NotNull
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name="data")
	private Date data;

	@ManyToOne(optional = false)
	@JoinColumn(name = "IdCategoria")
	private Categoria categoria;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "IdConta")
	private Conta conta;
	
	@Column(nullable = true, length = 250)
	private String observacao;
	
	@Enumerated(EnumType.STRING)
	private AbertoFechado status;
	
	@NotNull
	@Type(type="true_false")
	private boolean confirmado;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ReceitaDespesa getTipo() {
		return tipo;
	}

	public void setTipo(ReceitaDespesa tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	

	public AbertoFechado getStatus() {
		return status;
	}

	public void setStatus(AbertoFechado status) {
		this.status = status;
	}
	
	

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimento other = (Movimento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
