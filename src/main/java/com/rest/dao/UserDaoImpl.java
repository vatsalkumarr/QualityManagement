package com.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.rest.models.User;

public class UserDaoImpl implements UserDao{
	
	private JdbcTemplate jdbcTemplate;

	public UserDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<User> getUser(User user) {
		String sql = "select * from User where username = '"+user.getUserName()+"' and password = '"+user.getPassword()+"'" ;

		List<User> users = jdbcTemplate.query(sql,
				new RowMapper<User>() {
		
					@Override
					public User mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						User user = new User();
						user.setRole(rs.getString("role"));
						user.setUserName(rs.getString("username"));
						return user;
					}
				});
		return users;
	}


}
