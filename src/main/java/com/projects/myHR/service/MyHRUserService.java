package com.projects.myHR.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projects.myHR.dto.ChangePasswordRequestDTO;
import com.projects.myHR.dto.ChangeSecurityQuestionRequestDTO;
import com.projects.myHR.dto.MyHRUserLoginRequestDTO;
import com.projects.myHR.dto.MyHRUserLoginResponseDTO;
import com.projects.myHR.dto.MyHRUserRequestDTO;
import com.projects.myHR.dto.MyHRUserResponseDTO;
import com.projects.myHR.dto.ResetPasswordRequestDTO;
import com.projects.myHR.dto.SecurityQuestionRequestDTO;
import com.projects.myHR.dto.SecurityQuestionResponseDTO;
import com.projects.myHR.enums.MyHRRequestStatus;
import com.projects.myHR.enums.MyHRRoles;
import com.projects.myHR.model.MyHRUser;
import com.projects.myHR.repo.MyHRUserRepo;

import jakarta.persistence.EntityManager;


@Service
public class MyHRUserService {

	private MyHRUserRepo repo;
	private PasswordEncoder encoder;
	private AuthenticationManager authManager;
	private JWTService jwtService;
	
	@jakarta.persistence.PersistenceContext
	private EntityManager entityManager;

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
			user.setSecurityQuestion(userReqDTO.getSecurityQuestion().trim().toLowerCase());
			user.setSecurityAnswer(userReqDTO.getSecurityAnswer().trim().toLowerCase());

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

	public Page<MyHRUserResponseDTO> findAllUsers(int page, int size) {
		Page<MyHRUser> myHRUserPage = repo.findAll(PageRequest.of(page, size));
		return myHRUserPage.map((user)->new MyHRUserResponseDTO(user.getUsername(), user.getRole()));
	}

	public Page<MyHRUserResponseDTO> findAllUsersForHR(int page, int size) {
		Page<MyHRUser> myHRUserPage = repo.findByRoleIn(List.of(MyHRRoles.HR, MyHRRoles.EMPLOYEE), PageRequest.of(page, size));
		return myHRUserPage.map(user->new MyHRUserResponseDTO(user.getUsername(), user.getRole()));
	}

	public Page<MyHRUserResponseDTO> findAllUsersForEmployee(int page, int size) {
		Page<MyHRUser> myHRUserPage = repo.findByRole(MyHRRoles.EMPLOYEE, PageRequest.of(page, size));
		return myHRUserPage.map(user->new MyHRUserResponseDTO(user.getUsername(), user.getRole()));
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

	public MyHRRequestStatus changePassword(ChangePasswordRequestDTO changePasswordRequestDTO) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if(username != null ) {
			MyHRUser user = repo.findByUsername(username);
			if(user != null) {
				if(!encoder.matches(changePasswordRequestDTO.getNewPassword(), user.getPassword())) {
					user.setPassword(encoder.encode(changePasswordRequestDTO.getNewPassword()));
					repo.save(user);					
				}
				return MyHRRequestStatus.SUCCESS;
			}else {
				return MyHRRequestStatus.FAILED;
			}
		}
		return MyHRRequestStatus.FAILED;
	}

	public SecurityQuestionResponseDTO getSecurityQuestion(SecurityQuestionRequestDTO securityQuestionRequestDTO) {
		MyHRUser user = repo.findByUsername(securityQuestionRequestDTO.getUsername());
		if(user!=null) {
			return  new SecurityQuestionResponseDTO(user.getSecurityQuestion(), user.getSecurityAnswer());
		}
		return null;
	}

	public MyHRRequestStatus resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO) {
		MyHRUser user = repo.findByUsername(resetPasswordRequestDTO.getUsername());
		if(user != null && user.getSecurityAnswer().equalsIgnoreCase(resetPasswordRequestDTO.getSecurityAnswer())) {
			if(!encoder.matches(resetPasswordRequestDTO.getPassword(), user.getPassword())) {
				user.setPassword(encoder.encode(resetPasswordRequestDTO.getPassword()));
				repo.save(user);				
			}
			return MyHRRequestStatus.SUCCESS;
		}
		else {			
			return MyHRRequestStatus.FAILED;
		}
	}

	public MyHRRequestStatus updateProfile(ChangeSecurityQuestionRequestDTO reqDTO, MultipartFile profilePicture) throws IOException {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean changed = false;
		if(username != null) {
			MyHRUser user = repo.findByUsername(username);
			if(profilePicture != null && !profilePicture.isEmpty()) {
				user.setProfilePicture(profilePicture.getBytes());
				changed = true;
			}
			if(!user.getSecurityQuestion().equalsIgnoreCase(reqDTO.getSecurityQuestion().trim())) {
				user.setSecurityQuestion(reqDTO.getSecurityQuestion().trim().toLowerCase());
				changed = true;
			}
			if(!user.getSecurityAnswer().equalsIgnoreCase(reqDTO.getSecurityAnswer().trim())) {
				user.setSecurityAnswer(reqDTO.getSecurityAnswer().trim().toLowerCase());
				changed = true;
			}
			if(changed) {
				repo.save(user);
			}
			return MyHRRequestStatus.SUCCESS;
		}else {
			return MyHRRequestStatus.FAILED;
		}
	}

	public MyHRRequestStatus deleteEmployee(String username) {
		MyHRUser user = repo.findByUsername(username);
		if(user != null) {
			repo.deleteById(user.getId());
		}
		return MyHRRequestStatus.SUCCESS;
	}
}
