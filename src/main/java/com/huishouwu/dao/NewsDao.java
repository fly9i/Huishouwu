package com.huishouwu.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

import com.huishouwu.pojo.News;
import com.huishouwu.util.CustomerContextHolder;

@Repository
public class NewsDao {
	private static final Logger logger = LoggerFactory.getLogger(NewsDao.class);
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

	public List<News> getAllNews() {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		String sql = "select * from hsw_buss.news";
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(
				News.class));
	}

	public List<News> getNewsById(final int id){
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql="select * from hsw_buss.news where id=?";
		return this.jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setInt(1, id);
				return ps;
			}
		}, new BeanPropertyRowMapper(News.class));
	}
	
	public int deleteNewsById(final int id) {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql = "update hsw_buss.news set flag=-1 where id=?";
		return this.jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				return ps;
			}
		});
	}

	public int updateNewsById(final int id, final String content,
			final String title) {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql = "update hsw_buss.news set content=?,title=? where id=?";
		return this.jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, content);
				ps.setString(2, title);
				ps.setInt(3, id);
				return ps;
			}
		});
	}

	public int addNews(final String title, final String content,
			final Date time, final String author) {
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql = "insert into hsw_buss.news (author,title,content,time) values (?,?,?,?)";
		return this.jdbcTemplate.update(sql,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
					ps.setString(1, author);
					ps.setString(2, title);
					ps.setString(3, content);
					ps.setDate(4, new java.sql.Date(time.getTime()));
			}

		
		});
	}
}
