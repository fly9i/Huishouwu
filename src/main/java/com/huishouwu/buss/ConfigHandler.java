package com.huishouwu.buss;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.huishouwu.dao.ConfigDao;
import com.huishouwu.pojo.TypeConfig;
import com.huishouwu.pojo.TypeConfigSimple;

@Service
public class ConfigHandler {
	private static final Logger logger=LoggerFactory.getLogger(ConfigHandler.class);
	
	@Autowired
	private ConfigDao configDao;
	
	
	
	@Cacheable(value="typeconfig",key="#id+'typeid'")
	public List<TypeConfig> getTypeConfigBy(int id){
		return configDao.getTypeConfigById(id);
	}
	
	@CacheEvict(value="typeconfig",allEntries=true)
	public void clearAllTypeConfig(){
		logger.debug("Clear all type configs from cache.");
	}
	@CacheEvict(value="typeconfig",key="#id+'typeid'")
	public void clearTypeConfig(int id){
		logger.debug("Clear type config with key:"+id);
	}

	public List<TypeConfigSimple> getAllType(){
		return configDao.getAllType();
	}
	

}