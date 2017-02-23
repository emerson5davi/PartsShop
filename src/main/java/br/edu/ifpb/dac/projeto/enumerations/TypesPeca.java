package br.edu.ifpb.dac.projeto.enumerations;

public enum TypesPeca{
	
	REPOSICAO("Reposição"),
	ACESSORIO("Acessório");
	
	private String descricao;
	
	private TypesPeca(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}