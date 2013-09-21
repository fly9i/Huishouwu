package com.huishouwu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishouwu.buss.ConfigHandler;
import com.huishouwu.dao.OrderDao;
import com.huishouwu.pojo.Order;
import com.huishouwu.pojo.TypeConfig;
import com.huishouwu.pojo.User;
import com.huishouwu.util.SystemFinal;
import com.huishouwu.util.Utils;
import com.huishouwu.vo.CartItem;
import com.huishouwu.vo.OrderView;

@Controller
public class OrderController {

	@Resource
	private ConfigHandler configHandler;

	@Resource
	private OrderDao orderDao;

	@RequestMapping("cart")
	public String cart(Model m, HttpServletRequest req) {
		Map<String, TypeConfig> typeConfigMap = configHandler.getAllConfig();
		Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();
		map = (Map<String, Map<String, Integer>>) req.getSession()
				.getAttribute("cart");
		if (map != null) {
			m.addAttribute("cart", getCartItems(map, typeConfigMap));
		} else {
			m.addAttribute("cart", null);
		}
		return "cart";
	}

	@RequestMapping("order/del/{fid}")
	public String del(@PathVariable String fid, Model m, HttpServletRequest req) {
		Map<String, Map<String, Integer>> cart = new HashMap<String, Map<String, Integer>>();
		cart = (Map<String, Map<String, Integer>>) req.getSession()
				.getAttribute("cart");

		for (String key : cart.keySet()) {
			cart.get(key).remove(fid);
		}
		return "redirect:../../cart";
	}

	@RequestMapping("orderpre")
	public String toOrder(Model m, HttpServletRequest req) {
		Map<String, TypeConfig> typeConfigMap = configHandler.getAllConfig();
		Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();
		map = (Map<String, Map<String, Integer>>) req.getSession()
				.getAttribute("cart");
		if (map != null) {
			m.addAttribute("cart", getCartItems(map, typeConfigMap));
		} else {
			m.addAttribute("cart", null);
		}
		return "orderpre";
	}

	@RequestMapping("order/add")
	public String addOrder(@RequestParam(defaultValue = "") String addr1,
			@RequestParam(defaultValue = "") String addr3,
			@RequestParam(defaultValue = "") String addr4,
			@RequestParam(defaultValue = "") String addr2,
			@RequestParam(defaultValue = "匿名") String connector,
			@RequestParam(defaultValue = "") String phone, Model m,
			HttpServletRequest req) {

		/**
		 * 每一种类型（电视机等），每一种feature（21寸彩电等）数量
		 */
		Map<String, Map<String, Integer>> cart = (Map<String, Map<String, Integer>>) req
				.getSession().getAttribute("cart");
		Map<String, TypeConfig> typeConfigMap = configHandler.getAllConfig();
		String orderid = Utils.md5(new Date().hashCode() + "_"
				+ phone.hashCode());
		String userid = "";
		User u = (User) req.getSession().getAttribute("user");
		if (u != null) {
			userid = u.getUserid();
		}

		Order order = new Order();

		order.setConnector(connector);
		order.setOrderid(orderid);
		order.setPhone(phone);
		order.setStatus(1);
		order.setUserid(userid);
		order.setAddr1(addr1);
		order.setAddr2(addr2);
		order.setAddr3(addr3);
		order.setAddr4(addr4);

		String fids = "";
		int count = 0;
		for (String key : cart.keySet()) {
			for (String k : cart.get(key).keySet()) {
				count++;
				if (count == 1) {
					fids = k+"@@"+cart.get(key).get(k);
				} else {
					fids += "," + k+"@@"+cart.get(key).get(k);
				}
			}
		}
		order.setFid(fids);

		if (count != 0) {
			orderDao.addOrder(order);
		}
		req.getSession().removeAttribute("cart");
		return "redirect:../";
	}

