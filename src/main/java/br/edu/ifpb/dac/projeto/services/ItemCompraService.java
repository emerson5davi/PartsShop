package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.dac.projeto.dao.ItemCompraDao;
import br.edu.ifpb.dac.projeto.entities.ItemCompra;
import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.util.jpa.TransacionalCdi;

public class ItemCompraService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ItemCompraDao dao;

	public ItemCompraService() {
	}

	@TransacionalCdi
	public void add(ItemCompra itemPeca) throws PartsShopException {
		dao.add(itemPeca);
	}
	
	@TransacionalCdi
	public void remove(ItemCompra itemPeca) throws PartsShopException {
		dao.remove(itemPeca);
	}

	@TransacionalCdi
	public ItemCompra findById(Long id){
		return dao.findById(id);
	}
	
	@TransacionalCdi
	public ItemCompra update(ItemCompra itemPeca){
		return dao.update(itemPeca);
	}
	
	@TransacionalCdi
	public List<ItemCompra> findAll() {
		return dao.findAll();
	}
	
	@TransacionalCdi
	public List<ItemCompra> findByPeca(Peca peca) {
		return dao.findByPeca(peca);
	}
	
	@TransacionalCdi
	public List<Object[]> getTotalPorPeca(){
		List<Object[]> result = null;
		try {
			result = dao.getTotalPorPeca();
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@TransacionalCdi
	public Long getQuantidadeByPeca(Peca peca) {
		Long result = 0l;
		try {
			result = dao.getQuantidadeByPeca(peca);
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return result;
	}
}