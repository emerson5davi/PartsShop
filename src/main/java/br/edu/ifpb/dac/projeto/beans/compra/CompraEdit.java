package br.edu.ifpb.dac.projeto.beans.compra;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.entities.ItemCompra;
import br.edu.ifpb.dac.projeto.entities.Pagamento;
import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.enumerations.Payment;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.ClienteService;
import br.edu.ifpb.dac.projeto.services.CompraService;
import br.edu.ifpb.dac.projeto.services.PagamentoService;
import br.edu.ifpb.dac.projeto.services.PecaService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@Named
@ViewScoped
public class CompraEdit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CompraService compraService;

	@Inject
	private PecaService pecaService;
	
	@Inject
	private ClienteService clienteService;
	
	@Inject
	private PagamentoService pagamentoService;
	
	private List<Payment> payments = new ArrayList<Payment>();
	
	private Compra compra;
	
	private Pagamento pagamento;
	
	private List<Peca> pecas;
	
	private List<Cliente> clientes;
	
	private ItemCompra itemCompra;
	
	private ItemCompra selectedItemCompra = new ItemCompra();
	
	private Integer quant = 0;
	
	private Double valorParcela = 0.0;
	
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
			pagamento = new Pagamento();
			compra.setItensCompra(new ArrayList<ItemCompra>());
		}else{
		}
	}

	public void save() throws PartsShopException {
		if (isCompraEdited()) {
			compraService.update(compra);
			MessageUtils.messageSucess("Compra atualizada com sucesso.");

		} else {
			for (ItemCompra itemCompra : getCompra().getItensCompra()) {
				quant += itemCompra.getQuantidade();
			}
			if(getItemCompra().getPeca().getQuantidade() >= quant){
				compra.setPagamento(getPagamento());
				compraService.add(compra);
				Peca peca = pecaService.findById(getItemCompra().getPeca().getId());
				peca.setQuantidade(peca.getQuantidade()-quant);
				pecaService.update(peca);
				MessageUtils.messageSucess("Compra efetuada com sucesso.");
				quant = 0;
				JSFUtils.rederTo("Compras.xhtml");
			}
			else{
				MessageUtils.messageError("Quantidade de peças a ser comprada excede o limite em estoque!");
				quant = 0;
			}
		}
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

	public void setCompra(Compra compra) {
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

	public String getValorParcela(){
		return String.format("R$ %.2f", valorParcela);
	}
	
	public void setValorParcela(Double valorParcela){
		this.valorParcela = valorParcela;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public void calcularPreco(){
		Double precoPeca = this.selectedItemCompra.getPeca().getPreco() * this.selectedItemCompra.getQuantidade();
		this.selectedItemCompra.setPreco(precoPeca);
	}
	
	public void calcularValorTotal(){
		pagamento.setValor(BigDecimal.valueOf(CompraBean.getValor(compra)));
	}
	
	public void calcularValorParcela(){
		Double total = 0.0;
		for (ItemCompra itemCompra : getCompra().getItensCompra()) {
			total += itemCompra.getPreco();
		}
		setValorParcela(total/getPagamento().getNumDeParcelas());
	}
	
	public List<Payment> getPayments(){
		this.payments = pagamentoService.payments;
		return payments;
	}
	
}