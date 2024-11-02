package com.theris.devl.blockchain_based_voting_system.auth.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@PostMapping("/api/vote/login")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
		LOGGER.debug("Execute authenticateUser() in JwtAuthenticationController");
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

			String jwt = jwtUtil.generateToken(authRequest.getUsername());
			return ResponseEntity.ok(new AuthResponse(jwt));
		} catch (AuthenticationException e) {
			LOGGER.error("Invalid Credentials: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
		}finally {
			LOGGER.debug("Exit authenticateUser() in JwtAuthenticationController");
		}
	}
}

@Getter
@Setter
class AuthRequest {
	private String username;
	private String password;
}

@Getter
@Setter
@AllArgsConstructor
class AuthResponse {
	private String jwt;
}
