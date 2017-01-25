package br.edu.ifpb.dac.projeto.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.edu.ifpb.dac.projeto.entities.Funcionario;
import br.edu.ifpb.dac.projeto.enumerations.OrgsExpeds;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.services.FuncionarioService;
import br.edu.ifpb.dac.projeto.util.JSFUtils;
import br.edu.ifpb.dac.projeto.util.MessageUtils;


@ManagedBean
public class FuncionarioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Funcionario> funcionarios;
	
	private List<OrgsExpeds> orgaos;

	private Funcionario selectedFuncionario;
	
	private FuncionarioService funcionarioService;

	@PostConstruct
	public void init() {
		funcionarioService = new FuncionarioService();
		carregarFuncionarios();
	}
	
	public void carregarFuncionarios(){
		funcionarios = funcionarioService.findAll();
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	public Funcionario getSelectedFuncionario() {
		return selectedFuncionario;
	}

	public void setSelectedFuncionario(Funcionario selectedFuncionario) {
		this.selectedFuncionario = selectedFuncionario;
	}
	
	public void renderTo() {
		JSFUtils.rederTo("FuncionarioView.xhtml");
		JSFUtils.setParam("funcionario", selectedFuncionario);
	}
	
	public void remove() throws PartsShopException {
		funcionarioService.remove(selectedFuncionario);
		MessageUtils.messageSucess("Funcionário removido com sucesso.");
		JSFUtils.rederTo("Funcionarios.xhtml");
	}
	
	public List<OrgsExpeds> getOrgaos() {
		this.orgaos = Arrays.asList(OrgsExpeds.values());
		return orgaos;
	}
}
