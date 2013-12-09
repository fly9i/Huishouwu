package com.huishouwu.controller;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.huishouwu.pojo.Collector;
import com.huishouwu.pojo.User;
import com.huishouwu.util.Utils;

@Controller
@RequestMapping("col")
public class CollectorController {
	private static final Logger logger=LoggerFactory.getLogger(CollectorController.class);
	

	
	@RequestMapping("/apply")
	@ResponseBody
	public String apply(@RequestParam("corpname") String corpName,
			@RequestParam("corpsize")int corpSize,
			@RequestParam("corpowner") String corpOwner,
			@RequestParam("email") String email,
			@RequestParam("corpphone") String corpPhone,
			@RequestParam("addr") String addr,
			@RequestParam("corplicense") CommonsMultipartFile corpLicense,
			@RequestParam("corpshow") CommonsMultipartFile corpShow,
			@RequestParam("showsite") String showSite,
			HttpServletRequest req) {

		Collector collector=new Collector();
		collector.setAddr(addr);
		
		RandomStringUtils.random(count);
		collector.setCorpLicense(corpLicense);

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
	
}
