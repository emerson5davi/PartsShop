package br.edu.ifpb.dac.projeto.beans.divida;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
public class DividaEdit implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Divida divida;
	
	@Inject
	private DividaService dividaService;
	
	public void preRenderView() {
		if (divida == null) {
			divida = new Divida();
		}
	}

	public void save() throws PartsShopException{
		if (isEdicaoDeDivida()) {
			dividaService.update(divida);
			MessageUtils.messageSucess("Dívida atualizada com sucesso.");
		} else {
			dividaService.add(divida);
			MessageUtils.messageSucess("Dívida cadastrada com sucesso.");
		}

		JSFUtils.rederTo("DividaView.xhtml");
		JSFUtils.setParam("divida", divida);
	}

	public boolean isEdicaoDeDivida() {
		return divida.getId() != null;
	}

	public void setDivida(Divida divida) {
		this.divida = divida;
	}
	
	public List<String> getNomeEmpresas(String nomeEmpresa){
		List<String> ad =  dividaService.findNomeEmpresas(nomeEmpresa);
		HashSet<String> aux = new HashSet<String>();
		
		for (String string : ad) {
			aux.add(string);
		}
		return new ArrayList<String>(aux);
	}

	public Divida getDivida() {
		return divida;
	}	
}