package br.edu.ifpb.dac.projeto.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.enumerations.Estados;
import br.edu.ifpb.dac.projeto.services.EnderecoService;

@Named
@ViewScoped
public class EnderecoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Estados> estados = new ArrayList<>();
	private List<String> cidades = new ArrayList<>();
	
	
	public List<String> getCidades() {
		this.cidades = EnderecoService.cidades;
		return cidades;
	}
	
	public List<Estados> getEstados(){
		this.estados = EnderecoService.estados;
		return estados;
	}
}