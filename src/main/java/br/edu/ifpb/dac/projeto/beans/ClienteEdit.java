package br.edu.ifpb.dac.projeto.beans;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Endereco;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.ClienteService;
import br.edu.ifpb.dac.projeto.services.EnderecoService;
import br.edu.ifpb.dac.projeto.util.JSFUtils;
import br.edu.ifpb.dac.projeto.util.MessageUtils;

@ViewScoped
@ManagedBean
public class ClienteEdit implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	
	private ClienteService clienteService = new ClienteService();
	
	private EnderecoService enderecoService = new EnderecoService();

	public void preRenderView() {
		if (cliente == null) {
			cliente = new Cliente();
			cliente.setEndereco(new Endereco());
		}
		else{
			this.carregarCidades();
		}
	}

	public void saveCliente() throws PartsShopException{
		if (isEdicaoDeCliente()) {
			clienteService.update(cliente);
			MessageUtils.messageSucess("Cliente atualizado com sucesso.");
		} else {
			clienteService.add(cliente);
			MessageUtils.messageSucess("Cliente cadastrado com sucesso.");
		}

		JSFUtils.rederTo("ClienteView.xhtml");
		JSFUtils.setParam("cliente", cliente);
	}

	public boolean isEdicaoDeCliente() {
		return cliente.getId() != null;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public void carregarCidades() {
		if (cliente.getEndereco().getEstado() != null){
			enderecoService.getCidades(cliente.getEndereco().getEstado(),
					cliente.getEndereco().getEstado().getCodigo());
		}
	}
}