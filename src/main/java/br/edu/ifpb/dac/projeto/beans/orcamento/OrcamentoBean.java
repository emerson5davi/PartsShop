package br.edu.ifpb.dac.projeto.beans.orcamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.ItemCompra;
import br.edu.ifpb.dac.projeto.entities.Orcamento;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.OrcamentoService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@Named
@RequestScoped
public class OrcamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OrcamentoService orcamentoService;

	private List<Orcamento> orcamentos;

	private Orcamento selectedOrcamento;

	@PostConstruct
	public void init() {
		this.listOfOrcamentos();
	}

	public void remove() throws PartsShopException {
		orcamentoService.remove(selectedOrcamento);
		MessageUtils.messageSucess("Orçamento removido com sucesso.");
		JSFUtils.rederTo("Orcamentos.xhtml");
	}

	public void renderTo() {
		JSFUtils.rederTo("OrcamentoView.xhtml");
		JSFUtils.setParam("orcamento", selectedOrcamento);
	}

	public void listOfOrcamentos() {
		this.orcamentos = orcamentoService.findAll();
	}

	public String getPecas(Orcamento orcamento) {
		ArrayList<String> array = new ArrayList<String>();

		for (ItemCompra itemCompra : orcamento.getItensCompra()) {
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

	public String getQuantidade(Orcamento orcamento) {
		int quant = 0;
		for (ItemCompra itemCompra : orcamento.getItensCompra()) {
			quant += itemCompra.getQuantidade();
		}
		return Integer.toString(quant);
	}

	public static BigDecimal getValor(Orcamento orcamento) {
		BigDecimal valor = new BigDecimal(0.0);
		for (ItemCompra itemCompra : orcamento.getItensCompra()) {
			valor = valor.add(itemCompra.getPreco());
		}

		return valor;
	}

	public List<Orcamento> getOrcamentos() {
		return orcamentos;
	}

	public void setOrcamentos(List<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
	}

	public Orcamento getSelectedOrcamento() {
		return selectedOrcamento;
	}

	public void setSelectedOrcamento(Orcamento selectedOrcamento) {
		this.selectedOrcamento = selectedOrcamento;
	}

}