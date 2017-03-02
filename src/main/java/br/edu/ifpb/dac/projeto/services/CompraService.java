package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.dac.projeto.dao.CompraDao;
import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.enumerations.Payment;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopExceptionHandler;
import br.edu.ifpb.dac.projeto.exceptions.PersistencePartsShopException;
import br.edu.ifpb.dac.projeto.util.jpa.TransacionalCdi;

public class CompraService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CompraDao dao;

	public CompraService() {
	}
	
	@TransacionalCdi
	public void add(Compra compra) throws PartsShopException {
		if(compra.getPagamento().getPayment() == Payment.A_PRAZO){
			dao.add(compra);
		}
		else{
			compra.getPagamento().setValorPago(compra.getPagamento().getValorTotal());
			compra.getPagamento().setDataPagamento(compra.getData());
			dao.add(compra);
		}
	}
	
	@TransacionalCdi
	public void remove(Compra compra) throws PartsShopException {
		if(compra.getPagamento().getDataPagamento() == null){
			throw new PartsShopExceptionHandler("Não pode remover compra com pagamento pendente");
		}
		dao.remove(compra);
	}

	public Compra findById(Long id){
		return dao.findById(id);
	}
	
	@TransacionalCdi
	public Compra update(Compra compra){
		if(compra.getPagamento().getValorPago().compareTo(compra.getPagamento().getValorTotal()) == 0){
			compra.getPagamento().setDataPagamento(compra.getPagamento().getItensPagamento().get(compra.getPagamento().getItensPagamento().size()-1).getDataPagamento());
		}
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
	
	public List<Compra> getComprasByCliente(Cliente cliente) {
		List<Compra> result = null;
		try {
			result = dao.getComprasByCliente(cliente);
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Compra getCompraComPagamentos(Long id){
		Compra result = null;
		try{
			result = dao.getCompraComPagamentos(id);
		} catch(PersistencePartsShopException e){
			e.printStackTrace();
		}
		return result;
	}
}