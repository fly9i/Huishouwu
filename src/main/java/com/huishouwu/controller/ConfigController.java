package com.huishouwu.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		return configHandler.getTypeConfigByName(type);
	}
	

}
