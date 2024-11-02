package com.theris.devl.blockchain_based_voting_system.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CandidateTableDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String party;

	public CandidateTableDTO(Long id, String name, String party) {
		this.id=id;
		this.name = name;
		this.party = party;
	}
}
