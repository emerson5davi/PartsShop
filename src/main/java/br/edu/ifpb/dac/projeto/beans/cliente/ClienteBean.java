package br.edu.ifpb.dac.projeto.beans.cliente;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.ClienteService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@ViewScoped
@Named
public class ClienteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Cliente> clientes;

	private Cliente selectedCliente;
	
	@Inject
	private ClienteService clienteService;

	@PostConstruct
	public void init() {
		carregarClientes();
	}
	
	public void carregarClientes(){
		clientes = clienteService.findAll();
	}

	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public Cliente getSelectedCliente() {
		return selectedCliente;
	}

	public void setSelectedCliente(Cliente selectedCliente) {
		this.selectedCliente = selectedCliente;
	}
	
	public void renderTo() {
		JSFUtils.rederTo("ClienteView.xhtml");
		JSFUtils.setParam("cliente", selectedCliente);
	}
	
	public void remove() throws PartsShopException {
		clienteService.remove(selectedCliente);
		MessageUtils.messageSucess("Cliente removido com sucesso.");
		JSFUtils.rederTo("Clientes.xhtml");
	}
}
