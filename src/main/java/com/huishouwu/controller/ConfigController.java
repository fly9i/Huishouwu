package com.huishouwu.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishouwu.buss.ConfigHandler;

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
//		return configHandler.getTypeConfig();
		return null;
	}
}
