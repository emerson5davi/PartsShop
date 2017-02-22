package br.edu.ifpb.dac.projeto.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
	@Column(name = "VALOR_TOTAL", precision = 10, scale = 2)
	private BigDecimal valorTotal;
	
	@Column(name = "VALOR_PAGO", precision = 10, scale = 2)
	private BigDecimal valorPago;
	
	@Column(name = "PAYMENT", nullable = false)
	@Enumerated(EnumType.STRING)
	private Payment payment;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_PAGAMENTO")
	private Date dataPagamento;
	
	@Column(name = "NUM_PARCELAS")
	private Integer numDeParcelas;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemPagamento> itensPagamento = new ArrayList<>();
	
	public Pagamento(){
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
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
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
		result = prime * result + ((dataPagamento == null) ? 0 : dataPagamento.hashCode());
		result = prime * result + ((numDeParcelas == null) ? 0 : numDeParcelas.hashCode());
		result = prime * result + ((itensPagamento == null) ? 0 : itensPagamento.hashCode());
		result = prime * result + ((valorPago == null) ? 0 : valorPago.hashCode());
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
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		if (payment == null) {
			if (other.payment != null)
				return false;
		} else if (!payment.equals(other.payment))
			return false;
		if (dataPagamento == null) {
			if (other.dataPagamento != null)
				return false;
		} else if (!dataPagamento.equals(other.dataPagamento))
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
		if (valorPago == null) {
			if (other.valorPago != null)
				return false;
		} else if (!valorPago.equals(other.valorPago))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", valorTotal=" + valorTotal + ", valorPago=" + valorPago + ", payment="
				+ payment + ", dataPagamento=" + dataPagamento + ", numDeParcelas=" + numDeParcelas
				+ ", itensPagamento=" + itensPagamento + "]";
	}

}