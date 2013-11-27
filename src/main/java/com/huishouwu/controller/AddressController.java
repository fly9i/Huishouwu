package com.huishouwu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishouwu.dao.AddressDao;
import com.huishouwu.pojo.Address;

@Controller
@RequestMapping("addr")
public class AddressController {
	
	@Resource
	private AddressDao dao;
	
	@RequestMapping("/p/{id}")
	@ResponseBody
	public List<Address> getAddrs(@PathVariable String id,Model m){
		return dao.getAddrs(id);
	}
}
