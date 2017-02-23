package br.edu.ifpb.dac.projeto.enumerations;

public enum StatusCompraDivida{
	
	ATRASADA("Atrasada"),
	EM_DIA("Em dia"),
	QUITADA("Quitada"),
	
	LB_EM_DIA("label label-info"),
	LB_ATRASADA("label label-danger"),
	LB_QUITADA("label label-success");
	
	private String descricao;
	
	private StatusCompraDivida(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}