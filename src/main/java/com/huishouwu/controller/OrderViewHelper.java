package com.huishouwu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huishouwu.pojo.Order;
import com.huishouwu.pojo.TypeConfig;
import com.huishouwu.vo.OrderFeature;
import com.huishouwu.vo.OrderView;

public class OrderViewHelper {

	public static List<OrderView> getOrderView(List<Order> orderList,
			Map<String, TypeConfig> configMap) {
		List<OrderView> orderViewList = new ArrayList<OrderView>();
		for (Order order : orderList) {
			String[] fids = order.getFid().split(",");
			OrderView ov = new OrderView();
			ov.setOrderid(order.getOrderid());
			List<OrderFeature> featureList = new ArrayList<OrderFeature>();
			for (String fid : fids) {
				String[] tmp = fid.split("\\@\\@");
				OrderFeature of = new OrderFeature();
				of.setTypeName(configMap.get(tmp[0]).getDes());
				of.setFeature(configMap.get(tmp[0]).getFeature());
				of.setPrice(configMap.get(tmp[0]).getPrice());
				if(tmp.length==2){
				of.setCount(Integer.parseInt(tmp[1]));
				}else{
					of.setCount(1);
				}
				featureList.add(of);

			}
			ov.setCreate(order.getCreate_at());
			ov.setUpdate(order.getUpdate_at());
			ov.setOrderFeatures(featureList);
			ov.setConnect(order.getConnector() + ":" + order.getPhone());
			ov.setAddress(order.getAddr1() + "," + order.getAddr2() + ","
					+ order.getAddr3() + "," + order.getAddr4());
			ov.setStatus(order.getStatus());
			orderViewList.add(ov);
		}
		return orderViewList;
	}
}
