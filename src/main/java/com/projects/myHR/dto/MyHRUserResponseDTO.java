package com.projects.myHR.dto;

import com.projects.myHR.enums.MyHRRoles;

public class MyHRUserResponseDTO {

	private String username;
	private MyHRRoles role = MyHRRoles.EMPLOYEE;

	public MyHRUserResponseDTO() {
		super();
	}

	public MyHRUserResponseDTO(String username, MyHRRoles role) {
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
		return "MyHRUserResponseDTO [username=" + username + ", role=" + role + "]";
	}
	
}
