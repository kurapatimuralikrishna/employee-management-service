package com.office.home.payload.response;

import java.util.List;

public class UserInfoResponse {
	private String username;
	private String email;
	private Integer employeeId;
	private List<String> roles;
	public UserInfoResponse(String username, String email, Integer employeeId, List<String> roles) {
		super();
		this.username = username;
		this.email = email;
		this.employeeId = employeeId;
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
