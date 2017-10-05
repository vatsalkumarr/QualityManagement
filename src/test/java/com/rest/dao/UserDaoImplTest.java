package com.rest.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rest.models.User;
import com.rest.services.MvcConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MvcConfiguration.class)
@WebAppConfiguration
public class UserDaoImplTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testLogin() throws Exception {

		User user = new User();

		user.setUserName("vatsal");
		user.setPassword("user");
		
	

		Assert.assertEquals("user",userDao.getUser(user).get(0).getRole());
		
		

	}

}
