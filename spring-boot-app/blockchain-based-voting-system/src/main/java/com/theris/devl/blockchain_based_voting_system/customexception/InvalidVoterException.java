package com.theris.devl.blockchain_based_voting_system.customexception;

public class InvalidVoterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidVoterException(String message) {
		super(message);
	}

}
