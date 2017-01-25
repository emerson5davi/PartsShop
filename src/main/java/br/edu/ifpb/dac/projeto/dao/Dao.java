package br.edu.ifpb.dac.projeto.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class Dao {

	static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("partsshop");
	}
	
	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void close() {
		if (emf.isOpen()) {
			emf.close();
		}
	}
}

