package com.projects.myHR.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Page<MyHRUserResponseDTO>> findAllUsers(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5") int size){
		Page<MyHRUserResponseDTO> userList = userService.findAllUsersForEmployee(page, size);
		return ResponseEntity.ok(userList);
	}
	
}
