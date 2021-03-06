package com.huishouwu.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.huishouwu.dao.UserDao;
import com.huishouwu.pojo.User;
import com.huishouwu.util.SystemFinal;
import com.huishouwu.util.Utils;

@Controller
@RequestMapping("user")
public class UserController {

	public static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	Md5PasswordEncoder md5 = new Md5PasswordEncoder();
	

	private UserDao userDao;

	@Resource
	private void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@RequestMapping("/login")
	@ResponseBody
	public String userLogin(@RequestParam("login_name") String name,
			@RequestParam("login_password") String pass, HttpServletRequest req) {
		List<User> userList = null;
		int code = 0;
		String msg = "";
		try {
			userList = userDao.checkUser(name, md5.encodePassword(pass, SystemFinal.SALT));
		} catch (Exception e) {
			logger.error("Error on user login.User:" + name, e);
			code = -2;
			msg = e.getMessage();
		}

		if (userList.size() > 0) {
			code = 200;
			msg = "登录成功";
			req.getSession().setAttribute("user", userList.get(0));
		} else if (userList.size() == 0) {
			code = -1;
			msg = "登录失败,用户名密码错误";
		}

		return "{code:" + code + ",message:'" + msg + "'}";
	}

	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest req) {
		req.getSession().removeAttribute("user");
		return "{code:200,message:'退出成功。'}";
	}

	private boolean isDupUname(String u) {
		boolean isDup = false;
		List<User> list = userDao.dupUname(u);
		if (list != null && !list.isEmpty()) {
			isDup = true;
		}
		return isDup;
	}

	private boolean isDupEmail(String u) {
		boolean isDup = false;
		List<User> list = userDao.dupEmail(u);
		if (list != null && !list.isEmpty()) {
			isDup = true;
		}
		return isDup;
	}

	private boolean isDupMobile(String u) {
		boolean isDup = false;
		List<User> list = userDao.dupMobile(u);
		if (list != null && !list.isEmpty()) {
			isDup = true;
		}
		return isDup;
	}

	@RequestMapping("/check")
	@ResponseBody
	public String checkUser(@RequestParam String val, @RequestParam String type) {
		String msg = "";
		int code = 400;
		boolean isDup = false;
		if (type.equals("un")) {
			isDup = isDupUname(val);
		} else if (type.equals("em")) {
			isDup = isDupEmail(val);
		} else if (type.equals("mb")) {
			isDup = isDupMobile(val);
		}
		
		if (!isDup) {
			code = 200;
			msg = "ok";
		} else {
			msg = "dup";
		}
		return "{code:" + code + ",msg:'"+msg+"'}";
	}

	@RequestMapping("/add")
	@ResponseBody
	public String addUser(@RequestParam("username") String name,
			@RequestParam("email") String email,
			@RequestParam("mobile") String mobile,
			@RequestParam("password1") String pass,
			@RequestParam(defaultValue = "1") String role,
			HttpServletRequest req) {

		User user = new User();
		User u = (User) req.getSession().getAttribute("user");
		if (u != null && u.getRole() == 2) {
			if (role.equals("2")) {
				user.setRole(2);
			} else if (role.equals("0")) {
				user.setRole(0);
			} else {
				user.setRole(1);
			}
		} else {
			user.setRole(1);
		}

		user.setName(name);
		user.setEmail(email);
		user.setMobile(mobile);

		user.setPass(md5.encodePassword(pass, SystemFinal.SALT));

		user.setAddress("");

		user.setSign_way("web");
		user.setCreate_at(new Date());
		user.setUpdate_at(new Date());
		user.setUserid(Utils.md5(user.getName() + "_" + user.getEmail() + "_"
				+ user.getMobile() + "_" + new Date().getTime()));

		boolean isDupUname = isDupUname(user.getName());
		boolean isDupEmail = isDupEmail(user.getEmail());
		boolean isDupMobile = isDupMobile(user.getMobile());
		if (isDupUname || isDupEmail || isDupMobile) {
			return "{code:201,message:' 重复的用户名/邮箱/手机号/'}";
		} else {

			try {
				user.setLast_login(DateUtils.parseDate("2000-01-01 00:00:00",
						"yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				logger.error("Format string to last login time err.", e);
			}
			int result = 0;
			int code = 0;
			String msg = "";
			try {
				result = this.userDao.addUser(user);
				msg = "添加用户成功。";
			} catch (Exception e) {
				logger.error("User register err.", e);
				msg = e.getMessage();
			}

			if (result == 1) {
				code = 200;
				if (u == null) {
					req.getSession().setAttribute("user", user);
				}
			}
			return "{code:" + code + ",message:'" + msg + "'}";
		}
	}
	
	
	@RequestMapping("/addcol")
	@ResponseBody
	public String addCollector(HttpServletRequest req){
		
		return "";
	}
	
}
