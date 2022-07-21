package com.app.tracker.security.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtTokenUtils {
	private static final long ONE_MILLISECOND = 1000L;

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Value("${jwt.tokenHeader}")
	private String tokenHeader;

	public String extractToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		return token.substring(tokenHead.length());
	}

	public String generateToken(Map<String, Object> claims) {
		log.info("Generating token...");
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.ES512, secret)
				.compact();
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * ONE_MILLISECOND);
	}

	private Date getExpiredDateFromToken(String token) {
		return getClaimsFromToken(token).getExpiration();
	}

	private Boolean isTokenExpired(String token) {
		return getExpiredDateFromToken(token).before(new Date());
	}

	public String getUserNameFromToken(String token) {
		String username;
		try {
			username = getClaimsFromToken(token).getSubject();
		} catch (Exception e) {
			log.error(e.toString());
			username = null;
		}

		return username;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			log.error("Error while trying to extract claims from token:{}", token);
		}
		return claims;
	}

	public boolean validateToken(String token,
								  UserDetails userdetails) {
		String username = getUserNameFromToken(token);
		return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
	}

	public boolean isValidJwtToken(String token) {
		try {
			Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException ex) {
			log.error("Invalid Jwt token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported Jwt token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty");
		}

		return false;
	}
}
