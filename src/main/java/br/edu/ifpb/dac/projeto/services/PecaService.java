package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.dac.projeto.dao.PecaDao;
import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class PecaService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private PecaDao dao = new PecaDao();

	public PecaService() {
	}
	
	public PecaService(PecaDao pecaDao){
		this.dao = pecaDao;
		
	}
	
	public void add(Peca peca) throws PartsShopException {
		dao.add(peca);
	}
	
	public void remove(Peca peca) throws PartsShopException {
		dao.remove(peca);
	}

	public Peca findById(Long id){
		return dao.findById(id);
	}
	
	public Peca update(Peca peca){
		return dao.update(peca);
	}
	
	public List<Peca> findAll() {
		return dao.findAll();
	}
}