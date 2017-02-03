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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "compras")
@NamedQueries({
	@NamedQuery(name = "compra.getTotalCompras", query = "SELECT COUNT(c) FROM Compra c"),
	@NamedQuery(name = "compra.getCountByCliente", query = "SELECT COUNT(c.id) FROM Compra c WHERE c.cliente = :cliente")
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

	@NotEmpty
	@NotNull
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "peca")
	private List<ItemPeca> itemPecas;

	@NotNull
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cliente_FK")
	private Cliente cliente;

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

	public List<ItemPeca> getItemPecas() {
		return itemPecas;
	}

	public void setItemPecas(List<ItemPeca> itemPecas) {
		this.itemPecas = itemPecas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemPecas == null) ? 0 : itemPecas.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
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
		if (itemPecas == null) {
			if (other.itemPecas != null)
				return false;
		} else if (!itemPecas.equals(other.itemPecas))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entrega [id=" + id + ", data=" + data + ", itemPecas=" + itemPecas + ", cliente=" + cliente + "]";
	}
}
