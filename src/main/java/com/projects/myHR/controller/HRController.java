package com.projects.myHR.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projects.myHR.dto.MyHRUserRequestDTO;
import com.projects.myHR.dto.MyHRUserResponseDTO;
import com.projects.myHR.enums.MyHRRoles;
import com.projects.myHR.service.MyHRUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hr/")
@PreAuthorize("hasAnyRole('ADMIN', 'HR')")
public class HRController {

	private MyHRUserService userService;
	
	public HRController(MyHRUserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/addUser")
	public ResponseEntity<MyHRUserResponseDTO> addUser(@Valid @RequestPart("user") MyHRUserRequestDTO userReqDTO, @RequestPart("profilePicture") MultipartFile profilePicture) throws IOException{
		if(userReqDTO.getRole() != MyHRRoles.ADMIN) {
		Optional<MyHRUserResponseDTO> userResponseDTO = userService.addUser(userReqDTO, profilePicture);
		if(userResponseDTO.isPresent())
			return ResponseEntity.ok(userResponseDTO.get());
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}	
	}

	@GetMapping("/findAllUsers")
	public ResponseEntity<List<MyHRUserResponseDTO>> findAllUsers(){
		List<MyHRUserResponseDTO> userList = userService.findAllUsersForHR();
		return ResponseEntity.ok(userList);
	}
	
}
