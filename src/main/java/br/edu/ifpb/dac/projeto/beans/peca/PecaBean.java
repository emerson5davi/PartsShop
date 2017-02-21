package br.edu.ifpb.dac.projeto.beans.peca;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.PecaService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@Named
@ViewScoped
public class PecaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Peca> pecas;

	private Peca selectedPeca;

	@Inject
	private PecaService pecaService;

	@PostConstruct
	public void init() {
		carregarPecas();
	}
	
	public void carregarPecas(){
		pecas = pecaService.findAll();
	}

	public List<Peca> getPecas() {
		return pecas;
	}
	
	public Peca getSelectedPeca() {
		return selectedPeca;
	}

	public void setSelectedPeca(Peca selectedPeca) {
		this.selectedPeca = selectedPeca;
	}
	
	public void renderTo() {
		JSFUtils.rederTo("PecaView.xhtml");
		JSFUtils.setParam("peca", selectedPeca);
	}
	
	public void remove() throws PartsShopException {
		pecaService.remove(selectedPeca);
		MessageUtils.messageSucess("Peça removida com sucesso.");
		JSFUtils.rederTo("Pecas.xhtml");
	}
}
