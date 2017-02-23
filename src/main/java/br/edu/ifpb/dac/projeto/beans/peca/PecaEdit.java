package br.edu.ifpb.dac.projeto.beans.peca;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.enumerations.Payment;
import br.edu.ifpb.dac.projeto.enumerations.TypesPeca;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.PecaService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@ViewScoped
@Named
public class PecaEdit implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Peca peca;
	
	private boolean modelo;
	
	private boolean ano;
	
	@Inject
	private PecaService pecaService;
	
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
	
	public List<TypesPeca> getTypesPeca(){
		return pecaService.typesPeca;
	}
	
	public void setModelo(boolean todos){
		if(todos == true){
			peca.setModelo("Todos");
			this.modelo = true;
		}
		else if(todos == false){
			peca.setModelo("");
			this.modelo = false;
		}
		else if (peca.getModelo().equals("Todos")) {
			this.modelo = true;
		}
		else if(!peca.getModelo().equals("Todos")){
			this.modelo = false;
		}
	}
	
	public boolean getModelo(){
		return modelo;
	}
	
	public void setAno(boolean todos){
		if(todos == true){
			peca.setAno("Todos");
			this.ano = true;
		}
		else if(todos == false){
			peca.setAno("");
			this.ano = false;
		}
		else if (peca.getAno().equals("Todos")) {
			this.ano = true;
		}
		else if(!peca.getAno().equals("Todos")){
			this.ano = false;
		}
	}
	
	public boolean getAno(){
		return ano;
	}
}