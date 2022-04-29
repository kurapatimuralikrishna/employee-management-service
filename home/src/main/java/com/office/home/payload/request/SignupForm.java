package com.office.home.payload.request;

import com.office.home.model.ERole;

public class SignupForm {

	private String username;
	private String userPassword;
	private String email;
	private ERole[] roles;
	private int employeeId;
	public SignupForm(String username, String userPassword, String email, ERole[] roles, int employeeId) {
		super();
		this.username = username;
		this.userPassword = userPassword;
		this.email = email;
		this.roles = roles;
		this.employeeId = employeeId;
	}
	public String getUsername() {
		return username;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public String getEmail() {
		return email;
	}
	public ERole[] getRoles() {
		return roles;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRoles(ERole[] roles) {
		this.roles = roles;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
}
