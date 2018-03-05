package com.luotianyi.test1.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luotianyi.test1.pojo.User;
import com.luotianyi.test1.service.UserService;

/**
 * @title: UserServiceImpl.java
 * @package com.shuyuntu.report.service.impl
 * @description: TODO
 * @copyright: shuyuntu.com
 * @author 毕泗平
 * @date 2017年3月20日 下午8:38:20
 * @version 1.0
 */
@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

}
