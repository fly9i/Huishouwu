package com.huishouwu.buss;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ConfigCacheReloader {
	private static Timer t=new Timer();
	
	public static void start(){
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ConfigHandler ch=new ConfigHandler();
				List<Integer> allTypeid=ch.getAllTypeid();
				for(int typeid : allTypeid){
					ch.clearTypeConfig(typeid);
					ch.getTypeConfigById(typeid);
				}
			}
		}, 1000, 300*1000);
	}
}
