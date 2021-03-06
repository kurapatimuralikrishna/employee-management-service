package com.office.home.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupForm {
	private Integer employeeId;
	@NotBlank
	@Size(min=6,max=40)
	private String unencodedPassword;
	@NotBlank
	@Size(max=50)
	@Email
	private String email;
	public SignupForm(Integer employeeId, @NotBlank @Size(min = 6, max = 40) String unencodedPassword,
			@NotBlank @Size(max = 50) @Email String email) {
		super();
		this.employeeId = employeeId;
		this.unencodedPassword = unencodedPassword;
		this.email = email;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public String getUnencodedPassword() {
		return unencodedPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public void setUnencodedPassword(String unencodedPassword) {
		this.unencodedPassword = unencodedPassword;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
