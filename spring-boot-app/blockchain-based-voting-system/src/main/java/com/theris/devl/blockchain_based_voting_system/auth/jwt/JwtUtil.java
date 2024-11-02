package com.theris.devl.blockchain_based_voting_system.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

	private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	@Value("${jwt.token.expiration.time.in.seconds}")
	private final long expirationTime;

	public JwtUtil(@Value("${jwt.token.expiration.time.in.seconds}") long expirationTime) {
		this.expirationTime = expirationTime;
	}

	// Generate a token for a given username
	public String generateToken(String username) {
		try {
			LOGGER.debug("Execute generateToken() in JwtUtil");
			Date now = new Date();
			Date expiryDate = new Date(now.getTime() + expirationTime);

			return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiryDate).signWith(secretKey)
					.compact();
		} finally {
			LOGGER.debug("Exit generateToken() in JwtUtil");
		}

	}

	// Extract the username from the token
	public String getUsernameFromToken(String token) {
		try {
			LOGGER.debug("Execute getUsernameFromToken() in JwtUtil");
			Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
			return claims.getSubject();
		} finally {
			LOGGER.debug("Exit getUsernameFromToken() in JwtUtil");
		}
	}

	// Validate token (checks expiration and signature)
	public boolean validateToken(String token, String username) {
		try {
			LOGGER.debug("Execute validateToken() in JwtUtil");
			String tokenUsername = getUsernameFromToken(token);
			return (tokenUsername.equals(username) && !isTokenExpired(token));
		} finally {
			LOGGER.debug("Exit validateToken() in JwtUtil");
		}

	}

	// Check if the token is expired
	private boolean isTokenExpired(String token) {
		try {
			LOGGER.debug("Execute isTokenExpired() in JwtUtil");
			Date expiration = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody()
					.getExpiration();
			return expiration.before(new Date());
		} finally {
			LOGGER.debug("Exit isTokenExpired() in JwtUtil");
		}
	}
}
