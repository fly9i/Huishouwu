package com.huishouwu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.huishouwu.pojo.User;
import com.huishouwu.util.CustomerContextHolder;

@Repository
public class TestDao {

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

	public List<User> queryUsers(){
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql="select * from users";
		List<User> userList=new ArrayList<User>();
		userList=this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
		return userList;
	}
	// insert into users
	// (name,pass,email,sign_way,mobile,role,address,create_at,update_at,last_login)
	// values
	// ('test1','test2','fly9i@163.com','web','18611733724',0,'北京霍营龙锦东四',current_timestamp(),current_timestamp(),current_timestamp());

}
