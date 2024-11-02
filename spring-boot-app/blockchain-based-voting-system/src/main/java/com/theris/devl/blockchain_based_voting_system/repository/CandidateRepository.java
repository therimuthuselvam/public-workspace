package com.theris.devl.blockchain_based_voting_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theris.devl.blockchain_based_voting_system.entity.CandidateTable;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateTable, Long> {

	CandidateTable findByName(String name);
	
	CandidateTable findByParty(String party);

}
