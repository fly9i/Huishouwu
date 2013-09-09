package com.huishouwu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishouwu.buss.ConfigHandler;
import com.huishouwu.dao.OrderDao;
import com.huishouwu.pojo.Order;
import com.huishouwu.pojo.TypeConfig;
import com.huishouwu.pojo.User;
import com.huishouwu.vo.OrderView;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private ConfigHandler configHandler;
	
	@RequestMapping("")
	public String show(Model m,HttpServletRequest req){
		User u=(User)req.getSession().getAttribute("user");
		List<Order> orderList=null;
		if(u!=null && u.getRole()==2){
			orderList=orderDao.getOrdersByManagerid(u.getUserid());
			
		}else{
			return "admin";
		}
		m.addAttribute("title","管理");
		
		List<OrderView> orderViewList=new ArrayList<OrderView>();
		Map<String,TypeConfig> configMap=configHandler.getAllConfig();
		orderViewList=OrderViewHelper.getOrderView(orderList, configMap);
		m.addAttribute("orders", orderViewList);
		return "admin";
	}
	
	@RequestMapping("/test")
	public String test(){
		return "test";
	}
}
