package com.projects.myHR.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class NoCacheFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse res = (HttpServletResponse)response;
		
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, max-age=0");
		res.setHeader("Pragma", "no-cache");
		res.setHeader("Expires", "0");
		chain.doFilter(request, response);

	}

	
	
}
