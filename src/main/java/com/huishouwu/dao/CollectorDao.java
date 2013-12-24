package com.huishouwu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.huishouwu.pojo.Address;
import com.huishouwu.pojo.Collector;
import com.huishouwu.pojo.HomePicture;
import com.huishouwu.util.CustomerContextHolder;
import com.huishouwu.util.SystemFinal;

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

	public Collector getCollectorById(String collectorid) {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "select * from hsw_user_center.collector where collectorid=?";
		return this.jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper(Collector.class),collectorid);
	}

	public List<Collector> getCollectorsByStatus(int status) {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "select * from hsw_user_center.collector where status="
				+ status;
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(
				Collector.class));
	}

	public List<Collector> getCollectorsByEmail(final String email) {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql = "select * from hsw_user_center.collector where email=?";
		return this.jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, email);
				return ps;
			}
		}, new BeanPropertyRowMapper(Collector.class));
	}

	public int ignoreCollector(final String collectorid) {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql = "update hsw_user_center.collector set status="
				+ SystemFinal.USER_COLLECTOR_IGNORE + " where collectorid=?";
		return this.jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, collectorid);
				return ps;
			}
		});
	}

	public int agreeCollector(final String collectorid) {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql = "update hsw_user_center.collector set status="
				+ SystemFinal.USER_COLLECTOR + " where collectorid=?";
		return this.jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, collectorid);
				return ps;
			}
		});
	}

	public int apply(final Collector collector) {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "insert into hsw_user_center.collector "
				+ "(corpname,corpsize,corpowner,email,corpphone,addr,corplicense,corpshow,showsite,create_at,update_at,collectorid,status)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
				ps.setObject(13, collector.getStatus());
			}
		});
	}

}
