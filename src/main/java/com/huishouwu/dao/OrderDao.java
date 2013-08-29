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
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.huishouwu.pojo.Order;
import com.huishouwu.pojo.User;
import com.huishouwu.util.CustomerContextHolder;

@Repository
public class OrderDao {
	private static final Logger logger = LoggerFactory
			.getLogger(OrderDao.class);
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

	@SuppressWarnings("unchecked")
	public List<Order> getOrdersByOrderid(final String orderid){
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql = "select * from hsw_buss.orders where orderid=?";
		return this.jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, orderid);
				return ps;
			}
		}, new BeanPropertyRowMapper(Order.class));
	
	}

	@SuppressWarnings("unchecked")
	public List<Order> getOrdersByManagerid(final String managerid){
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql = "select * from hsw_buss.orders where manager=?";
		return this.jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, managerid);
				return ps;
			}
		}, new BeanPropertyRowMapper(Order.class));
	
	}

	
	public List<Order> getOrders(){
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql = "select * from hsw_buss.orders";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(Order.class));
	
	}
	public int acceptOrder(final String [] orderids,final String userid){
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql = "update hsw_buss.orders set manager=?,status=0 where orderids=?";
		return this.jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, userid);
				ps.setString(2, orderids[0]);
				return ps;
			}
		});
		
	}
	public int addOrder(final Order order) {

		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "insert into hsw_buss.orders "
				+ "(orderid,fid,connector,phone,addr1,addr2,addr3,addr4,status,userid,addrid) "
				+ "values " + "(?,?,?,?,?,?,?,?,?,?,?)";
		return this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setObject(1, order.getOrderid());

				ps.setObject(2, order.getFid());
				ps.setObject(3, order.getConnector());
				ps.setObject(4, order.getPhone());
				ps.setObject(5, order.getAddr1());
				ps.setObject(6, order.getAddr2());
				ps.setObject(7, order.getAddr3());
				ps.setObject(8, order.getAddr4());
				ps.setObject(9, order.getStatus());
				ps.setObject(10, order.getUserid());
				ps.setObject(11, order.getAddrid());
			}
		});
	}

}
