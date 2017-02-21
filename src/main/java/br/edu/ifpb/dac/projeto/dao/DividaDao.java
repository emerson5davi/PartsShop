package br.edu.ifpb.dac.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.Divida;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class DividaDao extends AbstractDao<Divida>{

	private static final long serialVersionUID = 1L;
	
	public Divida findByCodBarra(String codBarra) throws PartsShopException {
		EntityManager em = getEntityManager();
		Divida result = null;
		try {
			TypedQuery<Divida> query = em.createNamedQuery("Divida.findByCodBarra", Divida.class);
			query.setParameter("codBarra", codBarra);
			result = query.getSingleResult();
		} catch (NoResultException e){
			return null;
		} catch (PersistenceException e) {
			throw new PartsShopException("erro ao tentar recuperar a dívida pelo cód. de barra "+ e.getMessage());
		}
		return result;
	}
	
	public List<String> findNomeEmpresas(String nomeEmpresa) {
		EntityManager em = getEntityManager();
		List<String> resultado = null;
		try {
			TypedQuery<String> query = em.createQuery("Divida.findNomeEmpresas", String.class);
			query.setParameter("nomeEmpresa", nomeEmpresa);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return resultado;
	}
	
	public List<String> findByAddress(String nomeEmpresa) {
		EntityManager em = getEntityManager();
		TypedQuery<String> query = em.createNamedQuery("Divida.findNomeEmpresas", String.class);
		query.setParameter("nomeEmpresa", "%" + nomeEmpresa + "%");

		return query.getResultList();
	}

}
