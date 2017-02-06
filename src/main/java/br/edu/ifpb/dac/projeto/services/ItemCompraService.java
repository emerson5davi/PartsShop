package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.dac.projeto.dao.ItemCompraDao;
import br.edu.ifpb.dac.projeto.entities.ItemCompra;
import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class ItemCompraService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ItemCompraDao dao = new ItemCompraDao();

	public ItemCompraService() {
	}
	
	public ItemCompraService(ItemCompraDao itemPecaDao){
		this.dao = itemPecaDao;
		
	}
	
	public void add(ItemCompra itemPeca) throws PartsShopException {
		dao.add(itemPeca);
	}
	
	public void remove(ItemCompra itemPeca) throws PartsShopException {
		dao.remove(itemPeca);
	}

	public ItemCompra findById(Long id){
		return dao.findById(id);
	}
	
	public ItemCompra update(ItemCompra itemPeca){
		return dao.update(itemPeca);
	}
	
	public List<ItemCompra> findAll() {
		return dao.findAll();
	}
	
	public List<ItemCompra> findByPeca(Peca peca) {
		return dao.findByPeca(peca);
	}
	
	public List<Object[]> getTotalPorPeca(){
		List<Object[]> result = null;
		try {
			result = dao.getTotalPorPeca();
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return result;
	}
	
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