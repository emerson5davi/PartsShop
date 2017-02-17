package br.edu.ifpb.dac.projeto.beans.peca;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.PecaService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@ViewScoped
@ManagedBean
public class PecaEdit implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Peca peca;
	
	private PecaService pecaService = new PecaService();
	
	public void preRenderView() {
		if (peca == null) {
			peca = new Peca();
		}
	}

	public void savePeca() throws PartsShopException{
		if (isEdicaoDePeca()) {
			pecaService.update(peca);
			MessageUtils.messageSucess("Peça atualizada com sucesso.");
		} else {
			pecaService.add(peca);
			MessageUtils.messageSucess("Peça cadastrada com sucesso.");
		}

		JSFUtils.rederTo("PecaView.xhtml");
		JSFUtils.setParam("peca", peca);
	}

	public boolean isEdicaoDePeca() {
		return peca.getId() != null;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public Peca getPeca() {
		return peca;
	}	
}