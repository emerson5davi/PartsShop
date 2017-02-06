package br.edu.ifpb.dac.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.ItemCompra;
import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class ItemCompraDao extends Dao implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	public void add(ItemCompra itemCompra) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(itemCompra);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public ItemCompra update(ItemCompra itemCompra) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		ItemCompra resultado = itemCompra;
		try {
			resultado = em.merge(itemCompra);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void remove(ItemCompra itemCompra) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			itemCompra = em.find(ItemCompra.class, itemCompra.getId());
			em.remove(itemCompra);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public ItemCompra findById(Long id) {
		EntityManager em = getEntityManager();
		ItemCompra resultado = null;
		try {
			resultado = em.find(ItemCompra.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<ItemCompra> findAll() {
		EntityManager em = getEntityManager();
		List<ItemCompra> resultado = null;
		try {
			TypedQuery<ItemCompra> query = em.createQuery("SELECT i FROM ItemCompra i", ItemCompra.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalPorPeca() throws PartsShopException{
		EntityManager em = getEntityManager();
		List<Object[]> result = null;

		try {
			Query query = em.createNamedQuery("itemCompra.getTotalPorPeca");
			result =  query.getResultList();
		} catch (PersistenceException e) {
			throw new PartsShopException("Erro ao tentar recuperar o total de peças"+e.getMessage());
		} finally {
			em.close();
		}
		return result;
	}
	
	public List<ItemCompra> findByPeca(Peca peca) {
		EntityManager em = getEntityManager();
		TypedQuery<ItemCompra> query = em.createNamedQuery("itemCompra.findByPeca", ItemCompra.class);
		query.setParameter("peca", peca);
		List<ItemCompra> result = query.getResultList();
		em.close();
		return result;
	}
	
	public Long getQuantidadeByPeca(Peca peca) throws PartsShopException {
		EntityManager em = getEntityManager();
		Long result = 0l;
		try {
			Query query = em.createNamedQuery("itemPeca.getQuantidadeByPeca");
			query.setParameter("peca", peca);
			result = (Long) query.getSingleResult();
		}catch (NoResultException e1){
			return 0l;
		} catch (PersistenceException e) {
			throw new PartsShopException("Erro ao tentar obter a quantidade de itens de compra por peça"+e.getMessage());
		} finally {
			em.close();
		}
		return result;
	}
}