package com.theris.devl.blockchain_based_voting_system.auth.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.theris.devl.blockchain_based_voting_system.auth.jwt.JwtAuthenticationFilter;
import com.theris.devl.blockchain_based_voting_system.auth.jwt.JwtH2DataBaseUserDetailsService;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {

	@Autowired
	private JwtH2DataBaseUserDetailsService h2DataBaseUserDetailsService;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${security.enabled:true}")
	private boolean securityEnabled;

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtSecurityConfig.class);
	
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(h2DataBaseUserDetailsService).passwordEncoder(passwordEncoder);
    	LOGGER.debug("authenticationManager bean is created in JwtSecurityConfig");
        return authenticationManagerBuilder.build();
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		if (!securityEnabled) {
			LOGGER.debug("security is disabled in JwtSecurityConfig");
			http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
		} else {
			LOGGER.debug("security is enabled in JwtSecurityConfig");
	        http
            .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity, adjust based on your needs
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/api/vote/login").permitAll() // disabling jwt authentication for login api
            		.requestMatchers("/api/vote/**")
            		.authenticated() // authentication requires for url with /api/vote
            		.anyRequest().permitAll() // authentication is not required for any request
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter before processing requests
            .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable));// Allow iframe embedding -
			// this for h2 db
		}
		LOGGER.debug("securityFilterChain bean is created in JwtSecurityConfig");
		return http.build();
	}
}
