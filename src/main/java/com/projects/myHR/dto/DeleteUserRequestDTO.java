package com.projects.myHR.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class DeleteUserRequestDTO {
	
	@NotNull
	@Pattern(regexp="^([a-z]{1}[a-z0-9]{1,})(@myHR\\.in)$", message = "username must contain only lowercase alphabets, must start with an alphabet, can contain numbers and must end with @myHR.in" )
	private String username;

	public DeleteUserRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeleteUserRequestDTO(
			@NotNull @Pattern(regexp = "^([a-z]{1}[a-z0-9]{1,})(@myHR\\.in)$", message = "username must contain only lowercase alphabets, must start with an alphabet, can contain numbers and must end with @myHR.in") String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "DeleteUserRequestDTO [username=" + username + "]";
	}
	
}
