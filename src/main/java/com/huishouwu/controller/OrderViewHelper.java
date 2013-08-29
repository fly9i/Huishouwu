package com.huishouwu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huishouwu.pojo.Order;
import com.huishouwu.pojo.TypeConfig;
import com.huishouwu.vo.OrderView;

public class OrderViewHelper {
	public static List<OrderView> getOrderView(List<Order> orderList,Map<String,TypeConfig> configMap){
		List<OrderView> orderViewList=new ArrayList<OrderView>();
		for(Order order:orderList){
			OrderView ov=new OrderView();
			ov.setOrderid(order.getOrderid());
			ov.setTypeName(configMap.get(order.getFid()).getT_name());
			ov.setFeature(configMap.get(order.getFid()).getFeature());
			ov.setConnect(order.getConnector()+":"+order.getPhone());
			ov.setAddress(order.getAddr1()+","+order.getAddr2()+","+order.getAddr3()+","+order.getAddr4());
			ov.setStatus(order.getStatus());
			orderViewList.add(ov);
		}
		return orderViewList;
	}
}
