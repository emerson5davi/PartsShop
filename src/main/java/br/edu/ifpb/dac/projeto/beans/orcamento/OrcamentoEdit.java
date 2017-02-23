package br.edu.ifpb.dac.projeto.beans.orcamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.entities.ItemCompra;
import br.edu.ifpb.dac.projeto.entities.Orcamento;
import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopExceptionHandler;
import br.edu.ifpb.dac.projeto.services.OrcamentoService;
import br.edu.ifpb.dac.projeto.services.PecaService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@Named
@ViewScoped
public class OrcamentoEdit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OrcamentoService orcamentoService;

	@Inject
	private PecaService pecaService;

	private Orcamento orcamento;

	private List<Peca> pecas;

	private ItemCompra itemCompra;

	private ItemCompra selectedItemCompra = new ItemCompra();

	public OrcamentoEdit() {
		pecas = new ArrayList<Peca>();
	}

	@PostConstruct
	public void init() {
		this.listOfPecas();
	}

	public void preRenderView() {
		if (orcamento == null) {
			orcamento = new Orcamento();
			orcamento.setItensCompra(new ArrayList<ItemCompra>());
		} else {
		}
	}

	public void save() throws PartsShopException {
		if (isOrcamentoEdited()) {
			orcamentoService.update(orcamento);
			MessageUtils.messageSucess("Orçamento atualizado com sucesso.");
		} else {
			if(orcamento.getItensCompra().isEmpty()){
				throw new PartsShopExceptionHandler("Adicione itens no orçamento para finalizar!");
			}
			orcamentoService.add(orcamento);
			MessageUtils.messageSucess("Orçamento cadastrado com sucesso.");
		}

		JSFUtils.rederTo("OrcamentoView.xhtml");
		JSFUtils.setParam("orcamento", orcamento);
	}

	public void resetItemCompra() {
		this.itemCompra = selectedItemCompra;
		this.itemCompra.setQuantidade(selectedItemCompra.getQuantidade());
		this.selectedItemCompra = new ItemCompra();
	}
	
	public void comprar() {
		JSFUtils.rederTo("/PartsShop/paginas/admin/compra/CompraEdit.xhtml");
		System.out.println(this.orcamento);
		JSFUtils.setParam("orcamentoCompra", this.orcamento);
		

	}

	public void listOfPecas() {
		this.pecas = pecaService.findAll();
	}

	public boolean isOrcamentoEdited() {
		return orcamento.getId() != null;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public List<Peca> getPecas() {
		return pecas;
	}

	public void setPecas(List<Peca> pecas) {
		this.pecas = pecas;
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

	public void calcularPreco() {
		BigDecimal quant = new BigDecimal(this.selectedItemCompra.getQuantidade());
		BigDecimal precoPeca = this.selectedItemCompra.getPeca().getPreco().multiply(quant);
		this.selectedItemCompra.setPreco(precoPeca);
	}
	
	public void viraCompra() throws PartsShopExceptionHandler{
		if(orcamento.getData() == null || orcamento.getItensCompra().isEmpty()){
			throw new PartsShopExceptionHandler("Preencha todos os campos obrigatórios!");
		}
		else{
			Compra compra = new Compra();
			compra.setData(orcamento.getData());
			compra.setItensCompra(orcamento.getItensCompra());
		}
		
	}

}