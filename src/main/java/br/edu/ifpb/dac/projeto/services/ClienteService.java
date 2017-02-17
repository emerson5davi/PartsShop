package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.dac.projeto.dao.ClienteDao;
import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopExceptionHandler;

public class ClienteService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ClienteDao dao = new ClienteDao();

	public ClienteService() {
	}
	
	public ClienteService(ClienteDao clienteDao){
		this.dao = clienteDao;
		
	}
	
	public void add(Cliente cliente) throws PartsShopException {
		Cliente c = dao.findByCPF(cliente.getCpf());
		if(c != null){
			throw new PartsShopExceptionHandler("Já existe um cliente com este CPF cadastrado");
		}
		dao.add(cliente);
	}
	
	public void remove(Cliente cliente) throws PartsShopException {
		dao.remove(cliente);
	}

	public Cliente findById(Long id){
		return dao.findById(id);
	}
	
	public Cliente update(Cliente cliente){
		return dao.update(cliente);
	}
	
	public List<Cliente> findAll() {
		return dao.findAll();
	}
	
	public Long getTotalClientes() {
		Long result = 0l;
		try {
			result = dao.getTotalClientes();
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Cliente findByCPF(String cpf) {
		Cliente result = null;
		try {
			result = dao.findByCPF(cpf);
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Cliente> findByNome(String nome) {
		List<Cliente> results = null;
		try {
			results = dao.findByNome(nome);
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return results;
	}
}