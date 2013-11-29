package com.huishouwu.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.huishouwu.pojo.Address;
import com.huishouwu.pojo.HomePicture;
import com.huishouwu.util.CustomerContextHolder;

@Repository
public class AddressDao {
	private JdbcTemplate jdbcTemplate;

	private DataSource dynamicDataSource;

	private void setDataSource(String dataSource) {
		CustomerContextHolder.setCustomerType(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dynamicDataSource);
	}

	@Autowired
	public void setDynamicDataSource(DataSource dynamicDataSource) {
		this.dynamicDataSource = dynamicDataSource;
	}

	public List<Address> getAddrByParentId(String parentid) {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "select * from hsw_buss.Address where parent_id='"
				+ parentid + "'";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(
				Address.class));
	}

	public List<Address> getAddrLevel1() {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "select * from hsw_buss.Address where level=1";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(
				Address.class));
	}

	public List<Address> getAddrLevel0() {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "select * from hsw_buss.Address where level=0";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(
				Address.class));
	}

	public List<Address> getAllAddrs() {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "select * from hsw_buss.Address";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(
				Address.class));
	}

}
