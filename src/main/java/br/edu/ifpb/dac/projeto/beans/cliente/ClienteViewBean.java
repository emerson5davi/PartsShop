package br.edu.ifpb.dac.projeto.beans.cliente;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.entities.ItemPagamento;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopExceptionHandler;
import br.edu.ifpb.dac.projeto.services.PagamentoService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

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
		System.out.println(this.compra);
	}

	public void validaCampo() throws PartsShopException{
		if(compra.getPagamento().getValorPago() == null){
			compra.getPagamento().setValorPago(new BigDecimal("0"));
		}

		if(itemPagamento.getValor().compareTo((compra.getPagamento().getValorTotal().subtract(compra.getPagamento().getValorPago()))) == 0 || itemPagamento.getValor().compareTo((compra.getPagamento().getValorTotal().subtract(compra.getPagamento().getValorPago()))) == -1){
			compra.getPagamento().setValorPago(itemPagamento.getValor());
			itemPagamento = new ItemPagamento();
		}
		else{
			itemPagamento = new ItemPagamento();
			throw new PartsShopExceptionHandler("Valor do pagamento ultrapassa o valor da compra");
		}
		
	}
	
	public void efetuarPagamento(){
		pagamentoSevice.update(compra.getPagamento());
		itemPagamento = new ItemPagamento();
		MessageUtils.messageSucess("Pagamento realizado com sucesso.");
		JSFUtils.rederTo("ClienteView.xhtml");
	}
	
}