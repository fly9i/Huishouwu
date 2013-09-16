package com.huishouwu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
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
	
	public Map<String,String> getSysConfig(){
		String sql="select type,value from hsw_buss.sys_config";
		List<String[]> list=this.jdbcTemplate.query(sql, new RowMapper<String[]>(){

			@Override
			public String[] mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				String [] res=new String[2];
				res[0]=rs.getString("type");
				res[1]=rs.getString("value");
				return res;
			}
			
		});
		Map<String,String> map=new HashMap<String,String>();
		for(String [] typeEntry:list){
			map.put(typeEntry[0], typeEntry[1]);
		}
		
		return map;
		
	}

	public int updateSysConfig(final String value,final String type){
		setDataSource(CustomerContextHolder.MYSQLDATASOURCE);
		final String sql="update hsw_buss.sys_config set value=? where type=?";
		return this.jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, value);
				ps.setString(2, type);
				return ps;
			}
		});
	}
}
