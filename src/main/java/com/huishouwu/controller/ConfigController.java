package com.huishouwu.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishouwu.buss.ConfigHandler;
import com.huishouwu.dao.ConfigDao;
import com.huishouwu.dao.UserDao;

@Controller
@RequestMapping("config")
public class ConfigController {



	@Resource
	private ConfigHandler configHandler;
//	@Resource
//	private ConfigDao configDao;
	
	
	@RequestMapping("/{type}")
	@ResponseBody
	public Object getTypeConfig(@PathVariable String type){
		return configHandler.getTypeConfig(1);
	}
}
