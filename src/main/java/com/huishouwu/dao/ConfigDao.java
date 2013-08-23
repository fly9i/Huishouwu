package com.huishouwu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.huishouwu.pojo.TypeConfig;
import com.huishouwu.pojo.TypeConfigSimple;
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

	public List<TypeConfigSimple> getAllType(){
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "select type,t_name as name,des  from hsw_buss.type_config";
		return this.jdbcTemplate.query(sql,new BeanPropertyRowMapper(TypeConfigSimple.class));
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
	
	public List<TypeConfig> getTypeConfigByName(final String name) {
		logger.debug("Start to query from database for type config.");
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql = "select * from hsw_buss.type_config where t_name=?";
		List<TypeConfig> configList = new ArrayList<TypeConfig>();
		configList = this.jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, name);
				return ps;
			}
		}, new BeanPropertyRowMapper(
				TypeConfig.class));
		return configList;
	}
}
