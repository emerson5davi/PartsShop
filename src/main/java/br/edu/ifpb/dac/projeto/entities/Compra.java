package br.edu.ifpb.dac.projeto.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "compras")
@NamedQueries({
	@NamedQuery(name = "compra.getTotalCompras", query = "SELECT COUNT(c) FROM Compra c"),
	@NamedQuery(name = "compra.getCountByCliente", query = "SELECT COUNT(c.id) FROM Compra c WHERE c.cliente = :cliente"),
	@NamedQuery(name = "compra.getComprasByCliente", query = "SELECT c FROM Compra c WHERE c.cliente = :cliente")
})
@NamedEntityGraphs({ 
	@NamedEntityGraph(name = "graph.Compra.comItensDePagamento",
					  attributeNodes = { @NamedAttributeNode(value = "pagamento", subgraph = "pagamento")
				  					   },
				  	  subgraphs = { @NamedSubgraph(name = "pagamento", 
				  	  							   attributeNodes = { @NamedAttributeNode(value = "itensPagamento")
				  	  							   }) 
				  	  }) 
	})
public class Compra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Date data;

	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemCompra> itensCompra;

	@NotNull
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cliente_FK")
	private Cliente cliente;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cod_pagamento")
	private Pagamento pagamento;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<ItemCompra> getItensCompra() {
		return itensCompra;
	}

	public void setItensCompra(List<ItemCompra> itensCompra) {
		this.itensCompra = itensCompra;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itensCompra == null) ? 0 : itensCompra.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((pagamento == null) ? 0 : pagamento.hashCode());
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
		Compra other = (Compra) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itensCompra == null) {
			if (other.itensCompra != null)
				return false;
		} else if (!itensCompra.equals(other.itensCompra))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (pagamento == null) {
			if (other.pagamento != null)
				return false;
		} else if (!pagamento.equals(other.pagamento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", data=" + data + ", itensCompra=" + itensCompra + ", cliente=" + cliente
				+ ", pagamento=" + pagamento + "]";
	}

}