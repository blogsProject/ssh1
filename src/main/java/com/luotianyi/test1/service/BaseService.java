package com.luotianyi.test1.service;

import java.io.Serializable;
import java.util.List;

/**
 * @title: BaseService.java
 * @package com.shuyuntu.report.service
 * @description: TODO
 * @copyright: shuyuntu.com 
 * @author 毕泗平
 * @date 2017年3月20日 上午9:30:49
 * @version 1.0
 * @param T 实体泛型
 * @param PK 实体主键ID泛型
 */
public interface BaseService<Entiy, PK extends Serializable> {

	PK save(Entiy entity);

	Entiy findById(PK id);

	boolean update(Entiy entity);

	boolean remove(Entiy entity);

	List<Entiy> findList();
}
