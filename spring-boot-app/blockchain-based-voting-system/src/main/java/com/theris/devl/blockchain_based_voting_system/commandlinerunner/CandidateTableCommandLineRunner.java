package com.theris.devl.blockchain_based_voting_system.commandlinerunner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.theris.devl.blockchain_based_voting_system.entity.CandidateTable;
import com.theris.devl.blockchain_based_voting_system.repository.CandidateRepository;

@Component
public class CandidateTableCommandLineRunner implements CommandLineRunner {

	@Autowired
	CandidateRepository candidateRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CandidateTableCommandLineRunner.class);

	@Override
	public void run(String... args) throws Exception {
		LOGGER.debug("Execute run() in CandidateTableCommandLineRunner");

		List<CandidateTable> candidates = candidateRepository.findAll();

		if (candidates.isEmpty()) {
			candidateRepository.saveAll(List.of(new CandidateTable("Narendra Modi", "BJP"),
					new CandidateTable("Rahul Gandhi", "Congress")));
			candidates = candidateRepository.findAll();
			candidates.forEach(candidate -> LOGGER.info("candidate {} from {} is inserted", candidate.getName(),
					candidate.getParty()));
		} else {
			LOGGER.info("Candidate Table data is loaded from Json so skipped initialization");
			candidates.forEach(candidate -> LOGGER.info("candidate {} from {} is present in the table",
					candidate.getName(), candidate.getParty()));
		}

		LOGGER.debug("Exit run() in CandidateTableCommandLineRunner");
	}

}
