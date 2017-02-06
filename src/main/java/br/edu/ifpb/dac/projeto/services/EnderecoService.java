package br.edu.ifpb.dac.projeto.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.ifpb.dac.projeto.dao.EnderecoDao;
import br.edu.ifpb.dac.projeto.enumerations.Estados;

public class EnderecoService{


	public static List<Estados> estados = new ArrayList<>();
	public static List<String> cidades =  new ArrayList<>();
	private EnderecoDao dao = new EnderecoDao();

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

	public List<String> listCidades(Integer codEstado) {
		List<String> result = dao.getCidades(codEstado);
		return result;
	}
}