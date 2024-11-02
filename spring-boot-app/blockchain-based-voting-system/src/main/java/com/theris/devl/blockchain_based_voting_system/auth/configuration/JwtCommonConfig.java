package com.theris.devl.blockchain_based_voting_system.auth.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class JwtCommonConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtCommonConfig.class);

	@Bean
	public PasswordEncoder passwordEncoder() {
		LOGGER.debug("passwordEncoder bean is created in JwtCommonConfig");
		return new BCryptPasswordEncoder();
	}
}
