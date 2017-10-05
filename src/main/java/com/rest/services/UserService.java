package com.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rest.dao.UserDao;
import com.rest.dao.UserDaoImpl;
import com.rest.models.User;

public class UserService {

	@Autowired
	private UserDao userDao;
	
	public List<User> getUser(User user) {
		return ((UserDaoImpl) userDao).getUser(user);
	}
	
}
