package com.huishouwu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishouwu.dao.OrderDao;

@Controller
@RequestMapping("b")
public class BusinessController {

	@Resource
	private OrderDao orderDao;
	
	public String admin(Model m,HttpServletRequest req){
		return "buss/admin";
	}

}
