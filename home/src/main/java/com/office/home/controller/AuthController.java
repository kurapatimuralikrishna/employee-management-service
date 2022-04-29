package com.office.home.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.home.payload.request.SignupForm;
import com.office.home.service.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/office/log")
public class AuthController {
	@Autowired
	private AuthService service;
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupForm signupForm){
		try {
			return service.registerUser(signupForm);
		}
		catch (Exception e) {
			return null;
		}
	}
}
