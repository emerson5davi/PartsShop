package br.edu.ifpb.dac.projeto.beans.cliente;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Endereco;
import br.edu.ifpb.dac.projeto.enumerations.Grupo;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.ClienteService;
import br.edu.ifpb.dac.projeto.services.EnderecoService;
import br.edu.ifpb.dac.projeto.services.ServicePartsShopException;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@ViewScoped
@Named
public class ClienteEdit implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	
	private String senhaAntiga;
	
	@Inject
	private ClienteService clienteService;
	
	@Inject
	private EnderecoService enderecoService;
	
	private List<Grupo> grupos = Arrays.asList(Grupo.CLIENTE);

	public void preRenderView() {
		if (cliente == null) {
			cliente = new Cliente();
			cliente.setEndereco(new Endereco());
		}
		else{
			this.carregarCidades();
			senhaAntiga = cliente.getSenha();
		}
	}

	public void saveCliente(){
		if (isEdicaoDeCliente()) {
			if (!cliente.getSenha().equals(senhaAntiga)) {
				try {
					clienteService.criptografarSenha(cliente);
				} catch (ServicePartsShopException e) {
					MessageUtils.messageError(e.getMessage());
				}
			}
			clienteService.update(cliente);
			MessageUtils.messageSucess("Cliente atualizado com sucesso.");
			JSFUtils.rederTo("ClienteView.xhtml");
			JSFUtils.setParam("cliente", cliente);
		} else {
			try {
				clienteService.criptografarSenha(cliente);
				clienteService.add(cliente);
				MessageUtils.messageSucess("Cliente cadastrado com sucesso.");
				JSFUtils.rederTo("ClienteView.xhtml");
				JSFUtils.setParam("cliente", cliente);
			} catch (PartsShopException e) {
				MessageUtils.messageError(e.getMessage());
			}
		}
	}

	public boolean isEdicaoDeCliente() {
		return cliente.getId() != null;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Grupo> getGrupos(){
		return grupos;
	}
	
	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

	public void setGrupos(List<Grupo> grupos){
		this.grupos = grupos;
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