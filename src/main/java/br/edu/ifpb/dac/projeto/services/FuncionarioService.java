package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.dac.projeto.dao.FuncionarioDao;
import br.edu.ifpb.dac.projeto.entities.Funcionario;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopExceptionHandler;
import br.edu.ifpb.dac.projeto.util.jpa.TransacionalCdi;

public class FuncionarioService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FuncionarioDao dao;

	public FuncionarioService() {
	}
	
	@TransacionalCdi
	public void add(Funcionario funcionario) throws PartsShopException {
		Funcionario f = dao.findByCPF(funcionario.getCpf());
		
		if(f != null){
			throw new PartsShopExceptionHandler("Já existe um funcionário com este CPF cadastrado");
		}

		for (Funcionario funcionario2 : findAll()) {
			if(funcionario2.getLogin().equals(funcionario.getLogin())){
				throw new PartsShopExceptionHandler("Já existe um funcionário com este Login cadastrado");
			}
			if(funcionario2.getCtps().equals(funcionario.getCtps())){
				throw new PartsShopExceptionHandler("Já existe um funcionário com este CTPS cadastrado");
			}
			if(funcionario2.getRg().equals(funcionario.getRg())){
				throw new PartsShopExceptionHandler("Já existe um funcionário com este RG cadastrado");
			}
		}
		
		dao.add(funcionario);
	}
	
	@TransacionalCdi
	public void remove(Funcionario funcionario) throws PartsShopException {
		dao.remove(funcionario);
	}

	@TransacionalCdi
	public Funcionario findById(Long id){
		return dao.findById(id);
	}
	
	@TransacionalCdi
	public Funcionario update(Funcionario funcionario){
		return dao.update(funcionario);
	}
	
	@TransacionalCdi
	public List<Funcionario> findAll() {
		return dao.findAll();
	}
	
	@TransacionalCdi
	public Long getTotalFuncionarios() {
		Long result = 0l;
		try {
			result = dao.getTotalFuncionarios();
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@TransacionalCdi
	public Funcionario findByCPF(String cpf) {
		Funcionario result = null;
		try {
			result = dao.findByCPF(cpf);
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return result;
	}

	@TransacionalCdi
	public List<Funcionario> findByNome(String nome) {
		List<Funcionario> results = null;
		try {
			results = dao.findByNome(nome);
		} catch (PartsShopException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public void criptografarSenha(Funcionario funcionario) throws ServicePartsShopException {
		funcionario.setSenha(criptografarSenha(funcionario.getSenha()));
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