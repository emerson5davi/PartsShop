package br.edu.ifpb.dac.projeto.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.edu.ifpb.dac.projeto.enumerations.Payment;

@Entity
@Table(name = "TB_PAGAMENTO")
public class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	@Column(name = "VALOR_TOTAL")
	private BigDecimal valor;
	
	@Column(name = "PAYMENT", nullable = false)
	@Enumerated(EnumType.STRING)
	private Payment payment;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_PAGAMENTO")
	private Date data_pagamento;
	
	@Column(name = "NUM_PARCELAS")
	private Integer numDeParcelas;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemPagamento> itensPagamento;
	
	public Pagamento(){
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Date getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}
	
	public Integer getNumDeParcelas() {
		return numDeParcelas;
	}

	public void setNumDeParcelas(Integer numDeParcelas) {
		this.numDeParcelas = numDeParcelas;
	}

	public List<ItemPagamento> getItensPagamento() {
		return itensPagamento;
	}

	public void setItensPagamento(List<ItemPagamento> itensPagamento) {
		this.itensPagamento = itensPagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
		result = prime * result + ((data_pagamento == null) ? 0 : data_pagamento.hashCode());
		result = prime * result + ((numDeParcelas == null) ? 0 : numDeParcelas.hashCode());
		result = prime * result + ((itensPagamento == null) ? 0 : itensPagamento.hashCode());
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		if (payment == null) {
			if (other.payment != null)
				return false;
		} else if (!payment.equals(other.payment))
			return false;
		if (data_pagamento == null) {
			if (other.data_pagamento != null)
				return false;
		} else if (!data_pagamento.equals(other.data_pagamento))
			return false;
		if (numDeParcelas == null) {
			if (other.numDeParcelas != null)
				return false;
		} else if (!numDeParcelas.equals(other.numDeParcelas))
			return false;
		if (itensPagamento == null) {
			if (other.itensPagamento != null)
				return false;
		} else if (!itensPagamento.equals(other.itensPagamento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", valor=" + valor + ", payment=" + payment + ", data_pagamento="
				+ data_pagamento + ", numDeParcelas=" + numDeParcelas + ", itensPagamento=" + itensPagamento + "]";
	}

}