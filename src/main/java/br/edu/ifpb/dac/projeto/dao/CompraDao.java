package br.edu.ifpb.dac.projeto.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

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
}