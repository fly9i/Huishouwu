package com.huishouwu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.huishouwu.pojo.HomePicture;
import com.huishouwu.util.CustomerContextHolder;

@Repository
public class ContentDao {

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

	public List<HomePicture> getPicture(int status){
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql="select * from hsw_buss.home_pic where 1=1";
		if(status!=0){
			sql+=" and status="+status;
		}
		sql+=" order by create_at desc";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(HomePicture.class));
	}
	
	public int addPic(final String name){
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql="insert into hsw_buss.home_pic (name,status,create_at) values (?,?,?)";
		return this.jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setInt(2, 1);
				ps.setObject(3, new Date());
				return ps;
			}
		});
	}
	
	public int deletePic(final int id){
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql="update hsw_buss.home_pic set status=-1 where id=?";
		return this.jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setInt(1, id);
				return ps;
			}
		});
	}

}
