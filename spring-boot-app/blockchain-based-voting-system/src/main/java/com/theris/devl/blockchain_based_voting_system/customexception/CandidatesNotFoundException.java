package com.theris.devl.blockchain_based_voting_system.customexception;

public class CandidatesNotFoundException extends RuntimeException {
	 private static final long serialVersionUID = 1L;

	public CandidatesNotFoundException(String message) {
	        super(message);
	    }
}
