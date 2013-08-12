package com.huishouwu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishouwu.dao.TestDao;
import com.huishouwu.pojo.User;

@Controller
public class MainController {
	
	private TestDao testDao;
	@Resource(name="testDao")
	public void setMainDAO(TestDao testDao) {
		this.testDao = testDao;
	}
	
	
	@RequestMapping("home")
	public String index(Model m) {
		m.addAttribute("title", "首页");
		return "index";
	}
	
	@RequestMapping("old")
	public String sale_old(Model m) {
		m.addAttribute("title", "卖旧货");
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
		List<User> users=testDao.queryUsers();
		for (User u:users){
			System.out.println(u.getEmail());
		}
		return "news";
	} 
}
