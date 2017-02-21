package br.edu.ifpb.dac.projeto.enumerations;

public enum StatusCompra{
	
	ATRASADA("Atrasada"),
	EM_DIA("Em dia"),
	
	LB_EM_DIA("label label-success"),
	LB_ATRASADA("label label-danger");
	
	private String descricao;
	
	private StatusCompra(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}