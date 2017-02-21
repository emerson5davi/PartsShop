package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.dac.projeto.dao.DividaDao;
import br.edu.ifpb.dac.projeto.entities.Divida;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopExceptionHandler;
import br.edu.ifpb.dac.projeto.util.jpa.TransacionalCdi;

public class DividaService implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private DividaDao dao;
	
	@TransacionalCdi
	public void add(Divida divida) throws PartsShopException{
		Divida d = dao.findByCodBarra(divida.getCodBarra());
		
		if(d != null){
			throw new PartsShopExceptionHandler("Já existe um gasto com este CÓDIGO cadastrado");
		}
		
		dao.add(divida);
		
	}
	
	@TransacionalCdi
	public void remove(Divida divida) throws PartsShopException {
		dao.remove(divida);
	}

	@TransacionalCdi
	public Divida findById(Long id){
		return dao.findById(id);
	}
	
	@TransacionalCdi
	public Divida update(Divida divida){
		return dao.update(divida);
	}
	
	@TransacionalCdi
	public List<Divida> findAll() {
		return dao.findAll();
	}
	
	@TransacionalCdi
	public List<String> findNomeEmpresas(String nomeEmpresa) {
		return dao.findNomeEmpresas(nomeEmpresa);
	}
}