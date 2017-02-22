package br.edu.ifpb.dac.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class CompraDao extends AbstractDao<Compra> {

	private static final long serialVersionUID = 1L;

	public Long getTotalCompras() {
		EntityManager em = getEntityManager();
		Query query = em.createNamedQuery("compra.getTotalCompras");
		Long result = (Long) query.getSingleResult();
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
		}
		return result;
	}
	
	public List<Compra> getComprasByCliente(Cliente cliente) throws PartsShopException {
		EntityManager em = getEntityManager();
		List<Compra> result = null;
		try {
			TypedQuery<Compra> query = em.createNamedQuery("compra.getComprasByCliente", Compra.class);
			query.setParameter("cliente", cliente);
			result = query.getResultList();
		} catch (PersistenceException e) {
			throw new PartsShopException("Ocorreu um erro ao tentar recuperar a lista de compras do cliente " + cliente.getNome());
		}
		return result;
	}
}