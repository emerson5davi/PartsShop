package br.edu.ifpb.dac.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.projeto.entities.Endereco;

public class EnderecoDao extends Dao{

	private EntityManager em = getEntityManager();

	@SuppressWarnings("unchecked")
	public List<String> getCidades(Integer codUF) {
		Query createQuery;
        createQuery = em.createNativeQuery("SELECT c.nome FROM cidades c where c.estado = " + codUF);
        return createQuery.getResultList();
	}
	
	public List<Endereco> findByLogradouro(String logradouro) {
		TypedQuery<Endereco> query = em.createNamedQuery("Endereco.findByLogradouro", Endereco.class);
		query.setParameter("logradouro", "%" + logradouro + "%");

		return query.getResultList();
	}
}