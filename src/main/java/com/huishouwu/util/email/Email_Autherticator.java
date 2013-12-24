package com.huishouwu.util.email;

import javax.mail.PasswordAuthentication;

/**
 * 校验发信人权限的方法
 * 
 * @author Administrator
 * 
 */
class Email_Autherticator extends javax.mail.Authenticator {
	private String strUser;
	private String strPwd;

	public Email_Autherticator(String user, String password) {
		this.strUser = user;
		this.strPwd = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(strUser, strPwd);
	}
}
