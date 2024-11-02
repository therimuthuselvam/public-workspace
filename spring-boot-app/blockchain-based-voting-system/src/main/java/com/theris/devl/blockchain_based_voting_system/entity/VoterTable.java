package com.theris.devl.blockchain_based_voting_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VoterTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private String voterId;
	private boolean hasVoted;

	public VoterTable(String firstName, String lastName, String voterId, boolean hasVoted) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.voterId = voterId;
		this.hasVoted = hasVoted;
	}

}
