package com.projects.myHR.dto;

import com.projects.myHR.enums.MyHRValidity;

public class MyHRUserLoginResponseDTO {
	private MyHRUserResponseDTO user;
	private String jwt = MyHRValidity.INVALID.name();

	public MyHRUserLoginResponseDTO() {
		super();
	}

	public MyHRUserLoginResponseDTO(MyHRUserResponseDTO user, String jwt) {
		super();
		this.user = user;
		this.jwt = jwt;
	}

	public MyHRUserResponseDTO getUser() {
		return user;
	}

	public void setUser(MyHRUserResponseDTO user) {
		this.user = user;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	@Override
	public String toString() {
		return "MyHRUserLoginResponseDTO [user=" + user + ", jwt=" + jwt + "]";
	}
	
}
