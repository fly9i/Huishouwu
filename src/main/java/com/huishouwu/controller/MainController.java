package com.huishouwu.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishouwu.buss.ConfigHandler;
import com.huishouwu.dao.NewsDao;
import com.huishouwu.dao.OrderDao;
import com.huishouwu.dao.UserDao;
import com.huishouwu.pojo.TypeConfigSimple;

@Controller
public class MainController {

	private UserDao userDao;

	@Resource(name = "userDao")
	public void setMainDAO(UserDao userDao) {
		this.userDao = userDao;
	}

	@Resource
	private OrderDao orderDao; 
	
	@Resource
	private ConfigHandler configHandler;

	@Resource
	private NewsDao newsDao;
	
	@RequestMapping("index")
	public String index(Model m,HttpServletRequest req) {
		String root=req.getServletContext().getRealPath("/");
		String realpath=root+"uploads/slideshow/";
		File f=new File(realpath);
		String [] files=f.list();
		m.addAttribute("title", "首页");
		m.addAttribute("files", files);
		return "index";
	}

	@RequestMapping("old")
	public String sale_old(Model m, HttpServletRequest req) {
		Map<String, Map<String, Integer>> cart = (Map<String, Map<String, Integer>>) req
				.getSession().getAttribute("cart");
		Map<String, Integer> cartCount = new HashMap<String, Integer>();
		if (cart != null) {
			for (String key : cart.keySet()) {
				int typeCount = 0;
				for (String k : cart.get(key).keySet()) {
					typeCount += cart.get(key).get(k);
				}
				cartCount.put(key, typeCount);
			}
		}
		m.addAttribute("title", "卖旧货");
		List<TypeConfigSimple> list = configHandler.getAllType();
		m.addAttribute("typeconfig", list);
		// m.addAttribute("cart", cart);
		m.addAttribute("cart", cartCount);
		return "sale_old";
	}

	@RequestMapping("market")
	public String market(Model m) {
		m.addAttribute("title", "市场");
		return "market";
	}

	@RequestMapping("news")
	public String news(Model m) {
		m.addAttribute("news", newsDao.getAllNews(1));
				return "news";
	}
}
