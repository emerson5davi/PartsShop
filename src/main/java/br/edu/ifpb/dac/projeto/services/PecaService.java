package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.dac.projeto.dao.PecaDao;
import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.util.jpa.TransacionalCdi;

public class PecaService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PecaDao dao;

	public PecaService() {
	}
	
	@TransacionalCdi
	public void add(Peca peca) throws PartsShopException {
		dao.add(peca);
	}
	
	@TransacionalCdi
	public void remove(Peca peca) throws PartsShopException {
		dao.remove(peca);
	}

	@TransacionalCdi
	public Peca findById(Long id){
		return dao.findById(id);
	}
	
	@TransacionalCdi
	public Peca update(Peca peca){
		return dao.update(peca);
	}
	
	@TransacionalCdi
	public List<Peca> findAll() {
		return dao.findAll();
	}
}