package com.xdpm.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class MyEntityManager {
	
	private static MyEntityManager instance = null;
	private EntityManager entityManager;
	
	private MyEntityManager() {
		entityManager = Persistence.createEntityManagerFactory("Nhom13_XDPM").createEntityManager();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public synchronized static MyEntityManager getInstance() {
		if (instance == null) 
			instance = new MyEntityManager();
		return instance;
	}
}
