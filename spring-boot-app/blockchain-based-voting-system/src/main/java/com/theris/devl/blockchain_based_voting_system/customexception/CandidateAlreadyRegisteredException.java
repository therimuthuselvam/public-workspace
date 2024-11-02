package com.theris.devl.blockchain_based_voting_system.customexception;

public class CandidateAlreadyRegisteredException extends Exception {
	private static final long serialVersionUID = 1L;

	public CandidateAlreadyRegisteredException(String message) {
		super(message);
	}
}
