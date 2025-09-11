package com.projects.myHR.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.myHR.dto.ChangePasswordRequestDTO;
import com.projects.myHR.dto.MyHRUserLoginRequestDTO;
import com.projects.myHR.dto.MyHRUserLoginResponseDTO;
import com.projects.myHR.dto.ResetPasswordRequestDTO;
import com.projects.myHR.dto.SecurityQuestionRequestDTO;
import com.projects.myHR.dto.SecurityQuestionResponseDTO;
import com.projects.myHR.enums.MyHRRequestStatus;
import com.projects.myHR.enums.MyHRValidity;
import com.projects.myHR.service.MyHRUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class HomeController {
	
	MyHRUserService userService;
	
	public HomeController(MyHRUserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<MyHRUserLoginResponseDTO> login(@Valid @RequestBody MyHRUserLoginRequestDTO user) {
		MyHRUserLoginResponseDTO loginResponseDTO = userService.validate(user);
		if(loginResponseDTO.getJwt().equals(MyHRValidity.INVALID.name())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		return ResponseEntity.ok(loginResponseDTO);
	}
	
	@GetMapping("/profilePicture/{username}")
	public ResponseEntity<byte[]> getProfilePicture(@PathVariable("username") String username){
		byte[] profilePicture = userService.getProfilePicture(username);
		if(profilePicture != null) {
			return ResponseEntity.ok(profilePicture);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<MyHRRequestStatus> changePassword(@Valid @RequestBody ChangePasswordRequestDTO changePasswordRequestDTO){
		MyHRRequestStatus requestStaus = userService.changePassword(changePasswordRequestDTO);
		
		if(requestStaus == MyHRRequestStatus.SUCCESS) {
			return ResponseEntity.ok(requestStaus);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(requestStaus);
		}
	}
	
	@PostMapping("/getSecurityQuestion")
	public ResponseEntity<SecurityQuestionResponseDTO> getSecurityQuestion(@RequestBody SecurityQuestionRequestDTO securityQuestionRequestDTO){
		SecurityQuestionResponseDTO securityQuestionResponseDTO = userService.getSecurityQuestion(securityQuestionRequestDTO);
		if(securityQuestionResponseDTO != null) {
			return ResponseEntity.ok(securityQuestionResponseDTO);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PutMapping("/getSecurityQuestion/resetPassword")
	public ResponseEntity<MyHRRequestStatus> resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO){
		
		MyHRRequestStatus resetStatus = userService.resetPassword(resetPasswordRequestDTO);
		if(resetStatus == MyHRRequestStatus.SUCCESS) {
			return ResponseEntity.ok(resetStatus);
		}else {			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resetStatus);
		}
	}
}
