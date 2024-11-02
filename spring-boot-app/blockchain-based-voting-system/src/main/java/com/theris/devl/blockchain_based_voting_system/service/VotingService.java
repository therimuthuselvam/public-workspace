package com.theris.devl.blockchain_based_voting_system.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.theris.devl.blockchain_based_voting_system.customexception.CandidateAlreadyRegisteredException;
import com.theris.devl.blockchain_based_voting_system.customexception.CandidatesNotFoundException;
import com.theris.devl.blockchain_based_voting_system.customexception.InvalidCandidateException;
import com.theris.devl.blockchain_based_voting_system.customexception.InvalidVoterException;
import com.theris.devl.blockchain_based_voting_system.customexception.VoterAlreadyRegisteredException;
import com.theris.devl.blockchain_based_voting_system.customexception.VoterAlreadyVoterException;
import com.theris.devl.blockchain_based_voting_system.customexception.VoterNotFoundException;
import com.theris.devl.blockchain_based_voting_system.dto.CandidateTableDTO;
import com.theris.devl.blockchain_based_voting_system.dto.VoteRequestDTO;
import com.theris.devl.blockchain_based_voting_system.dto.VoteResponseDTO;
import com.theris.devl.blockchain_based_voting_system.dto.VoterTableDTO;
import com.theris.devl.blockchain_based_voting_system.entity.CandidateTable;
import com.theris.devl.blockchain_based_voting_system.entity.VoterTable;
import com.theris.devl.blockchain_based_voting_system.model.Block;
import com.theris.devl.blockchain_based_voting_system.model.VotingBlockchainManager;
import com.theris.devl.blockchain_based_voting_system.repository.CandidateRepository;
import com.theris.devl.blockchain_based_voting_system.repository.VoterRepository;
import com.theris.devl.blockchain_based_voting_system.response.ErrorResponse;
import com.theris.devl.blockchain_based_voting_system.response.SuccessResponse;
import com.theris.devl.blockchain_based_voting_system.response.interfce.Response;
import com.theris.devl.blockchain_based_voting_system.utils.JsonUtil;

@Service
public class VotingService {

	@Autowired
	private VoterRepository voterRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(VotingService.class);

	private final String blockchainJSONFilePath;

	private VotingBlockchainManager votingBlockchainManager;

	public VotingService(@Value("${blockchain.json.file.path}") String blockchainJSONFilePath) {
		this.blockchainJSONFilePath = blockchainJSONFilePath;
	}

	public void setBlockChainManager(VotingBlockchainManager votingBlockchainManager) {
		this.votingBlockchainManager = votingBlockchainManager;
	}

	public void setBlockchain(List<Block> blockchain) {
		this.votingBlockchainManager.setBlockchain(blockchain);
	}

	public void saveBlockchainToJson() throws IOException, Exception {
		LOGGER.debug("Execute saveBlockchainToJson() in VotingService");
		JsonUtil.writeToJsonFile(votingBlockchainManager.getBlockchain(), blockchainJSONFilePath); // Save blockchain to
																									// JSON file
		LOGGER.debug("Exit saveBlockchainToJson() in VotingService");
	}

	public List<VoterTable> getVoterList() {
		LOGGER.debug("Execute getVoterList() in VotingService");
		List<VoterTable> voterList = voterRepository.findAll();
		if (voterList.isEmpty()) {
			throw new VoterNotFoundException("No voters found");

		}
		LOGGER.debug("Exit getVoterList() in VotingService");
		return voterList;

	}

	public List<CandidateTable> getCandidateList() {
		LOGGER.debug("Execute getCandidateList() in VotingService");
		List<CandidateTable> candidateList = candidateRepository.findAll();
		if (candidateList.isEmpty()) {
			throw new CandidatesNotFoundException("No candidates found");
		}
		LOGGER.debug("Exit getCandidateList() in VotingService");
		return candidateList;

	}

	public VoteResponseDTO castVote(VoteRequestDTO voteRequestDTO) {
		LOGGER.debug("Execute castVote() in VotingService");

		// Validate voter
		VoterTable voter = voterRepository.findByVoterId(voteRequestDTO.getVoterId());

		if (voter == null) {
			throw new InvalidVoterException("Invalid voter");
		} else if (voter.isHasVoted()) {
			throw new VoterAlreadyVoterException("Voter has already voted!");
		}

		// Verify Candidate
		Optional<CandidateTable> optionalCandidate = candidateRepository.findById(voteRequestDTO.getCandidateId());

		return optionalCandidate.map(candidate -> {
			// Process vote
			String previousHash = votingBlockchainManager.getLatestBlock().getCurrentHash();
			Block newBlock = new Block(voteRequestDTO.getVoterId(), voteRequestDTO.getCandidateId().toString(),
					previousHash);
			votingBlockchainManager.addBlock(newBlock);

			// Mark user as voted
			voter.setHasVoted(true);
			voterRepository.save(voter);

			LOGGER.debug("Exit castVote() in VotingService");
			return new VoteResponseDTO("Vote casted successfully!", voteRequestDTO.getVoterId(),
					voteRequestDTO.getCandidateId(), newBlock.getCurrentHash());
		}).orElseThrow(() -> new InvalidCandidateException("Invalid candidate"));

	}

	public Response validateBlockchain() throws RuntimeException {
		LOGGER.debug("Execute validateBlockchain() in VotingService");
		try {
			if (votingBlockchainManager.isBlockchainValid()) {
				return new SuccessResponse("blockchain is valid!");
			} else {
				return new ErrorResponse("blockchain is NOT valid!");
			}
		} finally {
			LOGGER.debug("Exit validateBlockchain() in VotingService");
		}
	}

	public List<Block> getBlockchain() throws RuntimeException {
		LOGGER.debug("Execute getBlockchain() in VotingService");
		List<Block> blockchain = new ArrayList<>();

		try {
			blockchain = votingBlockchainManager.getBlockchain();
			return blockchain;
		} finally {
			LOGGER.debug("Exit getBlockchain() in VotingService");
		}

	}

	public VoterTable registerVoter(VoterTableDTO newVoter) throws RuntimeException, VoterAlreadyRegisteredException {
		LOGGER.debug("Execute registerVoter() in VotingService");
		try {
			boolean existingVoter = voterRepository.findByVoterId(newVoter.getVoterId()) != null ? true : false;
			if (!existingVoter) {
				VoterTable voter = new VoterTable(newVoter.getFirstName(), newVoter.getLastName(),
						newVoter.getVoterId(), newVoter.isHasVoted());
				VoterTable savedVoter = voterRepository.save(voter);
				return savedVoter;
			} else {
				throw new VoterAlreadyRegisteredException("Existing Voter, so we can not register");
			}

		} finally {
			LOGGER.debug("Exit registerVoter() in VotingService");
		}
	}

	public CandidateTable registerCandidate(CandidateTableDTO newCandidate)
			throws RuntimeException, CandidateAlreadyRegisteredException {
		LOGGER.debug("Execute registerCandidate() in VotingService");
		try {
			boolean existingCandidate = candidateRepository.findByParty(newCandidate.getParty()) != null ? true : false;
			if (!existingCandidate) {
				CandidateTable candidate = new CandidateTable(newCandidate.getName(), newCandidate.getParty());
				CandidateTable savedCandidate = candidateRepository.save(candidate);
				return savedCandidate;
			} else {
				throw new CandidateAlreadyRegisteredException("Existing Candidate, so we can not register");
			}

		} finally {
			LOGGER.debug("Exit registerCandidate() in VotingService");
		}
	}
}
