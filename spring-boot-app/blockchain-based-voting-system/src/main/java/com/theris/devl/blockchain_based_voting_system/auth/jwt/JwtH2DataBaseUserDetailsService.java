package com.theris.devl.blockchain_based_voting_system.auth.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.theris.devl.blockchain_based_voting_system.entity.VoterTable;
import com.theris.devl.blockchain_based_voting_system.repository.VoterRepository;

@Service
public class JwtH2DataBaseUserDetailsService implements UserDetailsService {

	@Autowired
    private VoterRepository voterRepository;
    
	@Autowired
    private PasswordEncoder passwordEncoder;
    
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtH2DataBaseUserDetailsService.class);
	
    @Override
    public UserDetails loadUserByUsername(String voterId) throws UsernameNotFoundException {
    	LOGGER.debug("Execute loadUserByUsername() in JwtH2DataBaseUserDetailsService");
        // Fetch voter from the repository based on voterId
        VoterTable voter = voterRepository.findByVoterId(voterId);

        if (voter == null) {
            throw new UsernameNotFoundException("Voter not found: " + voterId);
        }
        LOGGER.debug("Valid User");
        LOGGER.debug("Exit loadUserByUsername() in JwtH2DataBaseUserDetailsService");
        return User.withUsername(voter.getVoterId()) // Use voter ID as username
                .password(passwordEncoder.encode(voter.getVoterId())) //  Use voter ID as password and encode it
                .build();
    }
}

