package com.theris.devl.blockchain_based_voting_system.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VoterTableDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String voterId;
	private boolean hasVoted;

	public VoterTableDTO(Long id, String firstName, String lastName, String voterId, boolean hasVoted) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.voterId = voterId;
		this.hasVoted = hasVoted;
	}

}
