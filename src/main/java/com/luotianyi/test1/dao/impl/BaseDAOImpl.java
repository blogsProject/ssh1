package com.luotianyi.test1.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title: BaseDAOImpl.java
 * @package com.shuyuntu.report.dao.impl
 * @description: TODO
 * @copyright: shuyuntu.com
 * @author 毕泗平
 * @date 2017年3月20日 上午8:57:01
 * @version 1.0
 */
public class BaseDAOImpl<Entiy, PK extends Serializable> {
	@SuppressWarnings("unchecked")
	Class<Entiy> entiyClass = (Class<Entiy>) ((ParameterizedType) getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];

	@Autowired
	private SessionFactory sessionFactory;

	public Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public PK save(Entiy entity) {
		return (PK) currentSession().save(entity);
	}

	public Entiy findById(PK id) {
		return currentSession().get(entiyClass, id);
	}

	public boolean update(Entiy entity) {
		currentSession().update(entity);
		return true;
	}

	public boolean remove(Entiy entity) {
		currentSession().delete(entity);
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Entiy> findList() {
		String hql = "from " + entiyClass.getName() + "entiyt";
		Query query = currentSession().createQuery(hql);
		return query.list();
	}
}
