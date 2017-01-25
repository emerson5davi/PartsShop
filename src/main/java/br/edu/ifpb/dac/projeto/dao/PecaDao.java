package br.edu.ifpb.dac.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.Peca;

public class PecaDao extends Dao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public void add(Peca peca) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(peca);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Peca update(Peca peca) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Peca resultado = peca;
		try {
			resultado = em.merge(peca);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void remove(Peca peca) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			peca = em.find(Peca.class, peca.getId());
			em.remove(peca);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Peca findById(Long id) {
		EntityManager em = getEntityManager();
		Peca resultado = null;
		try {
			resultado = em.find(Peca.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Peca> findAll() {
		EntityManager em = getEntityManager();
		List<Peca> resultado = null;
		try {
			TypedQuery<Peca> query = em.createQuery("SELECT p FROM Peca p", Peca.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}
}