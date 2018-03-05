package com.luotianyi.test1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luotianyi.test1.pojo.User;
import com.luotianyi.test1.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/save")
	@ResponseBody
	public Integer save() {
		User user = new User();
		user.setUername("luotianyi");
		user.setPassword("123456");
		return userService.save(user);
	}
}
