package br.edu.ifpb.dac.projeto.beans.funcionario;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Funcionario;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;

@Named
@ViewScoped
public class FuncionarioViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;

	public void preRenderView() {
		funcionario = (Funcionario) JSFUtils.getParam("funcionario");
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}