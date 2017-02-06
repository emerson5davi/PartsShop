package br.edu.ifpb.dac.projeto.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_FUNCIONARIO")
@DiscriminatorValue("Funcionario")
@NamedQueries({ 
	@NamedQuery(name = "Funcionario.findByNome", query = "SELECT f FROM Funcionario f WHERE LOWER(f.nome) LIKE :nome"),
	@NamedQuery(name = "Funcionario.findByCPF", query = "SELECT f FROM Funcionario f WHERE f.cpf = :cpf"),
	@NamedQuery(name = "Funcionario.getTotalFuncionarios", query = "SELECT COUNT(f) FROM Funcionario f")
})
public class Funcionario extends Pessoa{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "CARGO")
	private String cargo;

	@NotNull
	@Column(name = "SALARIO")
	private Double salario;
	
	@NotNull
	@Column(name = "CTPS")
	private String ctps;
	
	@NotNull
	@Column(name = "RG")
	private String rg;
	
	@NotNull
	@Column(name = "ORGEXPED")
	private String orgExped;
	
	@NotNull
	@Column(name = "TIPOCONTA")
	private String tipoConta;
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgExped() {
		return orgExped;
	}

	public void setOrgExped(String orgExped) {
		this.orgExped = orgExped;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((salario == null) ? 0 : salario.hashCode());
		result = prime * result + ((ctps == null) ? 0 : ctps.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((orgExped == null) ? 0 : orgExped.hashCode());
		result = prime * result + ((tipoConta == null) ? 0 : tipoConta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (salario == null) {
			if (other.salario != null)
				return false;
		} else if (!salario.equals(other.salario))
			return false;
		if (ctps == null) {
			if (other.ctps != null)
				return false;
		} else if (!ctps.equals(other.ctps))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (orgExped == null) {
			if (other.orgExped != null)
				return false;
		} else if (!orgExped.equals(other.orgExped))
			return false;
		if (tipoConta == null) {
			if (other.tipoConta != null)
				return false;
		} else if (!tipoConta.equals(other.tipoConta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funcionario [cargo=" + cargo + ", salario=" + salario + ", ctps=" + ctps + ", rg=" + rg + ", orgExped="
				+ orgExped + ", tipoConta=" + tipoConta + "]";
	}
	
}