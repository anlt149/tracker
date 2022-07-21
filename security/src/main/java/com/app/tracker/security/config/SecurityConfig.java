package com.app.tracker.security.config;

import com.app.tracker.security.component.JwtAuthenticationFilter;
import com.app.tracker.security.handler.ApiAccessDeniedHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final ApiAccessDeniedHandler apiAccessDeniedHandler;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// Permit all ignored urls
		// TODO

		// Filter chain
		http.authorizeRequests()
				.antMatchers("/api/v1/login")
				.permitAll()
				.antMatchers("/api/v1/*")
				.authenticated()
				.and()
				.logout()
				.permitAll()
				.and()
				.exceptionHandling()
				.accessDeniedHandler(apiAccessDeniedHandler)
				.and()
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
