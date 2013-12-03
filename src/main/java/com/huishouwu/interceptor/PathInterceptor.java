package com.huishouwu.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.huishouwu.buss.ConfigHandler;

public class PathInterceptor extends HandlerInterceptorAdapter {
	
	@Resource
	private ConfigHandler configHandler;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler) throws Exception {
		return super.preHandle(req, res, handler);
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		request.setAttribute("mainpath", "http://localhost:8080/web");
//		request.setAttribute("tip", "沧海横流，方显英雄本色");
		
		request.setAttribute("mainpath", configHandler.getSysConfig().get("mainpath"));
		request.setAttribute("tip", configHandler.getSysConfig().get("tip"));
	}

}
