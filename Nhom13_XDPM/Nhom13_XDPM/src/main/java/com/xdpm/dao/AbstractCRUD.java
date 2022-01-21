package com.xdpm.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class AbstractCRUD<T> {
	protected EntityManager entityManager;
	
	public AbstractCRUD() {
		this.entityManager = MyEntityManager.getInstance().getEntityManager();
	}
	
	public T add(T t) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(t);
			entityManager.flush();
			transaction.commit();
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}
	
	public T update(T t) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(t);
			transaction.commit();
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}
	
	public boolean delete(T t) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.remove(t);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			return false;
		}
	}
}
