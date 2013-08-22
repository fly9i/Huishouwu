package com.huishouwu.interceptor;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest req,
			HttpServletResponse res, Object handler)
			throws Exception {
		res.setCharacterEncoding("utf8");
		req.setCharacterEncoding("utf8");
		System.out.println("into interceptor");
		String [] filters=new String []{"/cmsmg"};
		String uriString=req.getRequestURI();
		boolean beFilter=false;
		for(String s:filters){
			if(uriString.contains(s)){
				beFilter=true;
				break;
			}
		}
		PrintWriter pw=res.getWriter();
		if(beFilter){
			if(req.getSession().getAttribute("user")==null){
				pw.print("<script>alert('请先登录！');window.location.href='home'</script>");
			}
			pw.close();
			return false;
		}
//		Map paramsMap = req.getParameterMap();  
//		  
//        for (Iterator<Map.Entry> it = paramsMap.entrySet().iterator(); it  
//                .hasNext();) {  
//            Map.Entry entry = it.next();  
//            Object[] values = (Object[]) entry.getValue();  
//            for (Object obj : values) {  
//                if (!DataUtil.isValueSuccessed(obj)) {  
//                    throw new RuntimeException("有非法字符：" + obj);  
//                }  
//            }  
//        }  
		
		return super
				.preHandle(req, res, handler);
	}

}