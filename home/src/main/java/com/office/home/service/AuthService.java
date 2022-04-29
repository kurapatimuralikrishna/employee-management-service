package com.office.home.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.office.home.payload.request.SignupForm;

public interface AuthService {

	ResponseEntity<?> registerUser(@Valid SignupForm signup);

}
