package com.projects.myHR.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projects.myHR.model.MyHRUser;
import com.projects.myHR.model.MyHRUserDetails;
import com.projects.myHR.repo.MyHRUserRepo;

@Service
public class MyHRUserDetailsService implements UserDetailsService{

	private MyHRUserRepo repo;
	
	
	public MyHRUserDetailsService(MyHRUserRepo repo) {
		super();
		this.repo = repo;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyHRUser user = repo.findByUsername(username);
		return new MyHRUserDetails(user);
	}
	
	
}
