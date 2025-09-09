package com.projects.myHR.filter;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projects.myHR.model.MyHRUser;
import com.projects.myHR.repo.MyHRUserRepo;
import com.projects.myHR.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter{

	private JWTService jwtService;
	private ApplicationContext context;

	public JWTFilter(JWTService jwtService, ApplicationContext context) {
		super();
		this.jwtService = jwtService;
		this.context = context;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		
		String username=null;
		String token=null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username=jwtService.extractUsername(token);
		}
		
		MyHRUser user = context.getBean(MyHRUserRepo.class).findByUsername(username);
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null && user != null) {
			UserDetails userDetails = context.getBean(UserDetailsService.class).loadUserByUsername(username);
			if(jwtService.validate(token)) {
				UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				upaToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upaToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

	
}
