package com.luotianyi.test1.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.luotianyi.test1.config.RootConfig;
import com.luotianyi.test1.pojo.User;
import com.luotianyi.test1.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = RootConfig.class)
public class ServiceTest {

	@Autowired
	UserService userService;
	@Test
	public void test() {
		User user = new User();
		user.setId(0);
		user.setUername("luotianyi");
		user.setPassword("123456");
		userService.save(user);
	}

}
