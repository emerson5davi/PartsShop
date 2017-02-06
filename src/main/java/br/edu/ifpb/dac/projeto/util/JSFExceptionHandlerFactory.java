package br.edu.ifpb.dac.projeto.util;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class JSFExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parentWrapped;

	public JSFExceptionHandlerFactory(ExceptionHandlerFactory parentWrapped) {
		this.parentWrapped = parentWrapped;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		return new JSFExceptionHandler(parentWrapped.getExceptionHandler());
	}

}
