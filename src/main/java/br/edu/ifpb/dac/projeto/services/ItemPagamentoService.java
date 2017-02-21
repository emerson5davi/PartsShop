package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.dac.projeto.dao.ItemPagamentoDao;
import br.edu.ifpb.dac.projeto.entities.ItemPagamento;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.util.jpa.TransacionalCdi;

public class ItemPagamentoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ItemPagamentoDao dao;

	public ItemPagamentoService() {
	}
	
	@TransacionalCdi
	public void add(ItemPagamento itemPagamento) throws PartsShopException {
		dao.add(itemPagamento);
	}
	
	@TransacionalCdi
	public void remove(ItemPagamento itemPagamento) throws PartsShopException {
		dao.remove(itemPagamento);
	}

	@TransacionalCdi
	public ItemPagamento findById(Long id){
		return dao.findById(id);
	}
	
	@TransacionalCdi
	public ItemPagamento update(ItemPagamento itemPagamento){
		return dao.update(itemPagamento);
	}
	
	@TransacionalCdi
	public List<ItemPagamento> findAll() {
		return dao.findAll();
	}
	
}