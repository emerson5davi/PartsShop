package br.edu.ifpb.dac.projeto.beans.peca;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;

@ManagedBean
@ViewScoped
public class PecaViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Peca peca;

	public void preRenderView() {
		peca = (Peca) JSFUtils.getParam("peca");
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

}