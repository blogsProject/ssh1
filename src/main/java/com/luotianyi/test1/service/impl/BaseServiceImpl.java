package com.luotianyi.test1.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.luotianyi.test1.dao.BaseDAO;

/**
 * @title: BaseServiceImpl.java
 * @package com.shuyuntu.report.service.impl
 * @description: @Transactional(propagation=Propagation.NOT_SUPPORTED)
 *               ：容器不为这个方法开启事务
 * @copyright: shuyuntu.com
 * @author 毕泗平
 * @date 2017年3月20日 上午9:32:29
 * @version 1.0
 * @param T
 *            实体泛型
 * @param PK
 *            实体主键ID泛型
 */
@Transactional
public class BaseServiceImpl<Entiy, PK extends Serializable> {

	@Autowired
	private BaseDAO<Entiy, PK> baseDAO;

	public PK save(Entiy entity) {
		return baseDAO.save(entity);
	}

	public boolean update(Entiy entity) {
		return baseDAO.update(entity);
	}

	public boolean remove(Entiy entity) {
		return baseDAO.remove(entity);
	}

	public List<Entiy> findList() {
		return baseDAO.findList();
	}

	public Entiy findById(PK id) {
		return baseDAO.findById(id);
	}
}
