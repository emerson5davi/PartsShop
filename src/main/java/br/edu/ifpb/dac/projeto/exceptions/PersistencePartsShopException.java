package br.edu.ifpb.dac.projeto.exceptions;

public class PersistencePartsShopException extends PartsShopException{

	private static final long serialVersionUID = 7159282553688713660L;

	public PersistencePartsShopException(String message) {
		super(message);
	}

	public PersistencePartsShopException(String message, Throwable cause) {
		super(message, cause);
	}

}
