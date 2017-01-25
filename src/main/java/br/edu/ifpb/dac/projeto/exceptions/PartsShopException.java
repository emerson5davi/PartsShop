package br.edu.ifpb.dac.projeto.exceptions;
public class PartsShopException extends Exception {

	private static final long serialVersionUID = -7669751088704144947L;

	public PartsShopException(String message) {
		super(message);
	}

	public PartsShopException(String message, Throwable cause) {
		super(message, cause);
	}

}
