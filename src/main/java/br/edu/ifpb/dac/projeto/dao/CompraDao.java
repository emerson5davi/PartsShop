package br.edu.ifpb.dac.projeto.dao;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.exceptions.PersistencePartsShopException;

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
	
	public Compra getCompraComPagamentos(Long id) throws PersistencePartsShopException{
		EntityManager em = getEntityManager();
		Compra resultado = null;
		try {
			
			Query query = em.createQuery("SELECT c FROM Compra c WHERE c.id = :id");
			query.setParameter("id", id);
			
			@SuppressWarnings("unchecked")
			EntityGraph<Compra> graph = (EntityGraph<Compra>) em.getEntityGraph("graph.Compra.comItensDePagamento");
			query.setHint("javax.persistence.loadgraph", graph);

			resultado = (Compra) query.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistencePartsShopException("Ocorreu algum erro ao tentar recuperar todos os professores carregando todos os dados.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}
}