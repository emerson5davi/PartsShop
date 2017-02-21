package br.edu.ifpb.dac.projeto.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

public abstract class AbstractDao<T extends Serializable> extends Dao {

	private static final long serialVersionUID = 1L;

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	protected AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void add(T t) {
		EntityManager em = getEntityManager();
		try {
			em.persist(t);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
	}

	public T update(T t) {
		EntityManager em = getEntityManager();
		T resultado = t;
		try {
			resultado = em.merge(t);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return resultado;
	}

	public void remove(T t) {
		EntityManager em = getEntityManager();
		try {
			t = em.merge(t);
			em.remove(t);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
	}

	public T findById(Serializable tId) {
		EntityManager em = getEntityManager();
		T resultado = null;
		try {
			resultado = em.find(persistentClass, tId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}

		return resultado;
	}

	public List<T> findAll() {
		EntityManager em = getEntityManager();
		List<T> resultado = null;
		try {
			TypedQuery<T> query = em.createQuery(String.format("SELECT a FROM %s a", persistentClass.getName()),
					persistentClass);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return resultado;
	}

}
