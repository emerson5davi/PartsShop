package br.edu.ifpb.dac.projeto.beans;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.util.JSFUtils;

@ManagedBean
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