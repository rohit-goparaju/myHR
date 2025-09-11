package com.projects.myHR.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ResetPasswordRequestDTO {
	@NotNull
	@Pattern(regexp="^([a-z]{1}[a-z0-9]{1,})(@myHR\\.in)$", message = "username must contain only lowercase alphabets, must start with an alphabet, can contain numbers and must end with @myHR.in" )
	private String username;
	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d+)[a-zA-Z][a-zA-Z0-9@]{5,}$", message="should be atleast 6 characters long, should contain atleast \"one lower case, one upper case, one digit and one @\"")
	private String password;
	
	@NotNull
	private String securityAnswer;

	public ResetPasswordRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResetPasswordRequestDTO(
			@NotNull @Pattern(regexp = "^([a-z]{1}[a-z0-9]{1,})(@myHR\\.in)$", message = "username must contain only lowercase alphabets, must start with an alphabet, can contain numbers and must end with @myHR.in") String username,
			@NotNull @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d+)[a-zA-Z][a-zA-Z0-9@]{5,}$", message = "should be atleast 6 characters long, should contain atleast \"one lower case, one upper case, one digit and one @\"") String password,
			@NotNull String securityAnswer) {
		super();
		this.username = username;
		this.password = password;
		this.securityAnswer = securityAnswer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	@Override
	public String toString() {
		return "ResetPasswordRequestDTO [username=" + username + ", password=" + password + ", securityAnswer="
				+ securityAnswer + "]";
	}

}
