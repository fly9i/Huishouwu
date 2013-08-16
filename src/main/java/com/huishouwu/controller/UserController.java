package com.huishouwu.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.huishouwu.dao.UserDao;
import com.huishouwu.pojo.User;

@Controller
@RequestMapping("user")
public class UserController {
	
	public static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	private UserDao userDao;

	@Resource
	private void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public String userLogin(HttpServletRequest req){
		User u=new User();
		u.setName("测试用户");
		req.getSession().setAttribute("user", u);
		System.out.println("dengluchenggong");
		return "登陆成功！";
	}

	@RequestMapping("/add")
	@ResponseBody
	@ModelAttribute
	public String addUser(@RequestParam("username") String name,
			@RequestParam("email") String email,
			@RequestParam("mobile") String mobile,
			@RequestParam("password1") String pass,HttpServletRequest req) {
		
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
		int result=0;
		String msg="";
		try{
			result=this.userDao.addUser(user);
			msg="ok";
		}catch(Exception e){
			logger.error("User register err.", e);
			msg=e.getMessage();
		}
		
		if(result==1){
			req.getSession().setAttribute("user", user);
		}
		return msg;
	}
}
