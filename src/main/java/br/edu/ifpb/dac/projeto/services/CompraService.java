package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.dac.projeto.dao.CompraDao;
import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.util.jpa.TransacionalCdi;

public class CompraService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CompraDao dao;

	public CompraService() {
	}
	
	@TransacionalCdi
	public void add(Compra compra) throws PartsShopException {
		if(compra.getItensCompra().isEmpty()){
			throw new PartsShopException("deu merda");
		}
		dao.add(compra);
	}
	
	@TransacionalCdi
	public void remove(Compra compra) throws PartsShopException {
		dao.remove(compra);
	}

	@TransacionalCdi
	public Compra findById(Long id){
		return dao.findById(id);
	}
	
	@TransacionalCdi
	public Compra update(Compra compra){
		return dao.update(compra);
	}
	
	@TransacionalCdi
	public List<Compra> findAll() {
		return dao.findAll();
	}
	
	@TransacionalCdi
	public Long getTotalCompras() {
		return dao.getTotalCompras();
	}
	
	@TransacionalCdi
	public Long getCountComprasByCliente(Cliente cliente) {
		Long result = 0l;
		try {
			result = dao.getCountComprasByCliente(cliente);
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@TransacionalCdi
	public List<Compra> getComprasByCliente(Cliente cliente) {
		List<Compra> result = null;
		try {
			result = dao.getComprasByCliente(cliente);
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}