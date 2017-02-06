package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.dac.projeto.dao.CompraDao;
import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class CompraService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private CompraDao dao = new CompraDao();

	public CompraService() {
	}
	
	public CompraService(CompraDao compraDao){
		this.dao = compraDao;
		
	}
	
	public void add(Compra compra) throws PartsShopException {
		dao.add(compra);
	}
	
	public void remove(Compra compra) throws PartsShopException {
		dao.remove(compra);
	}

	public Compra findById(Long id){
		return dao.findById(id);
	}
	
	public Compra update(Compra compra){
		return dao.update(compra);
	}
	
	public List<Compra> findAll() {
		return dao.findAll();
	}
	
	public Long getTotalCompras() {
		return dao.getTotalCompras();
	}
	
	public Long getCountComprasByCliente(Cliente cliente) {
		Long result = 0l;
		try {
			result = dao.getCountComprasByCliente(cliente);
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return result;
	}
}