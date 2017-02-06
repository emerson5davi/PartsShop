package br.edu.ifpb.dac.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class ClienteDao extends Dao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public void add(Cliente cliente) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(cliente);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Cliente update(Cliente cliente) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Cliente resultado = cliente;
		try {
			resultado = em.merge(cliente);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void remove(Cliente cliente) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			cliente = em.find(Cliente.class, cliente.getId());
			em.remove(cliente);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Cliente findById(Long id) {
		EntityManager em = getEntityManager();
		Cliente resultado = null;
		try {
			resultado = em.find(Cliente.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Cliente> findAll() {
		EntityManager em = getEntityManager();
		List<Cliente> resultado = null;
		try {
			TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}
	
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
		} finally {
			em.close();
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
		} finally {
			em.close();
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
		} finally {
			em.close();
		}
		return result;
	}

}