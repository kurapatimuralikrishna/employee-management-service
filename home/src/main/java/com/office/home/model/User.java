package com.office.home.model;

import java.util.Set;

public class User {
	/**
	 * A model for user details to be stored when an employee creates account for EMS.
	 * These details are stored in users table and user roles table.
	 */
	private String username;
	private String userPassword;
	private String email;
	private Integer employeeId;
	private Set<String> roles;
	public User(String username, String userPassword, String email, Integer employeeId, Set<String> roles) {
		super();
		this.username = username;
		this.userPassword = userPassword;
		this.email = email;
		this.employeeId = employeeId;
		this.roles = roles;
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
	public Integer getEmployeeId() {
		return employeeId;
	}
	public Set<String> getRoles() {
		return roles;
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
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
