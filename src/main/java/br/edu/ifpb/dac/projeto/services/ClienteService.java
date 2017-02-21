package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.dac.projeto.dao.ClienteDao;
import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopExceptionHandler;
import br.edu.ifpb.dac.projeto.util.jpa.TransacionalCdi;

public class ClienteService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteDao dao;

	public ClienteService() {
	}
	
	@TransacionalCdi
	public void add(Cliente cliente) throws PartsShopException {
		Cliente c = dao.findByCPF(cliente.getCpf());
		
		if(c != null){
			throw new PartsShopExceptionHandler("Já existe um cliente com este CPF cadastrado");
		}
		
		for (Cliente cliente2: findAll()) {
			if(cliente2.getLogin().equals(cliente.getLogin())){
				throw new PartsShopExceptionHandler("Já existe um usuário com este Login cadastrado");
			}
		}
		
		dao.add(cliente);
	}
	
	@TransacionalCdi
	public void remove(Cliente cliente) throws PartsShopException {
		dao.remove(cliente);
	}

	@TransacionalCdi
	public Cliente findById(Long id){
		return dao.findById(id);
	}
	
	@TransacionalCdi
	public Cliente update(Cliente cliente){
		return dao.update(cliente);
	}
	
	@TransacionalCdi
	public List<Cliente> findAll() {
		return dao.findAll();
	}
	
	@TransacionalCdi
	public Long getTotalClientes() {
		Long result = 0l;
		try {
			result = dao.getTotalClientes();
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@TransacionalCdi
	public Cliente findByCPF(String cpf) {
		Cliente result = null;
		try {
			result = dao.findByCPF(cpf);
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return result;
	}

	@TransacionalCdi
	public List<Cliente> findByNome(String nome) {
		List<Cliente> results = null;
		try {
			results = dao.findByNome(nome);
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public void criptografarSenha(Cliente cliente) throws ServicePartsShopException {
		cliente.setSenha(criptografarSenha(cliente.getSenha()));
	}

	private static String criptografarSenha(String password) throws ServicePartsShopException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String output = bigInt.toString(16);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new ServicePartsShopException("Não foi possível criptografar a senha!");
		} catch (UnsupportedEncodingException e) {
			throw new ServicePartsShopException("Não foi possível criptografar a senha!");
		}
	}
}