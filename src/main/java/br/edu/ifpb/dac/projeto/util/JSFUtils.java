package br.edu.ifpb.dac.projeto.util;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;

public class JSFUtils implements Serializable {

	private static final long serialVersionUID = 712599930272775732L;

	public static void rederTo(String page) {
	      try {
	            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
	            FacesContext.getCurrentInstance().responseComplete();
	        } catch (IOException ex) {
	            throw new FacesException(ex);
	        }
	}

	public static void setParam(String tag, Object obj) {
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(tag, obj);
	}

	public static Object getParam(String tag) {
		return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(tag);
	}
}