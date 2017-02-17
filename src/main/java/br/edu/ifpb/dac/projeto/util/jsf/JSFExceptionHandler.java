package br.edu.ifpb.dac.projeto.util.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import br.edu.ifpb.dac.projeto.exceptions.PartsShopExceptionHandler;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

public class JSFExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	public JSFExceptionHandler(ExceptionHandler exceptionHandler) {
		this.wrapped = exceptionHandler;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents()
				.iterator();

		while (events.hasNext()) {
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();

			Throwable exception = context.getException();
			
			PartsShopExceptionHandler partsShopException = getPartsShopException(exception);

			boolean handled = false;

			try {
				if (exception instanceof ViewExpiredException) {
					handled = true;
					redirect("/");
					
				}else if(partsShopException != null){
					handled = true;
					MessageUtils.messageError(partsShopException.getMessage());
					
				} else {
				}
			} finally {
				if (handled) {
					events.remove();
				}
			}
		}
		getWrapped().handle();
	}

	private PartsShopExceptionHandler getPartsShopException(Throwable exception) {
		
		if(exception instanceof PartsShopExceptionHandler){
			return (PartsShopExceptionHandler) exception;
			
		} else if(exception.getCause() != null){
			return getPartsShopException(exception.getCause());
		}

		return null;
	}

	private void redirect(String page) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			String contextPath = externalContext.getRequestContextPath();

			externalContext.redirect(contextPath + page);
			facesContext.responseComplete();
		} catch (IOException ex) {
			throw new FacesException(ex);
		}
	}
}