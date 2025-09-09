package com.projects.myHR.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.myHR.dto.MyHRUserRequestDTO;
import com.projects.myHR.dto.MyHRUserResponseDTO;
import com.projects.myHR.service.MyHRUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

	private MyHRUserService userService;

	public AdminController(MyHRUserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<MyHRUserResponseDTO> addUser(@Valid @RequestBody MyHRUserRequestDTO userReqDTO){
		Optional<MyHRUserResponseDTO> userResponseDTO = userService.addUser(userReqDTO);
		if(userResponseDTO.isPresent())
			return ResponseEntity.ok(userResponseDTO.get());
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@GetMapping("/findAllUsers")
	public ResponseEntity<List<MyHRUserResponseDTO>> findAllUsers(){
		List<MyHRUserResponseDTO> userList = userService.findAllUsers();
		return ResponseEntity.ok(userList);
	}
	
}
