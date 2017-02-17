package br.edu.ifpb.dac.projeto.beans.cliente;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Endereco;
import br.edu.ifpb.dac.projeto.enumerations.Grupo;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.ClienteService;
import br.edu.ifpb.dac.projeto.services.EnderecoService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@ViewScoped
@ManagedBean
public class ClienteEdit implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	
	private ClienteService clienteService = new ClienteService();
	
	private EnderecoService enderecoService = new EnderecoService();
	
	private List<Grupo> grupos = Arrays.asList(Grupo.values());

	public void preRenderView() {
		if (cliente == null) {
			cliente = new Cliente();
			cliente.setEndereco(new Endereco());
		}
		else{
			this.carregarCidades();
		}
	}

	public void saveCliente(){
		if (isEdicaoDeCliente()) {
			clienteService.update(cliente);
			MessageUtils.messageSucess("Cliente atualizado com sucesso.");
			JSFUtils.rederTo("ClienteView.xhtml");
		} else {
			try {
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