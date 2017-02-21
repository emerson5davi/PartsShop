package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.dac.projeto.dao.EnderecoDao;
import br.edu.ifpb.dac.projeto.entities.Endereco;
import br.edu.ifpb.dac.projeto.enumerations.Estados;
import br.edu.ifpb.dac.projeto.util.jpa.TransacionalCdi;

public class EnderecoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static List<Estados> estados = new ArrayList<>();
	public static List<String> cidades =  new ArrayList<>();
	
	@Inject
	private EnderecoDao dao;

	public EnderecoService() {
		estados = Arrays.asList(Estados.values());
	}

	public void getCidades(Object estado, Integer codEstado) {
		cidades.clear();
		if (estado != null) {
			for (String cidadesFiltradas : listCidades(codEstado)) {
				cidades.add(cidadesFiltradas);
			}
		}
	}

	@TransacionalCdi
	public List<String> listCidades(Integer codEstado) {
		List<String> result = dao.getCidades(codEstado);
		return result;
	}
	
	@TransacionalCdi
	public List<Endereco> findByLogradouro(String logradouro) {
		List<Endereco> result = dao.findByLogradouro(logradouro);
		return result;
	}
}