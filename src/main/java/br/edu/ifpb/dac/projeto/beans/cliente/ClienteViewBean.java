package br.edu.ifpb.dac.projeto.beans.cliente;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;

@Named
@ViewScoped
public class ClienteViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;

	public void preRenderView() {
		cliente = (Cliente) JSFUtils.getParam("cliente");
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}