package br.edu.ifpb.dac.projeto.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_DIVIDA")
@DiscriminatorValue("Divida")
@NamedQueries({ @NamedQuery(name = "Divida.findByCodBarra", query = "SELECT d FROM Divida d WHERE d.codBarra = :codBarra"),
				@NamedQuery(name = "Divida.findNomeEmpresas", query = "SELECT d.nomeEmpresa FROM Divida d WHERE d.nomeEmpresa = :nomeEmpresa")
})
public class Divida implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	@Column(name = "COD")
	private String codBarra;

	@NotNull
	@Column(name = "VALOR", precision = 10, scale = 2)
	private Double valor;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_PAGAMENTO")
	private Date dataPagamento;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_VENCIMENTO")
	private Date dataVencimento;

	@NotNull
	@Column(name = "NOME_EMPRESA")
	private String nomeEmpresa;

	public Divida() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codBarra == null) ? 0 : codBarra.hashCode());
		result = prime * result + ((dataPagamento == null) ? 0 : dataPagamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeEmpresa == null) ? 0 : nomeEmpresa.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
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
		Divida other = (Divida) obj;
		if (codBarra == null) {
			if (other.codBarra != null)
				return false;
		} else if (!codBarra.equals(other.codBarra))
			return false;
		if (dataPagamento == null) {
			if (other.dataPagamento != null)
				return false;
		} else if (!dataPagamento.equals(other.dataPagamento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeEmpresa == null) {
			if (other.nomeEmpresa != null)
				return false;
		} else if (!nomeEmpresa.equals(other.nomeEmpresa))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Divida [id=" + id + ", codBarra=" + codBarra + ", valor=" + valor + ", dataPagamento=" + dataPagamento
				+ ", dataVencimento=" + dataVencimento + ", nomeEmpresa=" + nomeEmpresa + "]";
	}

}