package com.theris.devl.blockchain_based_voting_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteRequestDTO {

	private String voterId;
	private Long candidateId;

}
