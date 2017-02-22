package br.edu.ifpb.dac.projeto.beans.compra;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.entities.ItemCompra;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.CompraService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@Named
@RequestScoped
public class CompraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CompraService compraService;

	private List<Compra> compras;

	private Compra selectedCompra;

	@PostConstruct
	public void init() {
		this.listOfCompras();
	}

	public void remove() throws PartsShopException {
		compraService.remove(selectedCompra);
		MessageUtils.messageSucess("Compra removida com sucesso.");
		JSFUtils.rederTo("Compras.xhtml");
	}

	public void renderTo() {
		JSFUtils.rederTo("CompraView.xhtml");
		JSFUtils.setParam("compra", selectedCompra);
	}

	public void listOfCompras() {
		this.compras = compraService.findAll();
	}

	public String getPecas(Compra compra) {
		ArrayList<String> array = new ArrayList<String>();

		for (ItemCompra itemCompra : compra.getItensCompra()) {
			array.add(itemCompra.getPeca().getNome());
		}

		ArrayList<String> array2 = array;

		if (array.size() > 1) {
			for (int j = 0; j < array.size(); j++) {
				if (array.get(j).equals(array2.get(j))) {
					array.remove(j);
				}
			}
		}

		return array.toString().replace("[", "").replace("]", "");
	}

	public String getQuantidade(Compra compra) {
		int quant = 0;
		for (ItemCompra itemCompra : compra.getItensCompra()) {
			quant += itemCompra.getQuantidade();
		}
		return Integer.toString(quant);
	}

	public static BigDecimal getValor(Compra compra) {
		BigDecimal valor = new BigDecimal(0.0);
		for (ItemCompra itemCompra : compra.getItensCompra()) {
			valor = valor.add(itemCompra.getPreco());
		}

		return valor;
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

	public List<Compra> getComprasByCliente(Cliente cliente) {
		return compraService.getComprasByCliente(cliente);
	}

	public String getPayment(Compra compra) {
		return compra.getPagamento().getPayment().getTipoDePagamento();
	}

	public BigDecimal getTotalCompras(Cliente cliente) {
		BigDecimal total = new BigDecimal(0.0);
		for (Compra compra : getComprasByCliente(cliente)) {
			total = total.add(getValor(compra));
		}
		return total;
	}

}