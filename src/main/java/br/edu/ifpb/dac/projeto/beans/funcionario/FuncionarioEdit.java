package br.edu.ifpb.dac.projeto.beans.funcionario;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.dac.projeto.entities.Funcionario;
import br.edu.ifpb.dac.projeto.enumerations.Grupo;
import br.edu.ifpb.dac.projeto.entities.Endereco;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.FuncionarioService;
import br.edu.ifpb.dac.projeto.services.EnderecoService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@ViewScoped
@ManagedBean
public class FuncionarioEdit implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	
	private FuncionarioService funcionarioService = new FuncionarioService();
	
	private EnderecoService enderecoService = new EnderecoService();
	
	private List<Grupo> grupos = Arrays.asList(Grupo.values());

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
	
	public List<Grupo> getGrupos(){
		return grupos;
	}
	
	public void setGrupos(List<Grupo> grupos){
		this.grupos = grupos;
	}
}