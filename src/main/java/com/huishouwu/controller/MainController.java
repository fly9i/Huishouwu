package com.huishouwu.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishouwu.buss.ConfigHandler;
import com.huishouwu.dao.ContentDao;
import com.huishouwu.dao.NewsDao;
import com.huishouwu.dao.OrderDao;
import com.huishouwu.dao.UserDao;
import com.huishouwu.pojo.HomePicture;
import com.huishouwu.pojo.TypeConfigSimple;

@Controller
public class MainController {

	private UserDao userDao;

	@Resource(name = "userDao")
	public void setMainDAO(UserDao userDao) {
		this.userDao = userDao;
	}

	@Resource
	private ContentDao contentDao;

	@Resource
	private OrderDao orderDao;

	@Resource
	private ConfigHandler configHandler;

	@Resource
	private NewsDao newsDao;

	@RequestMapping("home")
	public String index(Model m, HttpServletRequest req) {
		// String root=req.getServletContext().getRealPath("/");
		// String realpath=root+"uploads/slideshow/";
		// File f=new File(realpath);
		// String [] files=f.list();
		List<HomePicture> files = configHandler.getHomepics();
		m.addAttribute("title", "首页");
		m.addAttribute("files", files);

		// m.addAttribute("news", newsDao.getAllNews(1));
		return "index";
	}

	@RequestMapping("desc")
	public String desc(Model m, HttpServletRequest req) {
		return "description";
	}
	@RequestMapping("reg_collector")
	public String regCollector(Model m, HttpServletRequest req) {
		return "reg_collector";
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

	@RequestMapping("aboutus")
	public String aboutus(Model m) {
		m.addAttribute("title", "关于我们");
		return "aboutus";
	}

	@RequestMapping("alert")
	@ResponseBody
	public String alert(@RequestParam String des, HttpServletRequest req) {
		try {
			des = new String(des.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ss = "<script>alert('" + des + "')</script>";
		return ss;
	}

}
