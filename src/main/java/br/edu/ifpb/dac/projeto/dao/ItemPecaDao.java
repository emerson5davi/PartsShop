package br.edu.ifpb.dac.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.ItemPeca;

public class ItemPecaDao extends Dao implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	public void add(ItemPeca itemPeca) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(itemPeca);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public ItemPeca update(ItemPeca itemPeca) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		ItemPeca resultado = itemPeca;
		try {
			resultado = em.merge(itemPeca);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void remove(ItemPeca itemPeca) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			itemPeca = em.find(ItemPeca.class, itemPeca.getId());
			em.remove(itemPeca);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public ItemPeca findById(Long id) {
		EntityManager em = getEntityManager();
		ItemPeca resultado = null;
		try {
			resultado = em.find(ItemPeca.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<ItemPeca> findAll() {
		EntityManager em = getEntityManager();
		List<ItemPeca> resultado = null;
		try {
			TypedQuery<ItemPeca> query = em.createQuery("SELECT i FROM ItemPeca i", ItemPeca.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}
}