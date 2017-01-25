package br.edu.ifpb.dac.projeto.enumerations;

public enum MessagesType {

	SUCESS("Sucesso! "), ERROR("Erro! "), WARNING("Atenção! ");

	private String message;

	MessagesType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
