package br.edu.ifpb.dac.projeto.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.edu.ifpb.dac.projeto.enumerations.MessagesType;

public class MessageUtils implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static void messageSucess(String msg) {
		messageFaces(MessagesType.SUCESS.getMessage(), msg);
	}

	public static void messageError(String msg) {
		messageFaces(MessagesType.ERROR.getMessage(), msg);

	}

	public static void messageWarn(String msg) {
		messageFaces(MessagesType.WARNING.getMessage(), msg);

	}

	private static void messageFaces(String type, String message) {

		Severity severity = FacesMessage.SEVERITY_WARN;

		if (type.equals(MessagesType.ERROR.getMessage())) {
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}
		if (type.equals(MessagesType.SUCESS.getMessage())) {
			severity = FacesMessage.SEVERITY_INFO;
		}

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.setKeepMessages(true);
        flash.setRedirect(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, type, message));
	}
}
