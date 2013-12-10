package com.huishouwu.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.huishouwu.pojo.Address;
import com.huishouwu.pojo.Collector;
import com.huishouwu.pojo.HomePicture;
import com.huishouwu.util.CustomerContextHolder;

@Repository
public class CollectorDao {
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

	public List<Address> getCollectorById(String collectorid) {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "select * from hsw_user_center.collector where collectorid='"
				+ collectorid + "'";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(
				Address.class));
	}

	public int apply(final Collector collector) {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "insert into hsw_user_center.collector " +
				"(corpname,corpsize,corpowner,email,corpphone,addr,corplicense,corpshow,showsite,create_at,update_at,collectorid)" +
				" values (?,?,?,?,?,?,?,?,?,?,?,?)";
		return this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setObject(1, collector.getCorpName());
				ps.setObject(2, collector.getCorpSize());
				ps.setObject(3, collector.getCorpOwner());
				ps.setObject(4, collector.getEmail());
				ps.setObject(5, collector.getCorpPhone());
				ps.setObject(6, collector.getAddr());
				ps.setObject(7, collector.getCorpLicense());
				ps.setObject(8, collector.getCorpShow());
				ps.setObject(9, collector.getShowSite());
				ps.setObject(10, collector.getCreate_at());
				ps.setObject(11, collector.getUpdate_at());
				ps.setObject(12, collector.getCollectorid());
			}
		});
	}

}
