package com.office.home.security.jwt;

import java.security.Key;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.office.home.model.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@SuppressWarnings("deprecation")
@Component
public class JwtUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	@Value("${murali.office.home.jwtSecret}")
	private String jwtSecret;
	@Value("${murali.office.home.jwtExpirationMs}")
	private int jwtExpirationMs;
	@Value("${murali.office.home.jwtCookieName}")
	private String jwtCookie;
	private Key jwtKey;
	
	@PostConstruct
	private void setKey() {
		this.jwtKey = new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA512");
	}

	public boolean validateJwtToken(String jwt) {
		try {
			Jwts.parserBuilder().setSigningKey(jwtKey).build().parseClaimsJws(jwt);
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
		return Jwts.parserBuilder().setSigningKey(jwtKey).build().parseClaimsJws(jwt).getBody().getSubject();
	}

	public String getJwtFromCookie(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, jwtCookie);
		return (cookie!=null)? cookie.getValue():null;
	}

	/**
	 * get username of principal
	 * generate jwt token from username using generateJwtToken
	 * then generate response cookie from jwt token and return it.
	 * @param principal
	 * @return populated cookie
	 */
	public ResponseCookie generateJwtCookie(UserDetailsImpl principal) {
		String username = principal.getUsername();
		String jwt = generateJwtToken(username);
		ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt)
				.path("/office").maxAge(jwtExpirationMs).httpOnly(true).build();
		return cookie;
	}
	/**
	 * using jwts builder(), we set username as subject
	 * this username is later used to retrieve details of logged in user 
	 * 		and then setting security context while executing future requests 
	 * set the issue and expiry times
	 * sign the claims using sha512 algorithm, and then generate token using compact()
	 * @param principal
	 * @return jwt token with principal as claims
	 */
	private String generateJwtToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
		.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
		.signWith(jwtKey).compact();
	}

	/**
	 * this method is used when a user logs out.
	 * An empty token is created, which is then used to create a clean response cookie
	 * @return clean response cookie
	 */
	public ResponseCookie generateCleanCookie() {
		return ResponseCookie.from(jwtCookie, "").path("/office").httpOnly(true).build();
	}
	
}
