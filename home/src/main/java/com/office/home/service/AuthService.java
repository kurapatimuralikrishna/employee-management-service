package com.office.home.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.office.home.payload.request.ForgotPasswordForm;
import com.office.home.payload.request.LoginForm;
import com.office.home.payload.request.SignupForm;

public interface AuthService {

	ResponseEntity<?> registerUser(@Valid SignupForm signup);

	ResponseEntity<?> login(@Valid LoginForm loginForm);

	ResponseEntity<?> logout();

	ResponseEntity<?> getSecretQuestion(Integer employeeId);

	ResponseEntity<?> changePassword(Integer employeeId, ForgotPasswordForm form);

}
