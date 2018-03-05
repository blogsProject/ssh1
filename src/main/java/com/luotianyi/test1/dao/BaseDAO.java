package com.luotianyi.test1.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

/**
 * @title: BaseDAO.java
 * @package com.shuyuntu.report.dao
 * @description: 数据交互接口
 * @copyright: shuyuntu.com
 * @author 毕泗平
 * @date 2017年3月20日 上午8:50:17
 * @version 1.0
 * @param T 实体泛型
 * @param PK 实体主键ID泛型
 */
public interface BaseDAO<Entiy, PK extends Serializable> {
	
	public Session currentSession();
	
	public PK save(Entiy entity);

	public Entiy findById(PK id);

	public boolean update(Entiy entity);

	public boolean remove(Entiy entity);

	public List<Entiy> findList();
}
