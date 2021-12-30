package com.crud.demo.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class dbConfig {
	public EntityManager getEntity(String t) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(t);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		return em;
	}
}
