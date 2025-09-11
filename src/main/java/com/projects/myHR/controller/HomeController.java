package com.projects.myHR.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.myHR.dto.MyHRUserLoginRequestDTO;
import com.projects.myHR.dto.MyHRUserLoginResponseDTO;
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
}
