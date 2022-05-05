package com.office.home.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.home.payload.request.ForgotPasswordForm;
import com.office.home.payload.request.LoginForm;
import com.office.home.payload.request.SignupForm;
import com.office.home.service.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/office/log")
public class AuthController {
	@Autowired
	private AuthService service;
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm){
		return service.login(loginForm);
	}
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupForm signupForm){
		return service.registerUser(signupForm);
	}
	@PostMapping("/logout")
	public ResponseEntity<?> logout(){
		return service.logout();
	}
	@GetMapping("/secret/{employeeId}/question")
	public ResponseEntity<?> getSecretQuestion(@PathVariable Integer employeeId) {
		return service.getSecretQuestion(employeeId);
	}
	@PostMapping("/secret/{employeeId}/answer")
	public ResponseEntity<?> changePassword(@PathVariable Integer employeeId, @RequestBody ForgotPasswordForm form) {
		return service.changePassword(employeeId,form);
	}
}
