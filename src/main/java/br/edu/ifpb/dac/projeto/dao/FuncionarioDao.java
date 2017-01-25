package br.edu.ifpb.dac.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.Funcionario;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class FuncionarioDao extends Dao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public void add(Funcionario funcionario) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(funcionario);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Funcionario update(Funcionario funcionario) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Funcionario resultado = funcionario;
		try {
			resultado = em.merge(funcionario);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void remove(Funcionario funcionario) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			funcionario = em.find(Funcionario.class, funcionario.getId());
			em.remove(funcionario);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Funcionario findById(Long id) {
		EntityManager em = getEntityManager();
		Funcionario resultado = null;
		try {
			resultado = em.find(Funcionario.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Funcionario> findAll() {
		EntityManager em = getEntityManager();
		List<Funcionario> resultado = null;
		try {
			TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f", Funcionario.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}
	
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
}