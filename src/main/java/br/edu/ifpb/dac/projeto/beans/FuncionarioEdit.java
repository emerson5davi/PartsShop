package br.edu.ifpb.dac.projeto.beans;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.dac.projeto.entities.Funcionario;
import br.edu.ifpb.dac.projeto.entities.Endereco;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.FuncionarioService;
import br.edu.ifpb.dac.projeto.services.EnderecoService;
import br.edu.ifpb.dac.projeto.util.JSFUtils;
import br.edu.ifpb.dac.projeto.util.MessageUtils;

@ViewScoped
@ManagedBean
public class FuncionarioEdit implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	
	private FuncionarioService funcionarioService = new FuncionarioService();
	
	private EnderecoService enderecoService = new EnderecoService();

	public void preRenderView() {
		if (funcionario == null) {
			funcionario = new Funcionario();
			funcionario.setEndereco(new Endereco());
		}
		else{
			this.carregarCidades();
		}
	}

	public void saveFuncionario() throws PartsShopException{
		if (isEdicaoDeFuncionario()) {
			funcionarioService.update(funcionario);
			MessageUtils.messageSucess("Funcionário atualizado com sucesso.");
		} else {
			funcionarioService.add(funcionario);
			MessageUtils.messageSucess("Funcionário cadastrado com sucesso.");
		}

		JSFUtils.rederTo("FuncionarioView.xhtml");
		JSFUtils.setParam("funcionario", funcionario);
	}

	public boolean isEdicaoDeFuncionario() {
		return funcionario.getId() != null;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void carregarCidades() {
		if (funcionario.getEndereco().getEstado() != null){
			enderecoService.getCidades(funcionario.getEndereco().getEstado(),
					funcionario.getEndereco().getEstado().getCodigo());
		}
	}
}