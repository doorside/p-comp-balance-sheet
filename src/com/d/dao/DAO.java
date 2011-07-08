package com.d.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.d.util.PMF;

public class DAO {

	public <E> List<E> selectAll(Class<E> clazz) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(clazz);
		return (List<E>) query.execute();
	}

	public <E> E select(Class<E> clazz) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(clazz);
		return (E) query.execute();
	}

	public <E> E select(Class<E> clazz, Long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return pm.getObjectById(clazz, id);
	}

	public void save(Object obj) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(obj);
		} finally {
			pm.close();
		}
	}

	public <E> void delete(Class<E> clazz, Long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			E obj = pm.getObjectById(clazz, id);
			pm.deletePersistent(obj);
		} finally {
			pm.close();
		}

	}
}
