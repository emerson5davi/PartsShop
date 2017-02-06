package br.edu.ifpb.dac.projeto.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.entities.ItemCompra;
import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.ClienteService;
import br.edu.ifpb.dac.projeto.services.CompraService;
import br.edu.ifpb.dac.projeto.services.PecaService;
import br.edu.ifpb.dac.projeto.util.JSFUtils;
import br.edu.ifpb.dac.projeto.util.MessageUtils;

@Named
@ViewScoped
public class CompraEdit implements Serializable {

	private static final long serialVersionUID = 1L;

	private CompraService compraService = new CompraService();

	private PecaService pecaService = new PecaService();
	
	private ClienteService clienteService = new ClienteService();

	private Compra compra;
	private List<Peca> pecas;
	private List<Cliente> clientes;
	private ItemCompra itemCompra;
	private ItemCompra selectedItemCompra = new ItemCompra();

	public CompraEdit() {
		pecas = new ArrayList<Peca>();
	}

	@PostConstruct
	public void init() {
		this.listOfPecas();
		this.listOfClientes();
	}

	public void preRenderView() {
		if (compra == null) {
			compra = new Compra();
			compra.setItensCompra(new ArrayList<ItemCompra>());
		}else{
		}
	}

	public void save() throws PartsShopException {
		if (isCompraEdited()) {
			compraService.update(compra);
			MessageUtils.messageSucess("Compra atualizada com sucesso.");

		} else {
			compraService.add(compra);
			MessageUtils.messageSucess("Compra efetuada com sucesso.");
		}
		
		JSFUtils.rederTo("compraView.xhtml");
		JSFUtils.setParam("compra", compra);
	}

	public void resetItemCompra() {
		this.itemCompra = selectedItemCompra;
		this.itemCompra.setQuantidade(selectedItemCompra.getQuantidade());
		this.selectedItemCompra = new ItemCompra();
	}
	
	public void listOfPecas() {
		this.pecas = pecaService.findAll();
	}
	
	public void listOfClientes(){
		this.clientes = clienteService.findAll();
	}
	
	public boolean isCompraEdited(){
		return compra.getId() != null; 
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCarga(Compra compra) {
		this.compra = compra;
	}

	public List<Peca> getPecas() {
		return pecas;
	}

	public void setPecas(List<Peca> pecas) {
		this.pecas = pecas;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ItemCompra getItemCompra() {
		return itemCompra;
	}

	public void setItemCompra(ItemCompra itemCompra) {
		this.itemCompra = itemCompra;
	}

	public ItemCompra getSelectedItemCompra() {
		return selectedItemCompra;
	}

	public void setSelectedItemCompra(ItemCompra selectedItemCompra) {
		this.selectedItemCompra = selectedItemCompra;
	}
}