package com.app.tracker.security.config;

import com.app.tracker.security.component.JwtAuthenticationFilter;
import com.app.tracker.security.handler.ApiAccessDeniedHandler;
import com.app.tracker.security.utils.JwtTokenUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CommonConfig {
	@Bean
	public JwtTokenUtils jwtTokenUtils() {return new JwtTokenUtils();}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {return new JwtAuthenticationFilter();}

	@Bean
	public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

	@Bean
	public ApiAccessDeniedHandler apiAccessDeniedHandler() {return new ApiAccessDeniedHandler();}
}
