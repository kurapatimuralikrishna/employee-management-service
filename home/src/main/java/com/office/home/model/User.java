package com.office.home.model;

import java.util.Set;

public class User {
	private String username;
	private String userPassword;
	private String email;
	private Set<ERole> roles;
	private int employeeId;
	public User(String username, String userPassword, String email, Set<ERole> roles, int employeeId) {
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
	public Set<ERole> getRoles() {
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
	public void setRoles(Set<ERole> roles) {
		this.roles = roles;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
}
