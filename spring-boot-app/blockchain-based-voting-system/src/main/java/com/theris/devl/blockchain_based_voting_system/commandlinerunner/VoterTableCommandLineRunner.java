package com.theris.devl.blockchain_based_voting_system.commandlinerunner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.theris.devl.blockchain_based_voting_system.entity.VoterTable;
import com.theris.devl.blockchain_based_voting_system.repository.VoterRepository;

@Component
public class VoterTableCommandLineRunner implements CommandLineRunner {

	@Autowired
	VoterRepository voterRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(VoterTableCommandLineRunner.class);

	@Override
	public void run(String... args) throws Exception {
		LOGGER.debug("Execute run() in VoterTableCommandLineRunner");

		List<VoterTable> voterList = voterRepository.findAll();

		if (voterList.isEmpty()) {
			voterRepository.saveAll(List.of(new VoterTable("Theri", "Muthu Selvam", "100", false),
					new VoterTable("Mahatma", "Gandhi", "101", false)));
			voterList = voterRepository.findAll();
			voterList.forEach(
					voter -> LOGGER.info("voter {} {} is inserted", voter.getFirstName(), voter.getLastName()));
		} else {
			LOGGER.info("Voter Table data is loaded from Json so skipped initialization");
			voterList.forEach(voter -> LOGGER.info("voter {} {} is present in the table", voter.getFirstName(),
					voter.getLastName()));
		}

		LOGGER.debug("Exit run() in VoterTableCommandLineRunner");

	}

}
