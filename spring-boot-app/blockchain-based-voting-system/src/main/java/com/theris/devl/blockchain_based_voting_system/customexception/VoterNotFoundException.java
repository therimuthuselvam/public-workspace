package com.theris.devl.blockchain_based_voting_system.customexception;

public class VoterNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VoterNotFoundException(String message) {
		super(message);
	}

}
