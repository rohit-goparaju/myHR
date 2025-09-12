package com.projects.myHR.dto;

import com.projects.myHR.enums.MyHRRoles;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UpdateRoleRequestDTO {
	
	@NotNull
	@Pattern(regexp="^([a-z]{1}[a-z0-9]{1,})(@myHR\\.in)$", message = "username must contain only lowercase alphabets, must start with an alphabet, can contain numbers and must end with @myHR.in" )
	private String username;
	@NotNull
	private MyHRRoles role;
	public UpdateRoleRequestDTO() {
		super();
	}
	public UpdateRoleRequestDTO(
			@NotNull @Pattern(regexp = "^([a-z]{1}[a-z0-9]{1,})(@myHR\\.in)$", message = "username must contain only lowercase alphabets, must start with an alphabet, can contain numbers and must end with @myHR.in") String username,
			@NotNull MyHRRoles role) {
		super();
		this.username = username;
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public MyHRRoles getRole() {
		return role;
	}
	public void setRole(MyHRRoles role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UpdateRoleRequestDTO [username=" + username + ", role=" + role + "]";
	}
}
