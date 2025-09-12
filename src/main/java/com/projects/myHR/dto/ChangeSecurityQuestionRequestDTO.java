package com.projects.myHR.dto;

public class ChangeSecurityQuestionRequestDTO {

	private String securityQuestion;
	private String securityAnswer;
	
	
	
	public ChangeSecurityQuestionRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ChangeSecurityQuestionRequestDTO(String securityQuestion, String securityAnswer) {
		super();
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
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
		return "ChangeSecurityQuestionRequestDTO [securityQuestion=" + securityQuestion + ", securityAnswer="
				+ securityAnswer + "]";
	}

	
	
	
}
