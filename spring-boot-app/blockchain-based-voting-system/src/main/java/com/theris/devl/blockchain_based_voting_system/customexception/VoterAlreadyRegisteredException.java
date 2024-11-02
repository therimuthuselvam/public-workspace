package com.theris.devl.blockchain_based_voting_system.customexception;

public class VoterAlreadyRegisteredException extends Exception {
	private static final long serialVersionUID = 1L;

	public VoterAlreadyRegisteredException(String message) {
		super(message);
	}
}
