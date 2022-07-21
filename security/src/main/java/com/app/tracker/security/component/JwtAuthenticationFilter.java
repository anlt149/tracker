package com.app.tracker.security.component;

import com.app.tracker.security.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {

		String token = jwtTokenUtils.extractToken(request);

		if (StringUtils.hasText(token) && jwtTokenUtils.isValidJwtToken(token)) {
			// Validate
			String username = jwtTokenUtils.getUserNameFromToken(token);
			log.info("Checking username:{}", username);
			if (!username.isBlank()) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				if (jwtTokenUtils.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken authentication =
							new UsernamePasswordAuthenticationToken(
									userDetails,
									null,
									userDetails.getAuthorities());

					log.info("Authenticated user:{}", username);

					// Store authenticated user into context holder.
					SecurityContextHolder.getContext()
							.setAuthentication(authentication);
				}
			}


			filterChain.doFilter(request, response);
		}
	}
}
