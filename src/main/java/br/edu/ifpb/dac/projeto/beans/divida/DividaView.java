package br.edu.ifpb.dac.projeto.beans.divida;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Divida;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;

@Named
@ViewScoped
public class DividaView implements Serializable {

	private static final long serialVersionUID = 1L;

	private Divida divida;

	public void preRenderView() {
		divida = (Divida) JSFUtils.getParam("divida");
	}

	public Divida getDivida() {
		return divida;
	}

	public void setDivida(Divida divida) {
		this.divida = divida;
	}

}