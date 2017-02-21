package br.edu.ifpb.dac.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.ItemCompra;
import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;

public class ItemCompraDao extends AbstractDao<ItemCompra> {
	
private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalPorPeca() throws PartsShopException{
		EntityManager em = getEntityManager();
		List<Object[]> result = null;

		try {
			Query query = em.createNamedQuery("itemCompra.getTotalPorPeca");
			result =  query.getResultList();
		} catch (PersistenceException e) {
			throw new PartsShopException("Erro ao tentar recuperar o total de peças"+e.getMessage());
		}
		return result;
	}
	
	public List<ItemCompra> findByPeca(Peca peca) {
		EntityManager em = getEntityManager();
		TypedQuery<ItemCompra> query = em.createNamedQuery("itemCompra.findByPeca", ItemCompra.class);
		query.setParameter("peca", peca);
		List<ItemCompra> result = query.getResultList();
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
		}
		return result;
	}
}