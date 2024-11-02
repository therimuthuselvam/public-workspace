package com.theris.devl.blockchain_based_voting_system.dto;

import com.theris.devl.blockchain_based_voting_system.response.interfce.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VoteResponseDTO implements Response{
    private String message;
    private String voterId;
    private Long candidateId;
    private String blockHash;
}
