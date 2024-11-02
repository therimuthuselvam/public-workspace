package com.theris.devl.blockchain_based_voting_system.configuration;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.theris.devl.blockchain_based_voting_system.entity.CandidateTable;
import com.theris.devl.blockchain_based_voting_system.entity.VoterTable;
import com.theris.devl.blockchain_based_voting_system.model.Block;
import com.theris.devl.blockchain_based_voting_system.model.VotingBlockchainManager;
import com.theris.devl.blockchain_based_voting_system.repository.CandidateRepository;
import com.theris.devl.blockchain_based_voting_system.repository.VoterRepository;
import com.theris.devl.blockchain_based_voting_system.service.VotingService;
import com.theris.devl.blockchain_based_voting_system.utils.JsonUtil;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Configuration
public class AppInitAndDestroyConfig {

	VotingBlockchainManager votingBlockchainManager;

	@Autowired
	private VotingService votingService;

	@Autowired
	private VoterRepository voterRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(AppInitAndDestroyConfig.class);

	private final String blockchainJSONFilePath;

	private final String voterTableJSONFilePath;

	private final String candidateTableJSONFilePath;

	public AppInitAndDestroyConfig(@Value("${blockchain.json.file.path}") String blockchainJSONFilePath,
			@Value("${voter.table.json.file.path}") String voterTableJSONFilePath,
			@Value("${candidate.table.json.file.path}") String candidateTableJSONFilePath) {
		this.blockchainJSONFilePath = blockchainJSONFilePath;
		this.voterTableJSONFilePath = voterTableJSONFilePath;
		this.candidateTableJSONFilePath = candidateTableJSONFilePath;
	}

	@PostConstruct
	public void init() {
		LOGGER.debug("Execute init() in AppInitAndDestroyConfig");
		LoadBlockChainJson();
		LoadVoterTableJson();
		LoadCandidateTableJson();
		LOGGER.debug("Exit init() in AppInitAndDestroyConfig");
	}

	private void LoadBlockChainJson() {
		try {
			LOGGER.debug("Execute LoadBlockChainJson() in AppInitAndDestroyConfig");
			this.votingBlockchainManager = new VotingBlockchainManager();
			List<Block> blockchain = JsonUtil.readJson(blockchainJSONFilePath, Block.class); // Load
																								// blockchain
																								// from
																								// JSON file
			if (blockchain != null && !blockchain.isEmpty()) {
				votingService.setBlockChainManager(votingBlockchainManager);
				votingService.setBlockchain(blockchain);
				LOGGER.info("Loaded existing blockchain from blockchain.json.");
			} else {
				votingService.setBlockChainManager(votingBlockchainManager);
				LOGGER.info("No existing blockchain found in blockchain.json, so starting fresh.");
			}
		} catch (IOException e) {
			votingService.setBlockChainManager(votingBlockchainManager);
			LOGGER.error("Failed to read blockchain from blockchain.json: {}", e.getMessage());
		} catch (Exception e) {
			votingService.setBlockChainManager(votingBlockchainManager);
			LOGGER.error("An unexpected error occurred while reading json from blockchain.json: {}", e.getMessage());
		} finally {
			LOGGER.debug("Exit LoadBlockChainJson() in AppInitAndDestroyConfig");
		}
	}

	private void LoadVoterTableJson() {
		try {
			LOGGER.debug("Execute LoadVoterTableJson() in AppInitAndDestroyConfig");
			List<VoterTable> voters = JsonUtil.readJson(voterTableJSONFilePath, VoterTable.class); // Load
																									// voter
																									// table
			// from JSON file
			if (voters != null && !voters.isEmpty()) {
				voterRepository.saveAll(voters);
				LOGGER.info("Loaded existing data from {} into H2 database", voterTableJSONFilePath);
			} else {
				LOGGER.info("No existing data found in {}", voterTableJSONFilePath);
			}
		} catch (IOException e) {
			LOGGER.error("Failed to load data from {} into H2 database: {}", voterTableJSONFilePath, e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error("An unexpected error occurred while loading data from {} into H2 database: {}",
					voterTableJSONFilePath, e.getMessage(), e);
		} finally {
			LOGGER.debug("Exit LoadVoterTableJson() in AppInitAndDestroyConfig");
		}
	}

	private void LoadCandidateTableJson() {
		try {
			LOGGER.debug("Execute LoadCandidateTableJson() in AppInitAndDestroyConfig");
			List<CandidateTable> candidates = JsonUtil.readJson(candidateTableJSONFilePath, CandidateTable.class); // Load
																													// voter
																													// table
																													// from
																													// JSON
																													// file
			if (candidates != null && !candidates.isEmpty()) {
				candidateRepository.saveAll(candidates);
				LOGGER.info("Loaded existing data from {} into H2 database", candidateTableJSONFilePath);
			} else {
				LOGGER.info("No existing data found in {}", candidateTableJSONFilePath);
			}
		} catch (IOException e) {
			LOGGER.error("Failed to load data from {} into H2 database: {}", candidateTableJSONFilePath, e.getMessage(),
					e);
		} catch (Exception e) {
			LOGGER.error("An unexpected error occurred while loading data from {} into H2 database: {}",
					candidateTableJSONFilePath, e.getMessage(), e);
		} finally {
			LOGGER.debug("Exit LoadCandidateTableJson() in AppInitAndDestroyConfig");
		}
	}

	@PreDestroy
	public void destroy() {
		LOGGER.debug("Execute destroy() in AppInitAndDestroyConfig");
		try {
			votingService.saveBlockchainToJson();
			LOGGER.info("Saved blockchain to json");
			saveVoterTableToJson();
			saveCandidateTableToJson();
		} catch (IOException e) {
			LOGGER.error("Failed to save blokchain to blockchain.json file", e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error("An unexpected error occurred while saving json to blockchain.json: {}", e.getMessage(), e);
		} finally {
			LOGGER.debug("Exit destroy() in AppInitAndDestroyConfig");
		}
	}

	private void saveVoterTableToJson() {
		LOGGER.debug("Execute saveVoterTableToJson() in AppInitAndDestroyConfig");
		try {
			List<VoterTable> voters = voterRepository.findAll();
			JsonUtil.writeToJsonFile(voters, voterTableJSONFilePath);
			LOGGER.info("Saved Voter Table to json");
		} catch (IOException e) {
			LOGGER.error("Failed to save json to {}: {}", voterTableJSONFilePath, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("An unexpected error occurred while saving json to {}: {}", voterTableJSONFilePath,
					e.getMessage());
		} finally {
			LOGGER.debug("Exit saveVoterTableToJson() in AppInitAndDestroyConfig");
		}
	}

	private void saveCandidateTableToJson() {
		LOGGER.debug("Execute saveCandidateTableToJson() in AppInitAndDestroyConfig");
		try {
			List<CandidateTable> candidates = candidateRepository.findAll();
			JsonUtil.writeToJsonFile(candidates, candidateTableJSONFilePath);
			LOGGER.info("Saved Candidate Table to json");
		} catch (IOException e) {
			LOGGER.error("Failed to save json to {}: {}", candidateTableJSONFilePath, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("An unexpected error occurred while saving json to {}: {}", candidateTableJSONFilePath,
					e.getMessage());
		} finally {
			LOGGER.debug("Exit saveCandidateTableToJson() in AppInitAndDestroyConfig");
		}
	}
}
