package br.edu.ifpb.dac.projeto.dao;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.Pagamento;

public class PagamentoDao extends Dao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public void add(Pagamento cliente) {
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

	public Pagamento update(Pagamento pagamento) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Pagamento resultado = pagamento;
		try {
			resultado = em.merge(pagamento);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void remove(Pagamento pagamento) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			pagamento = em.find(Pagamento.class, pagamento.getId());
			em.remove(pagamento);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Pagamento findById(Long id) {
		EntityManager em = getEntityManager();
		Pagamento resultado = null;
		try {
			resultado = em.find(Pagamento.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Pagamento> findAll() {
		EntityManager em = getEntityManager();
		List<Pagamento> resultado = null;
		try {
			TypedQuery<Pagamento> query = em.createQuery("SELECT p FROM TB_PAGAMENTO p", Pagamento.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}
}