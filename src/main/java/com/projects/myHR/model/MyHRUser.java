package com.projects.myHR.model;

import java.util.Arrays;

import com.projects.myHR.enums.MyHRRoles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;

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
	
	@Lob
	@Column(nullable=false)
	private byte[] profilePicture;

	@NotNull
	private String securityQuestion;

	@NotNull
	private String securityAnswer;
	
	public MyHRUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyHRUser(long id, String username, String password, MyHRRoles role, byte[] profilePicture,
			@NotNull String securityQuestion, @NotNull String securityAnswer) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.profilePicture = profilePicture;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
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

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	@Override
	public String toString() {
		return "MyHRUser [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", profilePicture=" + Arrays.toString(profilePicture) + ", securityQuestion=" + securityQuestion
				+ ", securityAnswer=" + securityAnswer + "]";
	}

}
