package br.edu.ifpb.dac.projeto.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.CompraService;
import br.edu.ifpb.dac.projeto.util.JSFUtils;
import br.edu.ifpb.dac.projeto.util.MessageUtils;

@Named
@RequestScoped
public class CompraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private CompraService compraService = new CompraService();

	private List<Compra> compras;

	private Compra selectedCompra;

	
	@PostConstruct
	public void init(){
		this.listOfCompras();
	}
	
	public void remove() throws PartsShopException{
		compraService.remove(selectedCompra);
		MessageUtils.messageSucess("Compra removida com sucesso.");
		JSFUtils.rederTo("Compras.xhtml");
	}
	
	public void renderTo() {
		JSFUtils.rederTo("CompraView.xhtml");
		JSFUtils.setParam("compra", selectedCompra);
	}
	
	public void listOfCompras(){
		this.compras = compraService.findAll();
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra getSelectedCompra() {
		return selectedCompra;
	}

	public void setSelectedCompra(Compra selectedCompra) {
		this.selectedCompra = selectedCompra;
	}
}