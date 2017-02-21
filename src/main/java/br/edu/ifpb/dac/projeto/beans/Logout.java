package br.edu.ifpb.dac.projeto.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class Logout extends AbstractBean {

	private static final long serialVersionUID = -7437667367775973347L;

	public String efetuarLogout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
		return "/pages/admin/index.xhtml?faces-redirect=true";
	}

}
