package br.edu.ifpb.dac.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class CompraDao extends Dao implements Serializable {

	private static final long serialVersionUID = 1L;

	public void add(Compra compra) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(compra);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Compra update(Compra compra) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Compra resultado = compra;
		try {
			resultado = em.merge(compra);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void remove(Compra compra) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			compra = em.find(Compra.class, compra.getId());
			em.remove(compra);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Compra findById(Long id) {
		EntityManager em = getEntityManager();
		Compra resultado = null;
		try {
			resultado = em.find(Compra.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Compra> findAll() {
		EntityManager em = getEntityManager();
		List<Compra> resultado = null;
		try {
			TypedQuery<Compra> query = em.createQuery("SELECT c FROM Compra c", Compra.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

	public Long getTotalCompras() {
		EntityManager em = getEntityManager();
		Query query = em.createNamedQuery("compra.getTotalCompras");
		Long result = (Long) query.getSingleResult();
		em.close();
		return result;
	}

	public Long getCountComprasByCliente(Cliente cliente) throws PartsShopException {
		Long result = 0l;
		EntityManager em = getEntityManager();
		try {
			Query query = em.createNamedQuery("compra.getCountByCliente");
			query.setParameter("cliente", cliente);
			result = (Long) query.getSingleResult();
		} catch (PersistenceException e) {
			throw new PartsShopException(
					"Ocorreu um erro ao tentar consultar a quantidade de compras por cliente." + e.getMessage());
		} finally {
			em.close();
		}
		return result;
	}
}