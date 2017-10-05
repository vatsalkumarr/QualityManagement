package com.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.models.ErrorMessage;
import com.rest.models.User;
import com.rest.services.UserService;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
	@Autowired
	private UserService userService;

	/*
	 * 
	 * Test Plans REST APIs
	 */

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public ResponseEntity<?> updateCase(@RequestBody User user) {
		try {

			userService.getUser(user);
			return new ResponseEntity<>(userService.getUser(user),
					HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage = e + "    <==== error";
			ErrorMessage error = new ErrorMessage();
			error.setStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(errorMessage);

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
	}

}
