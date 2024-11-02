package com.theris.devl.blockchain_based_voting_system.customexception;

public class InvalidCandidateException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidCandidateException(String message) {
		super(message);
	}
}
