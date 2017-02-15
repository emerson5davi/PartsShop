package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.dac.projeto.dao.ItemPagamentoDao;
import br.edu.ifpb.dac.projeto.entities.ItemPagamento;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class ItemPagamentoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ItemPagamentoDao dao = new ItemPagamentoDao();

	public ItemPagamentoService() {
	}
	
	public ItemPagamentoService(ItemPagamentoDao itemPagamentoDao){
		this.dao = itemPagamentoDao;
		
	}
	
	public void add(ItemPagamento itemPagamento) throws PartsShopException {
		dao.add(itemPagamento);
	}
	
	public void remove(ItemPagamento itemPagamento) throws PartsShopException {
		dao.remove(itemPagamento);
	}

	public ItemPagamento findById(Long id){
		return dao.findById(id);
	}
	
	public ItemPagamento update(ItemPagamento itemPagamento){
		return dao.update(itemPagamento);
	}
	
	public List<ItemPagamento> findAll() {
		return dao.findAll();
	}
	
}