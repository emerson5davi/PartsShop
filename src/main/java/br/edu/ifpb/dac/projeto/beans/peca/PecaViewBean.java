package br.edu.ifpb.dac.projeto.beans.peca;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;

@Named
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