	/**
	 * cart 每一种类型（电视机等），每一种feature（21寸彩电等）数量
	 */
	@RequestMapping("cart/add")
	@ResponseBody
	public String addOrder(@RequestParam(defaultValue = "") String desc,
			@RequestParam(defaultValue = "1") String count,
			HttpServletRequest req) {
		String type = "";
		Pattern p = Pattern.compile("^[a-zA-Z]+");
		Matcher m = p.matcher(desc);
		if (m.find()) {
			type = m.group();
		} else {
			return "{result:'err',code:80001,des:'表单错误'}";
		}

		int cartCount = Integer.parseInt(count);
		Map<String, Map<String, Integer>> cart = null;
		cart = (Map<String, Map<String, Integer>>) req.getSession()
				.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<String, Map<String, Integer>>();
		}
		Map<String, Integer> record = cart.get(type);
		if (record != null) {
			if (record.containsKey(desc)) {
				record.put(desc, record.get(desc) + cartCount);
			} else {
				record.put(desc, cartCount);
			}
		} else {
			Map<String, Integer> tmpRecord = new HashMap<String, Integer>();
			tmpRecord.put(desc, cartCount);
			cart.put(type, tmpRecord);
		}
		req.getSession().setAttribute("cart", cart);
		return "{result:'ok',code:200,des:''}";
	}

	private List<CartItem> getCartItems(Map<String, Map<String, Integer>> map,
			Map<String, TypeConfig> typeConfigMap) {
		List<CartItem> cartItems = new ArrayList<CartItem>();
		for (String k : map.keySet()) {
			for (String key : map.get(k).keySet()) {
				CartItem item = new CartItem();
				item.setDes(typeConfigMap.get(key).getDes());
				item.setFeature(typeConfigMap.get(key).getFeature());
				item.setPrice(typeConfigMap.get(key).getPrice()
						* map.get(k).get(key));
				item.setCount(map.get(k).get(key));
				item.setFid(key);
				cartItems.add(item);
			}
		}
		return cartItems;
	}

	@RequestMapping("order")
	public String order(Model m,HttpServletRequest req,@RequestParam(defaultValue="fd") String tp){
		/**
		 * tp 参数表示查找订单类型，fd则为所有符合条件的，mg则表示管理自身的
		 */
		User u=(User)req.getSession().getAttribute("users");
		m.addAttribute("user", u);
		List<Order> orderList=null;
		if(u!=null){
			if(u.getRole()==0){
				if(tp.equals("fd")){
					orderList=orderDao.getOrders();
				}else{
					orderList=orderDao.getOrdersByManagerid(u.getUserid());
				}
			}else if(u.getRole()==1){
				orderList=orderDao.getOrdersByUserid(u.getUserid());
			}
		}
		List<OrderView> orderViewList = new ArrayList<OrderView>();
		Map<String, TypeConfig> configMap = configHandler.getAllConfig();
		orderViewList = OrderViewHelper.getOrderView(orderList, configMap);
		m.addAttribute("orders", orderViewList);
			
		return "admin/order";
	}
	
	@RequestMapping("order/accept")
	@ResponseBody
	public String acceptOrder(@RequestParam String[] orderids,
			HttpServletRequest req) {
		User u = (User) req.getSession().getAttribute("user");
		if (u == null || u.getRole() !=0) {
			return "<script type='text/javascript'>alert('只有回收商可以进行接单');window.location.href='../'</script>";
		}
		orderDao.changeOrder(orderids, u.getUserid(),SystemFinal.ORDER_ACCEPTED);
		return "<script type='text/javascript'>alert('接单成功');window.location.href='../home'</script>";
	}
	
	@RequestMapping("order/finish")
	@ResponseBody
	public String finishOrder(@RequestParam String[] orderids,
			HttpServletRequest req) {
		User u = (User) req.getSession().getAttribute("user");
		if (u == null || (u.getRole() != 1 && u.getRole()!=0)) {
			return "<script type='text/javascript'>alert('你无法进行结束订单操作。');window.location.href='../'</script>";
		}
		orderDao.changeOrder(orderids, u.getUserid(),SystemFinal.ORDER_FINISH);
		return "<script type='text/javascript'>alert('结束订单成功');window.location.href='../'</script>";
	}
	
	
}
