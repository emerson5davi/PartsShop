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
import br.edu.ifpb.dac.projeto.entities.Orcamento;
import br.edu.ifpb.dac.projeto.entities.Pagamento;
import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.enumerations.Payment;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopExceptionHandler;
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

	private Orcamento orcamentoCompra;

	private Pagamento pagamento;

	private List<Peca> pecas;

	private List<Cliente> clientes;

	private ItemCompra itemCompra;

	private ItemCompra selectedItemCompra = new ItemCompra();

	private Integer quant = 0;

	private BigDecimal valorParcela = new BigDecimal(0.0);

	public CompraEdit() {
		pecas = new ArrayList<Peca>();
	}

	@PostConstruct
	public void init() 	{
		this.listOfPecas();
		this.listOfClientes();
	}

	public void preRenderView() {
		System.out.println("Entra aqui..... " + orcamentoCompra);
		if (compra == null) {
			compra = new Compra();
			pagamento = new Pagamento();
			compra.setItensCompra(new ArrayList<ItemCompra>());
		}
		
		if(this.isOrcamentoExiste()) {
			this.compra.setData(orcamentoCompra.getData());
			this.compra.setItensCompra(this.orcamentoCompra.getItensCompra());
		}
	}

	public void save() throws PartsShopException {
		if (isCompraEdited()) {
			compraService.update(compra);
			MessageUtils.messageSucess("Compra atualizada com sucesso.");

		} else {
			if (compra.getItensCompra().isEmpty()) {
				throw new PartsShopExceptionHandler("Adicione itens na compra para finalizar!");
			}
			for (ItemCompra itemCompra : getCompra().getItensCompra()) {
				quant += itemCompra.getQuantidade();
			}
			if (getItemCompra().getPeca().getQuantidade() >= quant) {
				if (getPagamento().getPayment() != Payment.A_PRAZO && getPagamento().getDataPagamento() == null) {
					getPagamento().setDataPagamento(compra.getData());
				}
				compra.setPagamento(getPagamento());
				compraService.add(compra);
				Peca peca = pecaService.findById(getItemCompra().getPeca().getId());
				peca.setQuantidade(peca.getQuantidade() - quant);
				pecaService.update(peca);
				MessageUtils.messageSucess("Compra efetuada com sucesso.");
				quant = 0;
				JSFUtils.rederTo("Compras.xhtml");
			} else {
				quant = 0;
				throw new PartsShopExceptionHandler("Quantidade de peças a ser comprada excede o limite em estoque!");
			}
		}
	}

	public void resetItemCompra() {
		this.itemCompra = selectedItemCompra;
		this.itemCompra.setQuantidade(selectedItemCompra.getQuantidade());
		this.selectedItemCompra = new ItemCompra();
	}
	
	public boolean isOrcamentoExiste() {
		return this.orcamentoCompra != null;

	}

	public void listOfPecas() {
		this.pecas = pecaService.findAll();
	}

	public void listOfClientes() {
		this.clientes = clienteService.findAll();
	}

	public boolean isCompraEdited() {
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

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Orcamento getOrcamentoCompra() {
		return orcamentoCompra;
	}

	public void setOrcamentoCompra(Orcamento orcamentoCompra) {
		this.orcamentoCompra = orcamentoCompra;
	}

	public void calcularPreco() {
		BigDecimal quant = new BigDecimal(this.selectedItemCompra.getQuantidade());
		BigDecimal precoPeca = this.selectedItemCompra.getPeca().getPreco().multiply(quant);
		this.selectedItemCompra.setPreco(precoPeca);
	}

	public void calcularValorTotal() {
		pagamento.setValorTotal(CompraBean.getValor(compra));
	}

	public void calcularValorParcela() {
		BigDecimal total = new BigDecimal(0.0);
		for (ItemCompra itemCompra : getCompra().getItensCompra()) {
			total = total.add(itemCompra.getPreco());
		}
		setValorParcela(total.divide(new BigDecimal(getPagamento().getNumDeParcelas())));
	}

	public BigDecimal getCalcularValorParcela(Compra compra) {
		BigDecimal total = new BigDecimal(0.0);
		for (ItemCompra itemCompra : compra.getItensCompra()) {
			total = total.add(itemCompra.getPreco());
		}
		return total.divide(new BigDecimal(getPagamento().getNumDeParcelas()));
	}

	@SuppressWarnings("static-access")
	public List<Payment> getPayments() {
		this.payments = pagamentoService.payments;
		return payments;
	}
	
}