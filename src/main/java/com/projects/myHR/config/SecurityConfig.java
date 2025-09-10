package com.projects.myHR.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projects.myHR.filter.JWTFilter;
import com.projects.myHR.service.MyHRUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private MyHRUserDetailsService myHRUserDetailsService;
	private JWTFilter jwtFilter;
	

	public SecurityConfig(MyHRUserDetailsService myHRUserDetailsService, JWTFilter jwtFilter) {
		super();
		this.myHRUserDetailsService = myHRUserDetailsService;
		this.jwtFilter = jwtFilter;
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//		UserDetails admin= User
//				.withUsername("admin")
//				.password(encoder.encode("Admin@123"))
//				.roles("ADMIN")
//				.build();
//		UserDetails hr = User
//				.withUsername("hr")
//				.password(encoder.encode("Hr@123"))
//				.roles("HR")
//				.build();
//		UserDetails employee = User
//				.withUsername("employee")
//				.password(encoder.encode("Emp@123"))
//				.roles("EMPLOYEE")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin, hr, employee);
//	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	
	@Bean
	public AuthenticationProvider authenticationProvider(PasswordEncoder encoder) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(myHRUserDetailsService);
		provider.setPasswordEncoder(encoder);
		return provider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.cors(Customizer.withDefaults())
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(auth->auth
						.requestMatchers("/login").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/hr/**").hasAnyRole("ADMIN", "HR")
						.requestMatchers("/employee/**").hasAnyRole("ADMIN","HR","EMPLOYEE")
						.anyRequest().authenticated()
						)
//				.httpBasic(Customizer.withDefaults())
//				.formLogin(Customizer.withDefaults())
				.sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}
