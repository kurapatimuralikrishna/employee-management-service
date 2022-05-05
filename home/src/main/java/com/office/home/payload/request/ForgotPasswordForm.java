package com.office.home.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ForgotPasswordForm {
	@NotBlank
	@Size(max=20)
	private String attemptedAnswer;
	@NotBlank
	@Size(min=6,max=40)
	private String newPassword;
	public ForgotPasswordForm(@NotBlank @Size(max = 20) String attemptedAnswer,
			@NotBlank @Size(min = 6, max = 40) String newPassword) {
		super();
		this.attemptedAnswer = attemptedAnswer;
		this.newPassword = newPassword;
	}
	public String getAttemptedAnswer() {
		return attemptedAnswer;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setAttemptedAnswer(String attemptedAnswer) {
		this.attemptedAnswer = attemptedAnswer;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
