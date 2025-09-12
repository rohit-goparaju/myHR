package com.projects.myHR.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<MyHRUserResponseDTO> addUser(@Valid @RequestPart("user") MyHRUserRequestDTO userReqDTO, @RequestPart("profilePicture") MultipartFile profilePicture) throws IOException{
		Optional<MyHRUserResponseDTO> userResponseDTO = userService.addUser(userReqDTO, profilePicture);
		if(userResponseDTO.isPresent())
			return ResponseEntity.ok(userResponseDTO.get());
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@GetMapping("/findAllUsers")
	public ResponseEntity<Page<MyHRUserResponseDTO>> findAllUsers(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5") int size){
		Page<MyHRUserResponseDTO> userList = userService.findAllUsers(page, size);
		return ResponseEntity.ok(userList);
	}
	
}
