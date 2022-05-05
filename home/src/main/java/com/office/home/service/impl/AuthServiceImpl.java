package com.office.home.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.office.home.dao.UserDao;
import com.office.home.model.User;
import com.office.home.model.UserDetailsImpl;
import com.office.home.payload.request.ForgotPasswordForm;
import com.office.home.payload.request.LoginForm;
import com.office.home.payload.request.SignupForm;
import com.office.home.payload.response.MessageResponse;
import com.office.home.payload.response.UserInfoResponse;
import com.office.home.security.jwt.JwtUtils;
import com.office.home.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserDao dao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtUtils jwtUtils;
	/**
	 * If a user is already logged in - disallow creating new account
	 * Build user with signup form details
	 * create roles for user
	 * Add this user to database
	 */
	@Override
	public ResponseEntity<?> registerUser(@Valid SignupForm signupForm) {
		//An idea is to change authentication to OAuth2 with employee email for authentication.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null&&auth.isAuthenticated()&&!(auth instanceof AnonymousAuthenticationToken)) { 
			//A user can't create another account when he is logged in.
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("You are already logged in.")); 
		}
		else {
			//Check if user with username already exists already exists
			boolean userExists = dao.existsByEmployeeId(signupForm.getEmployeeId());
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
			String username = signupForm.getEmployeeId().toString();
			String encodedPassword = passwordEncoder.encode(signupForm.getUnencodedPassword());
			Set<String> roles = new HashSet<>();
			roles.add("ROLE_USER");
			User user = new User(username,encodedPassword,signupForm.getEmail(),signupForm.getEmployeeId(),roles);
			dao.registerUser(user);
			return ResponseEntity.ok(new MessageResponse("User registered successfully"));
		}
	}
	/**
	 * if a user is already logged in, then should I prevent user from logging in again? 
	 * verify form details
	 * authenticate user
	 * add these details to security context holder
	 * return a cookie response
	 */
	@Override
	public ResponseEntity<?> login(@Valid LoginForm loginForm) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null&&auth.isAuthenticated()&&!(auth instanceof AnonymousAuthenticationToken)) { 
			//A user can't log into another account when he is logged in.
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("You are already logged in.")); 
		}
		String username = loginForm.getEmployeeId().toString();
		String password = loginForm.getUnencodedPassword();
		auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		SecurityContextHolder.getContext().setAuthentication(auth);
		UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
		ResponseCookie responseCookie = jwtUtils.generateJwtCookie(principal);
		List<String> roles = principal.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, responseCookie.toString())
				.body(new UserInfoResponse(
						principal.getUsername(),
						principal.getEmail(),
						principal.getEmployeeId(),
						roles));
	}
	/**
	 * Logout api
	 * create a clean cookie with empty token
	 * return response with clean cookie
	 */
	@Override
	public ResponseEntity<?> logout() {
		ResponseCookie cookie = jwtUtils.generateCleanCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("Logout successfull"));
	}
	@Override
	public ResponseEntity<?> getSecretQuestion(Integer employeeId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null&&auth.isAuthenticated()&&!(auth instanceof AnonymousAuthenticationToken)) { 
			//A user can't create another account when he is logged in.
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("You are already logged in.")); 
		}
		String username = employeeId.toString();
		String question = dao.getSecretQuestion(username);
		if (question==null) {
			return ResponseEntity.badRequest().body(new MessageResponse("No such username"));
		}
		return ResponseEntity.ok(new MessageResponse(question));
	}
	@Override
	public ResponseEntity<?> changePassword(Integer employeeId, ForgotPasswordForm form) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null&&auth.isAuthenticated()&&!(auth instanceof AnonymousAuthenticationToken)) { 
			//A user can't create another account when he is logged in.
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("You are already logged in.")); 
		}
		String username = employeeId.toString();
		String originalAnswer = dao.getSecretAnswer(username);
		if(originalAnswer == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("No such username"));
		}
		boolean isCorrectAnswer = form.getAttemptedAnswer().equals(originalAnswer);
		if(isCorrectAnswer) {
			String encodedPassword = passwordEncoder.encode(form.getNewPassword());
			dao.changePassword(username,encodedPassword);
			return ResponseEntity.ok(new MessageResponse("Password changed"));
		}
		else return ResponseEntity.badRequest().body(new MessageResponse("wrong answer"));
	}
}
