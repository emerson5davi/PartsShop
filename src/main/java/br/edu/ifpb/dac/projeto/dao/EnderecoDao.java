package br.edu.ifpb.dac.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EnderecoDao extends Dao{

	private EntityManager em = getEntityManager();

	@SuppressWarnings("unchecked")
	public List<String> getCidades(Integer codUF) {
		Query createQuery;
        createQuery = em.createNativeQuery("SELECT c.nome FROM cidades c where c.estado = " + codUF);
        return createQuery.getResultList();
	}
}