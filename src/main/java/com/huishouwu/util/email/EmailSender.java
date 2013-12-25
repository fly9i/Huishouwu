package com.huishouwu.util.email;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSender {
	private static final Logger logger=LoggerFactory.getLogger(EmailSender.class);
	public EmailSender(){
		
	}
	
	public void sendPass(String cus,String pass,String email){
		
		BufferedReader br=null;
		InputStreamReader isr=null;
		
		try {
			isr=new InputStreamReader(this.getClass().getResourceAsStream("/template_col.html"));
			
			br=new BufferedReader(isr);
			String s="";
			StringBuffer sb=new StringBuffer();
			while((s=br.readLine())!=null){
				s=s.replace("${cus}", cus).replace("${pass}", pass);
				sb.append(s);
			}
			Mail mail=new Mail(email, "回收商账户已开通", sb.toString());
			mail.send();
		} catch (FileNotFoundException e) {
			logger.error("template_col.html File not found",e);
		} catch (IOException e) {
			logger.error("Failed to read file template_col.html.",e);
		}
	}
}
