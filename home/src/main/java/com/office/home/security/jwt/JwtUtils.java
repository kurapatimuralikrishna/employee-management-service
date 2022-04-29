package com.office.home.security.jwt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	@Value("${murali.office.home.jwtSecret}")
	private String jwtSecret;
	@Value("${murali.office.home.jwtExpirationMs}")
	private int jwtExpirationMs;
	@Value("${murali.office.home.jwtCookieName}")
	private String jwtCookie;
	
	public boolean validateJwtToken(String jwt) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid jwt signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid jwt token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("Jwt token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("Jwt token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("Jwt claims string is empty: {}", e.getMessage());
		}
		return false;
	}

	public String getUsernameFromToken(String jwt) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
	}

	public String getJwtFromCookie(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, jwtCookie);
		return (cookie!=null)? cookie.getValue():null;
	}
	
}
