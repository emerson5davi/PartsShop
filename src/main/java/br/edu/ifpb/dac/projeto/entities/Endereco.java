package br.edu.ifpb.dac.projeto.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.edu.ifpb.dac.projeto.enumerations.Estados;

@Entity
@Table(name = "TB_ENDERECO")

@NamedQueries({ @NamedQuery(name = "Endereco.findByLogradouro", query = "SELECT e FROM Endereco e WHERE LOWER(e.logradouro) LIKE (:logradouro)") })
public class Endereco implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	@Column(name = "numero")
	private String numero;

	@NotNull
	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private Estados estado;

	@Column(name = "cidade")
	@NotNull
	private String cidade;

	@NotNull
	@Column(name = "zona")
	private String zona;

	@NotBlank
	@NotNull
	@Column(name = "bairro")
	private String bairro;
	
	@NotBlank
	@Column(name = "logradouro")
	private String logradouro;

	@NotBlank
	@Column(name = "cep")
	private String cep;

	@Column(name = "complemento")
	private String complemento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result
				+ ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((zona == null) ? 0 : zona.hashCode());
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
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
		Endereco outro = (Endereco) obj;
		if (logradouro == null) {
			if (outro.logradouro != null)
				return false;
		} else if (!logradouro.equals(outro.logradouro))
			return false;
		if (cep == null) {
			if (outro.cep != null)
				return false;
		} else if (!cep.equals(outro.cep))
			return false;
		if (cidade == null) {
			if (outro.cidade != null)
				return false;
		} else if (!cidade.equals(outro.cidade))
			return false;
		if (complemento == null) {
			if (outro.complemento != null)
				return false;
		} else if (!complemento.equals(outro.complemento))
			return false;
		if (id == null) {
			if (outro.id != null)
				return false;
		} else if (!id.equals(outro.id))
			return false;
		if (numero == null) {
			if (outro.numero != null)
				return false;
		} else if (!numero.equals(outro.numero))
			return false;
		if (estado != outro.estado)
			return false;
		if (zona == null) {
			if (outro.zona != null)
				return false;
		} else if (!zona.equals(outro.zona))
			return false;
		if (bairro == null) {
			if (outro.bairro != null)
				return false;
		} else if (!bairro.equals(outro.bairro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", numero=" + numero + ", estado=" + estado
				+ ", cidade=" + cidade + ", zona=" + zona + ", bairro=" + bairro + ", logradouro=" + logradouro
				+ ", cep=" + cep + ", complemento=" + complemento + "]";
	}

}