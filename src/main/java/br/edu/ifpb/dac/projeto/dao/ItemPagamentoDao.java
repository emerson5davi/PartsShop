package br.edu.ifpb.dac.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.ItemPagamento;

public class ItemPagamentoDao extends Dao implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	public void add(ItemPagamento itemPagamento) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(itemPagamento);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public ItemPagamento update(ItemPagamento itemPagamento) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		ItemPagamento resultado = itemPagamento;
		try {
			resultado = em.merge(itemPagamento);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void remove(ItemPagamento itemPagamento) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			itemPagamento = em.find(ItemPagamento.class, itemPagamento.getId());
			em.remove(itemPagamento);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public ItemPagamento findById(Long id) {
		EntityManager em = getEntityManager();
		ItemPagamento resultado = null;
		try {
			resultado = em.find(ItemPagamento.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<ItemPagamento> findAll() {
		EntityManager em = getEntityManager();
		List<ItemPagamento> resultado = null;
		try {
			TypedQuery<ItemPagamento> query = em.createQuery("SELECT i FROM TB_ITEM_PAGAMENTO i", ItemPagamento.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}
	
}