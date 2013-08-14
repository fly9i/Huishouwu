package com.huishouwu.controller;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishouwu.dao.UserDao;
import com.huishouwu.pojo.User;

@Controller
public class UserController {
	public static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	private UserDao userDao;

	@Resource
	private void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@RequestMapping("user/add")
	@ResponseBody
	public String addUser(@RequestParam("username") String name,
			@RequestParam("email") String email,
			@RequestParam("mobile") String mobile,
			@RequestParam("password1") String pass) {
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setMobile(mobile);

		user.setPass(pass);

		user.setAddress("");
		user.setRole(1);
		user.setSing_way("web");
		user.setCreate_at(new Date());
		user.setUpdate_at(new Date());

		try {
			user.setLast_login(DateUtils.parseDate("2000-01-01 00:00:00",
					"yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("Format string to last login time err.", e);
		}
		this.userDao.addUser(user);
		return "ok";
	}
}
