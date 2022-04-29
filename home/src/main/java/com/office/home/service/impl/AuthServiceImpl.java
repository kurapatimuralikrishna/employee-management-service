package com.office.home.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.office.home.dao.UserDao;
import com.office.home.payload.request.SignupForm;
import com.office.home.payload.response.MessageResponse;
import com.office.home.service.AuthService;

public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserDao dao;
	/**
	 * If a user is already logged in - 
	 * 		disallow creating new account
	 * 		this can be done at browser but malicious actors can figure out a way to send fraudulent request.
	 * 		but is this a security vulnerability?
	 * Build user with signup form details
	 * create roles for user
	 * Add this user to database
	 */
	@Override
	public ResponseEntity<?> registerUser(@Valid SignupForm signupForm) {
		//An idea is to change authentication to OAuth2 with employee email for authentication.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null&&auth.isAuthenticated()) { 
			//A user can't create another account when he is logged in.
			return null; 
			}
		else {
			//Check if user with username already exists already exists
			boolean userExists = dao.existsByUsername(signupForm.getUsername());
			if(userExists) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("This username is not available"));
			}
			boolean emailExists = dao.existsByEmail(signupForm.getEmail());
			if(emailExists) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("This email is not available"));
			}
			boolean anotherAccountExists
			return null;
		}
	}

}
