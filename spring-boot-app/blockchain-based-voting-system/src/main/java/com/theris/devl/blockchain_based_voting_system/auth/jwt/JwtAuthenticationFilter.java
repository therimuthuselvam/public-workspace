package com.theris.devl.blockchain_based_voting_system.auth.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private JwtH2DataBaseUserDetailsService jwtH2DataBaseUserDetailsService;

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		LOGGER.debug("Execute doFilterInternal() in JwtAuthenticationFilter");
		String authHeader = request.getHeader("Authorization");
		try {
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				String jwtToken = authHeader.substring(7); // removing Bearer from token
				String username = jwtUtil.getUsernameFromToken(jwtToken);

				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = jwtH2DataBaseUserDetailsService.loadUserByUsername(username);

					if (jwtUtil.validateToken(jwtToken, userDetails.getUsername())) {
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				}
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
			response.setContentType("application/json");
			response.getWriter().write("{\"message\": \"JWT token has expired. Please log in again.\"}");
			LOGGER.error("JWT token has expired. Please log in again.", e);
		} catch (SignatureException e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
			response.setContentType("application/json");
			response.getWriter().write("{\"message\": \"JWT signature is invalid. Please log in again.\"}");
			LOGGER.error("JWT signature is invalid. Please log in again.", e);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
			response.setContentType("application/json");
			response.getWriter().write("{\"message\": \"An unexpected error occurred. Please try again later.\"}");
			LOGGER.error("An unexpected error occurred. Please try again later.", e);
		} finally {
			LOGGER.debug("Exit doFilterInternal() in JwtAuthenticationFilter");
		}

	}
}
