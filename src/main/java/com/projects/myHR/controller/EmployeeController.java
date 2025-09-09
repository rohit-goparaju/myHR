package com.projects.myHR.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.myHR.dto.MyHRUserResponseDTO;
import com.projects.myHR.service.MyHRUserService;

@RestController
@RequestMapping("/employee/")
@PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
public class EmployeeController {
	
	private MyHRUserService userService;
	
	
	public EmployeeController(MyHRUserService userService) {
		super();
		this.userService = userService;
	}



	@GetMapping("/findAllUsers")
	public ResponseEntity<List<MyHRUserResponseDTO>> findAllUsers(){
		List<MyHRUserResponseDTO> userList = userService.findAllUsersForEmployee();
		return ResponseEntity.ok(userList);
	}
	
}
