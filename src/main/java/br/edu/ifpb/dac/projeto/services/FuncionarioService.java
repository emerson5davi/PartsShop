package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.dac.projeto.dao.FuncionarioDao;
import br.edu.ifpb.dac.projeto.entities.Funcionario;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopExceptionHandler;

public class FuncionarioService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private FuncionarioDao dao = new FuncionarioDao();

	public FuncionarioService() {
	}
	
	public FuncionarioService(FuncionarioDao funcionarioDao){
		this.dao = funcionarioDao;
		
	}
	
	public void add(Funcionario funcionario) throws PartsShopException {
		FuncionarioDao funcionarioDao = (FuncionarioDao) this.dao;
		Funcionario f = funcionarioDao.findByCPF(funcionario.getCpf());
		if(f != null){
			throw new PartsShopExceptionHandler("Já existe um funcionário com este CPF cadastrado");
		}
		dao.add(funcionario);
	}
	
	public void remove(Funcionario funcionario) throws PartsShopException {
		dao.remove(funcionario);
	}

	public Funcionario findById(Long id){
		return dao.findById(id);
	}
	
	public Funcionario update(Funcionario funcionario){
		return dao.update(funcionario);
	}
	
	public List<Funcionario> findAll() {
		return dao.findAll();
	}
}