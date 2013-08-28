package com.huishouwu.controller;

import java.util.ArrayList;
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
import com.huishouwu.pojo.TypeConfig;
import com.huishouwu.vo.CartItem;

@Controller
public class OrderController {
	
	@Resource
	private ConfigHandler configHandler;
	
	@RequestMapping("cart")
	public String cart(Model m,HttpServletRequest req){
		Map<String,TypeConfig> typeConfigMap=configHandler.getAllConfig();
		Map<String,Map<String,Integer>> map=new HashMap<String,Map<String,Integer>>();
		map=(Map<String, Map<String, Integer>>) req.getSession().getAttribute("cart");
		
		m.addAttribute("cart", getCartItems(map, typeConfigMap));
		return "cart";
	}
	
	@RequestMapping("order/del/{fid}")
	public String del(@PathVariable String fid,Model m,HttpServletRequest req){
		Map<String,Map<String,Integer>> cart=new HashMap<String,Map<String,Integer>>();
		cart=(Map<String, Map<String, Integer>>) req.getSession().getAttribute("cart");
		
		for(String key:cart.keySet()){
			cart.get(key).remove(fid);
		}
		return "redirect:../../cart";
		
	}
	@RequestMapping("cart/add")
	@ResponseBody
	public String addOrder(@RequestParam(defaultValue="") String desc,@RequestParam(defaultValue="1") String count,HttpServletRequest req){
		String type="";
		Pattern p=Pattern.compile("^[a-zA-Z]+");
		Matcher m=p.matcher(desc);
		if(m.find()){
			type=m.group();
		}else{
			return "{result:'err',code:80001,des:'表单错误'}";
		}
		
		int cartCount=Integer.parseInt(count);
		Map<String,Map<String,Integer>> cart=null;
		cart=(Map<String, Map<String, Integer>>) req.getSession().getAttribute("cart");
		if(cart==null){
			cart=new HashMap<String, Map<String,Integer>>();
		}
		Map<String,Integer> record=cart.get(type);
		if(record!=null ){
			if(record.containsKey(desc)){
				record.put(desc, record.get(desc)+cartCount);
			}else{
				record.put(desc, cartCount);
			}
		}else{
			Map<String,Integer> tmpRecord=new HashMap<String, Integer>();
			tmpRecord.put(desc, cartCount);
			cart.put(type, tmpRecord);
		}
		req.getSession().setAttribute("cart", cart);
		return "{result:'ok',code:200,des:''}";
	}
	private List<CartItem> getCartItems(Map<String,Map<String,Integer>> map,Map<String,TypeConfig> typeConfigMap){
		List<CartItem> cartItems=new ArrayList<CartItem>();
		for(String k:map.keySet()){
			for(String key:map.get(k).keySet()){
				CartItem item=new CartItem();
				item.setDes(typeConfigMap.get(key).getDes());
				item.setFeature(typeConfigMap.get(key).getFeature());
				item.setPrice(typeConfigMap.get(key).getPrice()*map.get(k).get(key));
				item.setCount(map.get(k).get(key));
				item.setFid(key);
				cartItems.add(item);
			}
		}
		return cartItems;
	}
}
