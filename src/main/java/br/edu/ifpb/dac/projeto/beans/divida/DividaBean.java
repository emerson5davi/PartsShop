package br.edu.ifpb.dac.projeto.beans.divida;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Divida;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.DividaService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@Named
@ViewScoped
public class DividaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Divida> dividas;

	private Divida selectedDivida;

	@Inject
	private DividaService dividaService;

	@PostConstruct
	public void init() {
		carregarDividas();
	}

	public void carregarDividas() {
		dividas = dividaService.findAll();
	}

	public List<Divida> getDividas() {
		return dividas;
	}

	public Divida getSelectedDivida() {
		return selectedDivida;
	}

	public void setSelectedDivida(Divida selectedDivida) {
		this.selectedDivida = selectedDivida;
	}

	public void renderTo() {
		JSFUtils.rederTo("DividaView.xhtml");
		JSFUtils.setParam("divida", selectedDivida);
	}

	public void remove() throws PartsShopException {
		dividaService.remove(selectedDivida);
		MessageUtils.messageSucess("Dívida removida com sucesso.");
		JSFUtils.rederTo("Dividas.xhtml");
	}

	public String updateLabelStatus(String status) {
		if (status.equals("Quitada")) {
			return "label label-success";
		}

		else if (status.equals("Em dia")) {
			return "label label-info";
		}

		else {
			return "label label-danger";
		}
	}

	public String getStatus(Divida divida) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date data1 = new Date();

		if (divida.getDataPagamento() != null) {
			return "Quitada";
		}
		
		else if(format.format(data1).compareTo(format.format(divida.getDataVencimento())) == 0){
			return "Em dia";
		}

		else if (data1.after(divida.getDataVencimento())) {
			return "Atrasada";
		} 
		
		else {
			return "Em dia";
		}
	}
}