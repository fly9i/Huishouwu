package com.huishouwu.vo;

import java.util.Date;
import java.util.List;

public class OrderView {
	private String orderid;
	private String address;
	private String connect;
	private int status;
	private List<OrderFeature> orderFeatures;
	private int total;
	private Date create;
	private Date update;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public List<OrderFeature> getOrderFeatures() {
		return orderFeatures;
	}
	public void setOrderFeatures(List<OrderFeature> orderFeatures) {
		this.orderFeatures = orderFeatures;
	}
	public int getTotal() {
		for(OrderFeature of:this.getOrderFeatures()){
			total+=of.getPrice()*of.getCount();
		}
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Date getCreate() {
		return create;
	}
	public void setCreate(Date create) {
		this.create = create;
	}
	public Date getUpdate() {
		return update;
	}
	public void setUpdate(Date update) {
		this.update = update;
	}
	
}
