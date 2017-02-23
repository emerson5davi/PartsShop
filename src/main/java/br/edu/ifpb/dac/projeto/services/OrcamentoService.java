package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.dac.projeto.dao.OrcamentoDao;
import br.edu.ifpb.dac.projeto.entities.Orcamento;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.util.jpa.TransacionalCdi;

public class OrcamentoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrcamentoDao dao;

	public OrcamentoService() {
	}
	
	@TransacionalCdi
	public void add(Orcamento orcamento) throws PartsShopException {
		dao.add(orcamento);
	}
	
	@TransacionalCdi
	public void remove(Orcamento orcamento) throws PartsShopException {
		dao.remove(orcamento);
	}

	@TransacionalCdi
	public Orcamento findById(Long id){
		return dao.findById(id);
	}
	
	@TransacionalCdi
	public Orcamento update(Orcamento orcamento){
		return dao.update(orcamento);
	}
	
	@TransacionalCdi
	public List<Orcamento> findAll() {
		return dao.findAll();
	}
	
}