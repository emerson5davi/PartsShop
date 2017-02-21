package br.edu.ifpb.dac.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.Funcionario;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class FuncionarioDao extends AbstractDao<Funcionario> {
	
	private static final long serialVersionUID = 1L;
	
	public Funcionario findByCPF(String cpf) throws PartsShopException {
		EntityManager em = getEntityManager();
		Funcionario result = null;
		try {
			TypedQuery<Funcionario> query = em.createNamedQuery("Funcionario.findByCPF", Funcionario.class);
			query.setParameter("cpf", cpf);
			result = query.getSingleResult();
		} catch (NoResultException e){
			return null;
		} catch (PersistenceException e) {
			throw new PartsShopException("erro ao tentar recuperar funcionário pelo CPF "+ e.getMessage());
		}
		return result;
	}
	
	public List<Funcionario> findByNome(String nome) throws PartsShopException {
		EntityManager em = getEntityManager();
		List<Funcionario> result = null;
		try {
			TypedQuery<Funcionario> query = em.createNamedQuery("Funcionario.findByNome", Funcionario.class);
			query.setParameter("nome", "%" + nome.toLowerCase() + "%");
			result = query.getResultList();
		} catch (PersistenceException e) {
			throw new PartsShopException("Erro ao tentar cosultar funcionário pelo nome "+ e.getMessage());
		}
		return result;
	}

	public Long getTotalFuncionarios() throws PartsShopException {
		EntityManager em = getEntityManager();
		Long result = 0l;
		try {
			Query query = em.createNamedQuery("Funcionario.getTotalFuncionarios");
			result = (Long) query.getSingleResult();
		} catch (PersistenceException e) {
			throw new PartsShopException("Erro ao tentar cosultar o total de funcionários "+ e.getMessage());
		}
		return result;
	}
}