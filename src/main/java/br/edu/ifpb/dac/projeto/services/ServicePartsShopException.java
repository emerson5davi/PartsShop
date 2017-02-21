package br.edu.ifpb.dac.projeto.services;

import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class ServicePartsShopException extends PartsShopException {

	private static final long serialVersionUID = -3082351960302866350L;

	public ServicePartsShopException(String mensagem) {
		super(mensagem);
	}

	public ServicePartsShopException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
