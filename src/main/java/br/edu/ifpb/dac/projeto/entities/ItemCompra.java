package br.edu.ifpb.dac.projeto.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "itens_compra")
@NamedQueries({
	@NamedQuery(name = "itemCompra.getTotalPorPeca", query = "SELECT i.peca.nome, COUNT(i.peca) FROM ItemCompra i GROUP BY i.peca.nome, i.peca"),
	@NamedQuery(name = "itemCompra.findByPeca", query = "SELECT i FROM ItemCompra i WHERE i.peca = :peca"),
	@NamedQuery(name = "itemCompra.getQuantidadeByPeca", query = "SELECT COUNT(i.id) FROM ItemCompra i WHERE i.peca = :peca")})
public class ItemCompra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;

	@Column(name = "preco", nullable = false, precision = 10, scale = 2)
	private BigDecimal preco;

	@NotNull
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "peca_FK")
	private Peca peca;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((peca == null) ? 0 : peca.hashCode());
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
		ItemCompra other = (ItemCompra) obj;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (peca == null) {
			if (other.peca != null)
				return false;
		} else if (!peca.equals(other.peca))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemCompra [id=");
		builder.append(id);
		builder.append(", quantidade=");
		builder.append(quantidade);
		builder.append(", peca=");
		builder.append(peca);
		builder.append(", preco=");
		builder.append(preco);
		builder.append("]");
		return builder.toString();
	}

}
