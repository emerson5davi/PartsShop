package br.edu.ifpb.dac.projeto.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class Dao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}
	
}