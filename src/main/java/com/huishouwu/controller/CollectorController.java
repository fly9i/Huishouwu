package com.huishouwu.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.huishouwu.dao.CollectorDao;
import com.huishouwu.dao.UserDao;
import com.huishouwu.pojo.Collector;
import com.huishouwu.pojo.User;
import com.huishouwu.util.SystemFinal;
import com.huishouwu.util.Utils;

@Controller
@RequestMapping("col")
public class CollectorController {
	private static final Logger logger = LoggerFactory
			.getLogger(CollectorController.class);

	private Md5PasswordEncoder md5 = new Md5PasswordEncoder();

	@Resource
	private CollectorDao collectorDao;

	@Resource
	private UserDao userDao;

	private String getNewFileName(CommonsMultipartFile file) {
		String fileName = file.getOriginalFilename();
		String[] names = fileName.split("\\.");
		String ext = names[names.length - 1];
		String name = String.valueOf(System.currentTimeMillis())
				+ RandomStringUtils.randomNumeric(4) + "." + ext;
		return name;
	}

	@RequestMapping("/test")
	@ResponseBody
	public String apply() {
		return "{str:'test'}";
	}

	@RequestMapping("/op/{id}")
	@ResponseBody
	public String changeStatus(@RequestParam String op,
			@PathVariable("id") String id) {
		boolean add = false;
		switch (op) {
		case "agree":
			add = true;
			break;
		default:
			add = false;
			break;
		}

		int code = 0;
		String des = "";
		if (add) {
			User u = new User();
			Collector collector = collectorDao.getCollectorById(id);
			u.setAddress(collector.getAddr());
			u.setCreate_at(new Date());
			u.setEmail(collector.getEmail());
			u.setLast_login(new Date());
			u.setMobile(collector.getCorpPhone());
			u.setName(RandomStringUtils.randomNumeric(10));
			String pass = RandomStringUtils.randomAlphabetic(8);
			u.setPass(md5.encodePassword(pass, SystemFinal.SALT));
			u.setRole(SystemFinal.USER_COLLECTOR);
			u.setSign_way("apply");
			u.setUpdate_at(new Date());
			u.setUserid(collector.getCollectorid());
			int res = 0;
			try {
				res = userDao.addUser(u);
				collectorDao.agreeCollector(collector.getCollectorid());
				code=200;
				des="生成用户成功";
			} catch (Exception e) {
				logger.error("Failed to add user.", e);
				res = 0;
			}
			if (res == 0) {
				code = -1;
				des = "生成用户失败";
			}

		} else {
			collectorDao.ignoreCollector(id);
			code=200;
			des="成功忽略申请";
		}
		return "{code:"+code+",des:'"+des+"',op:'" + op + "'}";
	}

	@RequestMapping("/apply")
	@ResponseBody
	public String apply(
			@RequestParam(required = false, value = "corpname") String corpName,
			@RequestParam(required = false, value = "corpsize", defaultValue = "0") int corpSize,
			@RequestParam(required = false, value = "corpowner") String corpOwner,
			@RequestParam(required = false, value = "email") String email,
			@RequestParam(required = false, value = "corpphone") String corpPhone,
			@RequestParam(required = false, value = "addr") String addr,
			@RequestParam(required = false, value = "corplicense") CommonsMultipartFile corpLicense,
			@RequestParam(required = false, value = "corpshow") CommonsMultipartFile corpShow,
			@RequestParam(required = false, value = "showsite") String showSite,
			HttpServletRequest req) {
		String uploadPath = req.getSession().getServletContext()
				.getRealPath("/")
				+ "/uploads/collector_file/";
		boolean go = true;
		if (corpName == null || corpPhone == null || addr == null) {
			return "{code:400,desc:'公司名称或联系方式为空'}";
		}
		Collector collector = new Collector();
		collector.setAddr(addr);
		if (corpLicense != null) {
			String corpLisName = this.getNewFileName(corpLicense);

			File corpLisFile = new File(uploadPath + corpLisName);
			collector.setCorpLicense(corpLisName);
			try {
				corpLicense.transferTo(corpLisFile);
				collector.setCorpLicense(corpLisName);
			} catch (IllegalStateException e1) {
				logger.error("Failed to save file corpLicense", e1);
			} catch (IOException e1) {
				logger.error("Failed to save file corpLicense", e1);
			}
		}

		if (corpShow != null) {
			String corpShowName = this.getNewFileName(corpShow);
			File corpShowFile = new File(uploadPath + corpShowName);
			try {
				corpShow.transferTo(corpShowFile);
				collector.setCorpShow(corpShowName);
			} catch (IllegalStateException e1) {
				logger.error("Failed to save file corpShowFile", e1);
			} catch (IOException e1) {
				logger.error("Failed to save file corpShowFile", e1);
			}
		}
		collector.setCorpName(corpName);
		collector.setCorpOwner(corpOwner);
		collector.setCorpPhone(corpPhone);
		collector.setCorpSize(corpSize);
		collector.setCreate_at(new Date());
		collector.setEmail(email);
		collector
				.setCollectorid("C" + RandomStringUtils.randomAlphanumeric(15));
		collector.setStatus(SystemFinal.USER_COLLECTOR_APPLY);
		collector.setShowSite(showSite);
		collector.setUpdate_at(new Date());

		int result = 0;
		int code = 0;
		String msg = "";
		if (go) {
			try {
				result = this.collectorDao.apply(collector);
				msg = "成功完成申请。";
			} catch (Exception e) {
				logger.error("User register err.", e);
				msg = e.getMessage();
			}
		}
		if (result == 1) {
			code = 200;
		} else {
			code = 400;
			msg = "Failed to apply.";
		}

		return "<script type='text/javascript'>alert('" + msg
				+ "');window.parent.location.reload();</script>";

	}

}
