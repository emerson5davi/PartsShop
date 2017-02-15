package br.edu.ifpb.dac.projeto.enumerations;

public enum Payment {
	
	A_PRAZO("À prazo"), 
	A_VISTA("À vista"), 
	CARTAO("Cartão"), 
	CHEQUE("Cheque");

	private String tipoDePagamento;
	
	Payment(String tipoDePagamento){
		this.tipoDePagamento = tipoDePagamento;
	}

	public String getTipoDePagamento() {
		return tipoDePagamento;
	}

	public void setTipoDePagamento(String tipoDePagamento) {
		this.tipoDePagamento = tipoDePagamento;
	}
}