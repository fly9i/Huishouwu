package com.huishouwu.buss;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.huishouwu.dao.ConfigDao;
import com.huishouwu.dao.ContentDao;
import com.huishouwu.pojo.HomePicture;
import com.huishouwu.pojo.TypeConfig;
import com.huishouwu.pojo.TypeConfigSimple;

@Service
public class ConfigHandler {
	private static final Logger logger=LoggerFactory.getLogger(ConfigHandler.class);
	
	@Autowired
	private ConfigDao configDao;
	
	@Autowired
	private ContentDao contentDao;
	
	
	@Cacheable(value="typeconfig",key="#id+'typeid'")
	public List<TypeConfig> getTypeConfigById(int id){
		return configDao.getTypeConfigById(id);
	}
	@Cacheable(value="typeconfigname",key="#name+'typename'")
	public List<TypeConfig> getTypeConfigByName(String name){
		return configDao.getTypeConfigByName(name);
	}
	
	@CacheEvict(value="typeconfig",allEntries=true)
	public void clearAllTypeConfig(){
		logger.debug("Clear all type configs from cache.");
	}
	@CacheEvict(value="typeconfig",key="#id+'typeid'")
	public void clearTypeConfig(int id){
		logger.debug("Clear type config with key:"+id);
	}
	
	@Cacheable(value="typeconfigall")
	public List<TypeConfigSimple> getAllType(){
		return configDao.getAllType();
	}
	@Cacheable("configall")
	public Map<String,TypeConfig> getAllConfig(){
		return configDao.getAllConfig();
	}

	@Cacheable("contentconfig")
	public Map<String,String> getSysConfig(){
		return contentDao.getSysConfig();
	}
	
	@Cacheable("homepic")
	public List<HomePicture> getHomepics(){
		return contentDao.getPicture(1);
	}
	@CacheEvict(value="homepic",allEntries=true)
	public void clearAllHomePic(){
		logger.debug("Clear all home pic cache from cache.");
	}
}
