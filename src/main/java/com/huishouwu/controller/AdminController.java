package com.huishouwu.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.huishouwu.buss.ConfigHandler;
import com.huishouwu.dao.ContentDao;
import com.huishouwu.dao.OrderDao;
import com.huishouwu.pojo.HomePicture;
import com.huishouwu.pojo.Order;
import com.huishouwu.pojo.TypeConfig;
import com.huishouwu.pojo.User;
import com.huishouwu.vo.OrderView;

@Controller
@RequestMapping("admin")
public class AdminController {
	private static Logger logger = LoggerFactory
			.getLogger(AdminController.class);
	@Resource
	private OrderDao orderDao;

	@Resource
	private ContentDao contentDao;

	@Resource
	private ConfigHandler configHandler;

	@RequestMapping("")
	public String show(Model m, HttpServletRequest req) {
		m.addAttribute("title", "管理");

		User u = (User) req.getSession().getAttribute("user");
		m.addAttribute("user", u);

		return "admin/admin";
	}

	@RequestMapping("/order")
	public String dataAdmin(@RequestParam(defaultValue = "c") String ob,
			@RequestParam(defaultValue = "d") String st, Model m,
			HttpServletRequest req) {
		User u = (User) req.getSession().getAttribute("user");
		m.addAttribute("user", u);
		List<Order> orderList = null;
		if (u != null && u.getRole() == 2) {
			orderList = orderDao.getOrders();
			List<OrderView> orderViewList = new ArrayList<OrderView>();
			Map<String, TypeConfig> configMap = configHandler.getAllConfig();
			orderViewList = OrderViewHelper.getOrderView(orderList, configMap);
			m.addAttribute("orders", orderViewList);
		}
		return "admin/data_admin";
	}

	@RequestMapping("/news")
	public String news(HttpServletRequest req, Model m) {
		User u = (User) req.getSession().getAttribute("user");
		m.addAttribute("user", u);
		if (u != null && u.getRole() == 2) {
			return "admin/news_admin";
		} else {
			String ss = "请以管理员身份登录";
			try {
				ss = URLEncoder.encode(ss, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:../alert?des=" + ss;
		}

	}

	@RequestMapping("/user")
	public String user(HttpServletRequest req, Model m) {
		User u = (User) req.getSession().getAttribute("user");
		m.addAttribute("user", u);
		if (u != null && u.getRole() == 2) {
			return "admin/user_admin";
		} else {
			String ss = "请以管理员身份登录";
			try {
				ss = URLEncoder.encode(ss, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:../alert?des=" + ss;
		}

	}

	@RequestMapping("/pic")
	public String pic(HttpServletRequest req, Model m) {
		User u = (User) req.getSession().getAttribute("user");
		m.addAttribute("user", u);
		if (u != null && u.getRole() == 2) {
			List<HomePicture> pics = contentDao.getPicture(1);
			m.addAttribute("pics", pics);
			return "admin/pic_admin";
		} else {
			String ss = "请以管理员身份登录";
			try {
				ss = URLEncoder.encode(ss, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:../alert?des=" + ss;
		}
	}

	@RequestMapping("/delpic")
	@ResponseBody
	public String delpic(@RequestParam String id, HttpServletRequest req,
			Model m) {

		User u = (User) req.getSession().getAttribute("user");
		m.addAttribute("user", u);
		if (u == null || u.getRole() == 2) {
			String ss = "请以管理员身份登录";
			try {
				ss = URLEncoder.encode(ss, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:../alert?des=" + ss;
		} else {

			int delId = Integer.parseInt(id);
			contentDao.deletePic(delId);
			return "{code:200,desc:'删除成功'}";
		}
	}

	@RequestMapping("/upimg")
	public String upload(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest req, Model m) {
		User u = (User) req.getSession().getAttribute("user");
		m.addAttribute("user", u);
		if (u == null || u.getRole() == 2) {
			String ss = "请以管理员身份登录";
			try {
				ss = URLEncoder.encode(ss, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:../alert?des=" + ss;
		}

		String uploadPath = req.getServletContext().getRealPath("/")
				+ "uploads/slideshow/";
		String fileName = file.getOriginalFilename();
		String[] names = fileName.split("\\.");
		String ext = names[names.length - 1];
		String name = new Date().getTime() + ""
				+ String.valueOf((int) (Math.random() * 1000)) + "." + ext;
		File f = new File(uploadPath + name);
		boolean res = false;
		try {

			file.transferTo(f);
			contentDao.addPic(name);
			res = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("file upload faild.", e);
		}
		String s = res ? "上传成功。" : "上传失败";
		try {
			s = URLEncoder.encode(s, "utf8");
		} catch (UnsupportedEncodingException e) {
			logger.error("Error encode information", e);
		}
		return "redirect:./pic";
	}

	@RequestMapping("/orderdel")
	@ResponseBody
	public String delOrder(@RequestParam String id, HttpServletRequest req,
			Model m) {
		User u = (User) req.getSession().getAttribute("user");
		m.addAttribute("user", u);
		if (u != null && u.getRole() == 2) {
			orderDao.deleteOrder(id);
			return "{code:200,des:'删除成功。'}";
		} else {
			String ss = "请以管理员身份登录";
			try {
				ss = URLEncoder.encode(ss, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:../alert?des=" + ss;
		}

	}

	@RequestMapping("/config")
	public String config(Model m, HttpServletRequest req) {
		User u = (User) req.getSession().getAttribute("user");
		m.addAttribute("user", u);
		if (u == null || u.getRole() != 2) {
			String ss = "请以管理员身份登录";
			try {
				ss = URLEncoder.encode(ss, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:../alert?des=" + ss;
		}
		Map<String, String> configs = contentDao.getSysConfig();
		m.addAttribute("mainpathReal", configs.get("mainpath"));
		m.addAttribute("tipReal", configs.get("tip"));
		return "admin/config_admin";

	}

	@RequestMapping("/upconfig")
	@ResponseBody
	public String updateConfig(@RequestParam String tip, Model m,
			HttpServletRequest req) {
		User u = (User) req.getSession().getAttribute("user");
		m.addAttribute("user", u);
		if (u == null || u.getRole() != 2) {
						return "{code:400,des:'请以管理员身份登录'}";
		}

		contentDao.updateSysConfig(tip, "tip");
		return "{code:200,des:'ok'}";
	}
	
	@RequestMapping("/collector")
	@ResponseBody
	public String checkCollector(Model m, HttpServletRequest req){
		User u = (User) req.getSession().getAttribute("user");
		m.addAttribute("user", u);
		if (u == null || u.getRole() != 2) {
						return "{code:400,des:'请以管理员身份登录'}";
		}

		
		return "{code:200,des:'ok'}";
	}

}
