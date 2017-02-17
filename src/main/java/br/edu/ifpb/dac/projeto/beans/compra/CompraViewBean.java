package br.edu.ifpb.dac.projeto.beans.compra;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;

@ManagedBean
@ViewScoped
public class CompraViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Compra compra;

	public void preRenderView() {
		compra = (Compra) JSFUtils.getParam("compra");
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
}