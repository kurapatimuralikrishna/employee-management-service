package com.office.home.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {
	private Integer employeeId;
	@NotBlank
	@Size(min=6,max=40)
	private String unencodedPassword;
	public LoginForm(Integer employeeId, @NotBlank @Size(min = 6, max = 40) String unencodedPassword) {
		super();
		this.employeeId = employeeId;
		this.unencodedPassword = unencodedPassword;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public String getUnencodedPassword() {
		return unencodedPassword;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public void setUnencodedPassword(String unencodedPassword) {
		this.unencodedPassword = unencodedPassword;
	}
}
