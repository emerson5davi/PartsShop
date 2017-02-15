package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.ifpb.dac.projeto.dao.PagamentoDao;
import br.edu.ifpb.dac.projeto.entities.Pagamento;
import br.edu.ifpb.dac.projeto.enumerations.Payment;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class PagamentoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private PagamentoDao dao = new PagamentoDao();
	public static List<Payment> payments = new ArrayList<Payment>();

	public PagamentoService() {
		payments = Arrays.asList(Payment.values());
	}
	
	public PagamentoService(PagamentoDao dao){
		this.dao = dao;
		
	}
	
	public void add(Pagamento pagamento) throws PartsShopException {
		dao.add(pagamento);
	}
	
	public void remove(Pagamento pagamento) throws PartsShopException {
		dao.remove(pagamento);
	}

	public Pagamento findById(Long id){
		return dao.findById(id);
	}
	
	public Pagamento update(Pagamento pagamento){
		return dao.update(pagamento);
	}
	
	public List<Pagamento> findAll() {
		return dao.findAll();
	}
}