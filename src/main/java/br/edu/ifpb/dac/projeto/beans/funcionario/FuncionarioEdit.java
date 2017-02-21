package br.edu.ifpb.dac.projeto.beans.funcionario;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Funcionario;
import br.edu.ifpb.dac.projeto.enumerations.Grupo;
import br.edu.ifpb.dac.projeto.entities.Endereco;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.FuncionarioService;
import br.edu.ifpb.dac.projeto.services.ServicePartsShopException;
import br.edu.ifpb.dac.projeto.services.EnderecoService;
import br.edu.ifpb.dac.projeto.util.jsf.JSFUtils;
import br.edu.ifpb.dac.projeto.util.messages.MessageUtils;

@ViewScoped
@Named
public class FuncionarioEdit implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	
	private String senhaAntiga;
	
	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private EnderecoService enderecoService;
	
	private List<Grupo> grupos = Arrays.asList(Grupo.ADMIN,Grupo.FUNCIONARIO);

	public void preRenderView() {
		if (funcionario == null) {
			funcionario = new Funcionario();
			funcionario.setEndereco(new Endereco());
		}
		else{
			this.carregarCidades();
			senhaAntiga = funcionario.getSenha();
		}
	}

	public void saveFuncionario() throws PartsShopException{
		if (isEdicaoDeFuncionario()) {
			if (!funcionario.getSenha().equals(senhaAntiga)) {
				try {
					funcionarioService.criptografarSenha(funcionario);
				} catch (ServicePartsShopException e) {
					MessageUtils.messageError(e.getMessage());
				}
			}
			funcionarioService.update(funcionario);
			MessageUtils.messageSucess("Funcionário atualizado com sucesso.");
			JSFUtils.rederTo("FuncionarioView.xhtml");
			JSFUtils.setParam("funcionario", funcionario);
		} else {
			try {
				funcionarioService.criptografarSenha(funcionario);
				funcionarioService.add(funcionario);
				MessageUtils.messageSucess("Funcionário cadastrado com sucesso.");
				JSFUtils.rederTo("FuncionarioView.xhtml");
				JSFUtils.setParam("funcionario", funcionario);
			} catch (PartsShopException e) {
				MessageUtils.messageError(e.getMessage());
			}
		}
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