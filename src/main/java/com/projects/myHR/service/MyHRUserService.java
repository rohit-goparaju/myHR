package com.projects.myHR.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projects.myHR.dto.MyHRUserLoginRequestDTO;
import com.projects.myHR.dto.MyHRUserLoginResponseDTO;
import com.projects.myHR.dto.MyHRUserRequestDTO;
import com.projects.myHR.dto.MyHRUserResponseDTO;
import com.projects.myHR.enums.MyHRRoles;
import com.projects.myHR.model.MyHRUser;
import com.projects.myHR.repo.MyHRUserRepo;

@Service
public class MyHRUserService {

	private MyHRUserRepo repo;
	private PasswordEncoder encoder;
	private AuthenticationManager authManager;
	private JWTService jwtService;
	
	public MyHRUserService(MyHRUserRepo repo, PasswordEncoder encoder, AuthenticationManager authManager,
			JWTService jwtService) {
		super();
		this.repo = repo;
		this.encoder = encoder;
		this.authManager = authManager;
		this.jwtService = jwtService;
	}

	public Optional<MyHRUserResponseDTO> addUser(MyHRUserRequestDTO userReqDTO, MultipartFile profilePicture) throws IOException {
		if(repo.findByUsername(userReqDTO.getUsername()) == null) {
			MyHRUser user = new MyHRUser();
			user.setUsername(userReqDTO.getUsername());
			user.setPassword(encoder.encode(userReqDTO.getPassword()));
			user.setRole(userReqDTO.getRole());
			user.setProfilePicture(profilePicture.getBytes());
			
			MyHRUser savedUser = repo.save(user);

			MyHRUserResponseDTO userResponseDTO = new MyHRUserResponseDTO(savedUser.getUsername(), savedUser.getRole());

			return Optional.of(userResponseDTO);
		}else {
			return Optional.empty();
		}
	}
	
	private List<MyHRUserResponseDTO> convertToResponseDTOList(List<MyHRUser> hrUserList){
		List<MyHRUserResponseDTO> userResponseList = new ArrayList<>();

		if(hrUserList != null && !hrUserList.isEmpty()) {
			userResponseList = hrUserList.stream().map(user->new MyHRUserResponseDTO(user.getUsername(), user.getRole())).toList();
		}
		
		return userResponseList;
	}

	public List<MyHRUserResponseDTO> findAllUsers() {
		return convertToResponseDTOList(repo.findAll());
	}

	public List<MyHRUserResponseDTO> findAllUsersForHR() {
		return convertToResponseDTOList(repo.findByRoleIn(List.of(MyHRRoles.HR, MyHRRoles.EMPLOYEE)));
	}

	public List<MyHRUserResponseDTO> findAllUsersForEmployee() {
		return convertToResponseDTOList(repo.findByRole(MyHRRoles.EMPLOYEE));	
	}

	public MyHRUserLoginResponseDTO validate(MyHRUserLoginRequestDTO user) {
		Authentication auth= authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		MyHRUserLoginResponseDTO loginResponseDTO = new MyHRUserLoginResponseDTO();
		if(auth.isAuthenticated()) {
			MyHRUser repoUser = repo.findByUsername(user.getUsername());
			MyHRUserResponseDTO userResponseDTO = new MyHRUserResponseDTO(repoUser.getUsername(), repoUser.getRole());
			loginResponseDTO.setUser(userResponseDTO);
			loginResponseDTO.setJwt(jwtService.generateToken(user.getUsername()));
		}
		return loginResponseDTO;
	}
	
	public MyHRUser findByUsername(String username) {
		return repo.findByUsername(username);
	}
	
	public byte[] getProfilePicture(String username) {
		MyHRUser user = repo.findByUsername(username);
		if(user != null)
			return user.getProfilePicture();
		else
			return null;
	}
	
}
