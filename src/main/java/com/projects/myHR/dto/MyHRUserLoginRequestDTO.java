package com.projects.myHR.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MyHRUserLoginRequestDTO {
	@NotNull
	@Pattern(regexp="^([a-z]{1}[a-z0-9]{1,})(@myHR\\.in)$", message = "username must contain only lowercase alphabets, must start with an alphabet, can contain numbers and must end with @myHR.in" )
	private String username;
	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d+)[a-zA-Z][a-zA-Z0-9@]{5,}$", message="should be atleast 6 characters long, should contain atleast \"one lower case, one upper case, one digit and one @\"")
	private String password;

	public MyHRUserLoginRequestDTO() {
		super();
	}


	public MyHRUserLoginRequestDTO(
			@NotNull @Pattern(regexp = "^([a-z]{1}[a-z0-9]{1,})(@myHR\\.in)$", message = "username must contain only lowercase alphabets, must start with an alphabet, can contain numbers and must end with @myHR.in") String username,
			@NotNull @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d+)[a-zA-Z][a-zA-Z0-9@]{5,}$", message = "should be atleast 6 characters long, should contain atleast \"one lower case, one upper case, one digit and one @\"") String password) {
		super();
		this.username = username;
		this.password = password;
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

	@Override
	public String toString() {
		return "MyHRUserLoginRequestDTO [username=" + username + ", password=" + password + "]";
	}
	

}
