package com.huishouwu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
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
		return "news";
	} 
}
