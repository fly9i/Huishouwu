package com.huishouwu.pojo;

import java.util.Date;

public class Collector {
	private int id;
	private String corpName;
	private int corpSize;
	private String corpOwner;
	private String email;
	private String corpPhone;
	private String addr;
	private String corpLicense;
	private String corpShow;
	private String showSite;
	private Date create_at;
	private Date update_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public int getCorpSize() {
		return corpSize;
	}

	public void setCorpSize(int corpSize) {
		this.corpSize = corpSize;
	}

	public String getCorpOwner() {
		return corpOwner;
	}

	public void setCorpOwner(String corpOwner) {
		this.corpOwner = corpOwner;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCorpPhone() {
		return corpPhone;
	}

	public void setCorpPhone(String corpPhone) {
		this.corpPhone = corpPhone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCorpLicense() {
		return corpLicense;
	}

	public void setCorpLicense(String corpLicense) {
		this.corpLicense = corpLicense;
	}

	public String getCorpShow() {
		return corpShow;
	}

	public void setCorpShow(String corpShow) {
		this.corpShow = corpShow;
	}

	public String getShowSite() {
		return showSite;
	}

	public void setShowSite(String showSite) {
		this.showSite = showSite;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public Date getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
	}

}
