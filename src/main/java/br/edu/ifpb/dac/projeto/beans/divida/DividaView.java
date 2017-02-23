package br.edu.ifpb.dac.projeto.beans.divida;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Divida;
import br.edu.ifpb.dac.projeto.services.DividaService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@Named
@ViewScoped
public class DividaView implements Serializable {

	private static final long serialVersionUID = 1L;

	private Divida divida;
	
	@Inject
	private DividaService dividaService;

	public void preRenderView() {
		divida = (Divida) JSFUtils.getParam("divida");
	}

	public Divida getDivida() {
		return divida;
	}

	public void setDivida(Divida divida) {
		this.divida = divida;
	}
	
	public void pagar(){
		dividaService.update(divida);
		MessageUtils.messageSucess("Dívida paga com sucesso.");
		JSFUtils.rederTo("DividaView.xhtml");
		JSFUtils.setParam("divida", divida);
	}
	
	public boolean validaBtnPagar(){
		if(divida.getDataPagamento() == null){
			return true;
		}
		else{
			return false;
		}
	}

}