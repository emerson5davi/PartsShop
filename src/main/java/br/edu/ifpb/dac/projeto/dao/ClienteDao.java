package br.edu.ifpb.dac.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class ClienteDao extends AbstractDao<Cliente>{
	
	private static final long serialVersionUID = 1L;

	public Cliente findByCPF(String cpf) throws PartsShopException {
		EntityManager em = getEntityManager();
		Cliente result = null;
		try {
			TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findByCPF", Cliente.class);
			query.setParameter("cpf", cpf);
			result = query.getSingleResult();
		} catch (NoResultException e){
			return null;
		} catch (PersistenceException e) {
			throw new PartsShopException("erro ao tentar recuperar cliente pelo CPF "+ e.getMessage());
		}
		return result;
	}
	
	public List<Cliente> findByNome(String nome) throws PartsShopException {
		EntityManager em = getEntityManager();
		List<Cliente> result = null;
		try {
			TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findByNome", Cliente.class);
			query.setParameter("nome", "%" + nome.toLowerCase() + "%");
			result = query.getResultList();
		} catch (PersistenceException e) {
			throw new PartsShopException("Erro ao tentar cosultar cliente pelo nome "+ e.getMessage());
		}
		return result;
	}

	public Long getTotalClientes() throws PartsShopException {
		EntityManager em = getEntityManager();
		Long result = 0l;
		try {
			Query query = em.createNamedQuery("Cliente.getTotalClientes");
			result = (Long) query.getSingleResult();
		} catch (PersistenceException e) {
			throw new PartsShopException("Erro ao tentar cosultar o total de clientes "+ e.getMessage());
		}
		return result;
	}
}