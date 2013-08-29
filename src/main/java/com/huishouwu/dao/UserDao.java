package com.huishouwu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.huishouwu.pojo.User;
import com.huishouwu.util.CustomerContextHolder;

@Repository
public class UserDao {

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

	public List<User> getUsers() {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "select * from users";
		List<User> userList = new ArrayList<User>();
		userList = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(
				User.class));
		return userList;
	}

	public int addUser(final User user) {

		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "insert into users "
				+ "(name,pass,email,sign_way,mobile,role,address,create_at,update_at,last_login,userid) "
				+ "values " + "(?,?,?,?,?,?,?,?,?,?,?)";
		return this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setObject(1, user.getName());

				ps.setObject(2, user.getPass());
				ps.setObject(3, user.getEmail());
				ps.setObject(4, user.getSing_way());
				ps.setObject(5, user.getMobile());
				ps.setObject(6, user.getRole());
				ps.setObject(7, user.getAddress());
				ps.setObject(8, user.getCreate_at());
				ps.setObject(9, user.getUpdate_at());
				ps.setObject(10, user.getLast_login());
				ps.setObject(11, user.getUserid());
			}
		});
	}

	public List<User> checkUser(final String name, final String pass) {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql = "select * from users where pass=? and (name=? or email=? or mobile=?)";
		return this.jdbcTemplate.query(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql);
				ps.setObject(1, pass);
				ps.setObject(2, name);
				ps.setObject(3, name);
				ps.setObject(4, name);
				return ps;
			}
		}, new BeanPropertyRowMapper<User>(User.class));
	}
	// insert into users
	// (name,pass,email,sign_way,mobile,role,address,create_at,update_at,last_login)
	// values
	// ('test1','test2','fly9i@163.com','web','18611733724',0,'北京霍营龙锦东四',current_timestamp(),current_timestamp(),current_timestamp());

}
