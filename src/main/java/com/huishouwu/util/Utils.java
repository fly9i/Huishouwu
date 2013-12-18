package com.huishouwu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Utils {
	private static Logger logger = LoggerFactory.getLogger(Utils.class);
	public static String md5(String str){
		return MD5Util.MD5(str).toLowerCase();
	}
	
	public static String genAlert(String msg){
		try {
			msg=URLEncoder.encode(msg, "utf8");
		} catch (UnsupportedEncodingException e) {
			logger.error("Failed to encode \""+msg+"\"",e);
		}
		return "redirect:../alert?des=" + msg;
	}
}
