package com.huishouwu.util.email;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.lang3.time.DateUtils;


public class Mail {
	private InternetAddress[] toList=null;
	private String body;
	private String title;
	private String [] mailto=null;
	public Mail(String mails,String title,String body){
		this.mailto=mails.split(",");
		this.title=title;
		this.body=body+"<br/>";
		try {
			this.toList=new InternetAddress().parse(mails);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Mail(String [] mailList,String title,String body){
		String mailToList="";
		this.mailto=mailList;
		this.title=title;
		this.body=body;
		for(int i=0;i<mailList.length;i++){
			if(mailList!=null){
				if(i==0){
				mailToList=mailToList+mailList[i];
				}else{
					mailToList=mailToList+","+mailList[i];
				}
			}
		}
		try {
			this.toList=new InternetAddress().parse(mailToList);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getMailto() {
		return this.mailto;
	}

	public String getBody() {
		return body;
	}



	public String getTitle() {
		return title;
	}

	public boolean send() {
		boolean result=false;
		try {
			// 发件人使用发邮件的电子信箱服务器
			String host = "smtp.exmail.qq.com";
			// 发邮件的出发地（发件人的信箱）
			String from = "service@huishouwu.com";
	
			// 创建 properties ，里面包含了发送邮件服务器的地址。
			Properties props = new Properties();
			// 发送邮件服务器的地址
			props.put("mail.smtp.host", host);
			// 通过验证 默认为false
			props.put("mail.smtp.auth", "true");
			// smtp服务器端口号
//			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
//          props.setProperty("mail.smtp.socketFactory.fallback", "false");   
            props.setProperty("mail.smtp.port", "25");   
//          props.setProperty("mail.smtp.socketFactory.port", "25");  
			// 校验发信人权限
			Email_Autherticator myauth = new Email_Autherticator(
					"service@huishouwu.com", "4567123zf");
			// 创建 session
			Session session = Session.getDefaultInstance(props, myauth);
			// 创建 邮件的message，message对象包含了邮件众多有的部件，都是封装成了set方法去设置的
			MimeMessage message = new MimeMessage(session);
			
			
			Multipart mainPart = new MimeMultipart();    
		      // 创建一个包含HTML内容的MimeBodyPart    
		    BodyPart html = new MimeBodyPart();    
			
		    html.setContent(body, "text/html; charset=utf-8");  
		    mainPart.addBodyPart(html);
			
			// 设置发信人
			message.setFrom(new InternetAddress(from, "回收屋服务"));
			// 收信人
			message.addRecipients(Message.RecipientType.TO, toList);
			// 邮件标题
			message.setSubject(this.title);
			// 邮件内容
			message.setContent(mainPart);

			// 保存以上工作
			message.saveChanges();
			// 发送邮件
			Transport.send(message);
			result=true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}


	public String toString()
	{
		String result="收件人：";
		for(int i=0;i<mailto.length;i++)
			result+=mailto[i]+";";
			
		return result+title+":"+body;
	}

	public static void main(String[] args) {
		Mail m=new Mail("fei.zheng@agrant.cn", "test", "测试一下");
		m.send();
	}
}
