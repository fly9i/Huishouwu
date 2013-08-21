package com.huishouwu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.huishouwu.pojo.TypeConfig;
import com.huishouwu.util.CustomerContextHolder;

@Repository
public class ConfigDao {
	private static final Logger logger=LoggerFactory.getLogger(ConfigDao.class);
	private JdbcTemplate jdbcTemplate;

	private DataSource dynamicDataSource;

	/**
	 * @param dataSource
	 */
	private void setDataSource(String dataSource) {
		CustomerContextHolder.setCustomerType(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dynamicDataSource);
	}

	@Autowired
	public void setDynamicDataSource(DataSource dynamicDataSource) {
		this.dynamicDataSource = dynamicDataSource;
	}

	public List<Integer> getAllTypeid(){
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "select distinct type  from hsw_buss.type_config";
		return this.jdbcTemplate.query(sql, new RowMapper<Integer>(){

			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				return rs.getInt("type");
			}
			
		});
	}
	
	public List<TypeConfig> getTypeConfigById(int id) {
		logger.debug("Start to query from database for type config.");
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "select * from hsw_buss.type_config where type="+id;
		List<TypeConfig> configList = new ArrayList<TypeConfig>();
		configList = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(
				TypeConfig.class));
		return configList;
	}
}
