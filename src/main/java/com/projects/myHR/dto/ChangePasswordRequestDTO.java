package com.projects.myHR.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ChangePasswordRequestDTO {
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d+)[a-zA-Z][a-zA-Z0-9@]{5,}$", message="should be atleast 6 characters long, should contain atleast \"one lower case, one upper case, one digit and one @\"")
	@NotNull
	String newPassword;
	
	public ChangePasswordRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChangePasswordRequestDTO(
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d+)[a-zA-Z][a-zA-Z0-9@]{5,}$", message = "should be atleast 6 characters long, should contain atleast \"one lower case, one upper case, one digit and one @\"") String newPassword) {
		super();
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "ChangePasswordRequestDTO [newPassword=" + newPassword + "]";
	}
}
