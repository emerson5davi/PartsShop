package br.edu.ifpb.dac.projeto.beans.orcamento;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Orcamento;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;

@Named
@ViewScoped
public class OrcamentoViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Orcamento orcamento;

	public void preRenderView() {
		orcamento = (Orcamento) JSFUtils.getParam("orcamento");
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
}