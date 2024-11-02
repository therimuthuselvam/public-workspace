package com.theris.devl.blockchain_based_voting_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theris.devl.blockchain_based_voting_system.entity.VoterTable;

@Repository
public interface VoterRepository extends JpaRepository<VoterTable, Long> {

	VoterTable findByVoterId(String voterId);
}
