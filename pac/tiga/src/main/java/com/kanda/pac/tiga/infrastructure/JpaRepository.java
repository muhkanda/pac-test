package com.kanda.pac.tiga.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;
import java.util.List;

public abstract class JpaRepository<E, K> {

	private final Class<E> entityClass;

	public JpaRepository(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public E find(K id) {
		E data = null;
		if (id != null) {
			data = getEntityManager().find(entityClass, id);
		}
		return data;
	}

	public E findAndLock(K id) {
		E data = null;
		if (id != null) {
			data = getEntityManager().find(entityClass, id, LockModeType.PESSIMISTIC_WRITE);
		}
		return data;
	}

	public List<E> findAll() {
		return findAll(null, null);
	}

	public List<E> findAll(Integer firstResult, Integer maxResult) {
		TypedQuery<E> queryFind = getEntityManager().createQuery(
				"SELECT e FROM " + getEntityName() + " e", entityClass);
		if (firstResult != null) {
			queryFind.setFirstResult(firstResult);
		}
		if (maxResult != null) {
			queryFind.setMaxResults(maxResult);
		}
		List<E> result = queryFind.getResultList();
		return result;
	}

	public abstract EntityManager getEntityManager();

	protected String getEntityName() {
		return entityClass.getSimpleName();
	}

	public void store(E entity) {
		getEntityManager().persist(entity);
	}

	public E update(E entity) {
		return getEntityManager().merge(entity);
	}

	public void delete(E entity) {
		getEntityManager().remove(entity);
	}
}
