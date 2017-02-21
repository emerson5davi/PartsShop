package br.edu.ifpb.dac.projeto.enumerations;

public enum StatusCliente{
	
	INADIMPLENTE("Inadimplente"),
	ADIMPLENTE("Adimplente"),
	
	LB_ADIMPLENTE("label label-success"),
	LB_INADIMPLENTE("label label-danger");
	
	private String descricao;
	
	private StatusCliente(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}