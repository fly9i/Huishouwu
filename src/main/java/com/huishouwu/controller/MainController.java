package com.huishouwu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishouwu.buss.ConfigHandler;
import com.huishouwu.dao.UserDao;
import com.huishouwu.pojo.TypeConfigSimple;

@Controller
public class MainController {
	
	private UserDao userDao;
	@Resource(name="userDao")
	public void setMainDAO(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	@Resource
	private ConfigHandler configHandler;
	
	@RequestMapping("home")
	public String index(Model m) {
		m.addAttribute("title", "首页");
		return "index";
	}
	
	@RequestMapping("old")
	public String sale_old(Model m) {
		m.addAttribute("title", "卖旧货");
		List<TypeConfigSimple> list=configHandler.getAllType();
		m.addAttribute("typeconfig",list);
		return "sale_old";
	} 
	@RequestMapping("market")
	public String market(Model m) {
		m.addAttribute("title", "市场");
		return "market";
	}
	@RequestMapping("news")
	public String news(Model m) {
		m.addAttribute("title", "新闻");
		
		return "news";
	} 
}
