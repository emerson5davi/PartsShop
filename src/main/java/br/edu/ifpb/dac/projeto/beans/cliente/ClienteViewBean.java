package br.edu.ifpb.dac.projeto.beans.cliente;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.entities.ItemPagamento;
import br.edu.ifpb.dac.projeto.enumerations.Payment;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopExceptionHandler;
import br.edu.ifpb.dac.projeto.services.PagamentoService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;

@Named
@ViewScoped
public class ClienteViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;

	private ItemPagamento itemPagamento = new ItemPagamento();

	private Compra compra;

	@Inject
	private PagamentoService pagamentoSevice;

	public void preRenderView() {
		cliente = (Cliente) JSFUtils.getParam("cliente");
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ItemPagamento getItemPagamento() {
		return itemPagamento;
	}

	public void setItemPagamento(ItemPagamento itemPagamento) {
		this.itemPagamento = itemPagamento;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public void deleteItemPagamento(ItemPagamento itemPagamento) {
		List<ItemPagamento> itens = compra.getPagamento().getItensPagamento();
		itens.remove(itemPagamento);
		compra.getPagamento().setItensPagamento(itens);
		compra.getPagamento().setValorPago(compra.getPagamento().getValorPago().subtract(itemPagamento.getValor()));
		pagamentoSevice.update(compra.getPagamento());
	}

	public void validaCampo() throws PartsShopException {

		List<ItemPagamento> itens = new ArrayList<ItemPagamento>();

		if (compra.getPagamento().getValorPago() == null) {
			compra.getPagamento().setValorPago(new BigDecimal("0"));
		}

		BigDecimal valorItem = itemPagamento.getValor();
		BigDecimal valorPago = compra.getPagamento().getValorPago();
		BigDecimal valorTotal = compra.getPagamento().getValorTotal();

		if (comparaBigDecimal(valorItem, subtrairTotal(valorTotal, valorPago)) == 0
				|| comparaBigDecimal(valorItem, subtrairTotal(valorTotal, valorPago)) == -1) {
			itens = compra.getPagamento().getItensPagamento();
			itens.add(getItemPagamento());
			compra.getPagamento().setItensPagamento(itens);
			compra.getPagamento().setValorPago(compra.getPagamento().getValorPago().add(itemPagamento.getValor()));
			pagamentoSevice.update(compra.getPagamento());
			itemPagamento = new ItemPagamento();
		} else {
			itemPagamento = new ItemPagamento();
			throw new PartsShopExceptionHandler("Valor do pagamento ultrapassa o valor da compra");
		}

	}

	public BigDecimal subtrairTotal(BigDecimal valor1, BigDecimal valor2) {
		return valor1.subtract(valor2);
	}

	public int comparaBigDecimal(BigDecimal valor1, BigDecimal valor2) {
		return valor1.compareTo(valor2);
	}
	
	public BigDecimal calcValor(Compra compra) {
		if(compra.getPagamento().getValorPago() == null){
			compra.getPagamento().setValorPago(new BigDecimal(0.0));
		}
		return compra.getPagamento().getValorTotal().subtract(compra.getPagamento().getValorPago());
	}
	
	public boolean validaBtnPagar(Compra compra){
		if(compra.getPagamento().getValorPago() == null){
			compra.getPagamento().setValorPago(new BigDecimal(0.0));
		}
		if(compra.getPagamento().getValorPago().compareTo(compra.getPagamento().getValorTotal()) == 0){
			return false;
		}
		if(compra.getPagamento().getPayment() != Payment.A_PRAZO){
			return false;
		}
		else{
			return true;
		}
	}

}