package com.theris.devl.blockchain_based_voting_system.customexception;

public class VoterAlreadyVoterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VoterAlreadyVoterException(String message) {
		super(message);
	}

}
