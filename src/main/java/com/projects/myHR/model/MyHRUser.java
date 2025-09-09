package com.projects.myHR.model;

import com.projects.myHR.enums.MyHRRoles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MyHRUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique=true, nullable=false)
	private String username;

	@Column(nullable=false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private MyHRRoles role = MyHRRoles.EMPLOYEE;

	public MyHRUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyHRUser(long id, String username, String password, MyHRRoles role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public MyHRRoles getRole() {
		return role;
	}

	public void setRole(MyHRRoles role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "MyHRUser [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
}
