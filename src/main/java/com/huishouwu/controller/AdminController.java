package com.huishouwu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@RequestMapping("")
	public String show(Model m){
		m.addAttribute("title","管理");
		return "admin";
	}
}
