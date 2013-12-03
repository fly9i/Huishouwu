package com.huishouwu.buss;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.huishouwu.pojo.TypeConfigSimple;

public class ConfigCacheReloader {
	private static Timer t=new Timer();
	
//	public static void start(){
//		t.schedule(new TimerTask() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				ConfigHandler ch=new ConfigHandler();
//				List<TypeConfigSimple> allType=ch.getAllType();
//				for(TypeConfigSimple type : allType){
//					ch.clearTypeConfig(type.getType());
//					ch.getTypeConfigById(type.getType());
//				}
//			}
//		}, 1000, 300*1000);
//	}
}
