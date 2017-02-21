package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.dac.projeto.dao.PagamentoDao;
import br.edu.ifpb.dac.projeto.entities.Pagamento;
import br.edu.ifpb.dac.projeto.enumerations.Payment;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.util.jpa.TransacionalCdi;

public class PagamentoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PagamentoDao dao;
	
	public static List<Payment> payments = new ArrayList<Payment>();

	public PagamentoService() {
		payments = Arrays.asList(Payment.values());
	}
	
	@TransacionalCdi
	public void add(Pagamento pagamento) throws PartsShopException {
		dao.add(pagamento);
	}
	
	@TransacionalCdi
	public void remove(Pagamento pagamento) throws PartsShopException {
		dao.remove(pagamento);
	}

	@TransacionalCdi
	public Pagamento findById(Long id){
		return dao.findById(id);
	}
	
	@TransacionalCdi
	public Pagamento update(Pagamento pagamento){
		return dao.update(pagamento);
	}
	
	@TransacionalCdi
	public List<Pagamento> findAll() {
		return dao.findAll();
	}
}