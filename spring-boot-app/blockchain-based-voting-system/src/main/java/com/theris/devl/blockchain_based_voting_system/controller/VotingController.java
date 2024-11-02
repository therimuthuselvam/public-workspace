package com.theris.devl.blockchain_based_voting_system.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.theris.devl.blockchain_based_voting_system.response.ErrorResponse;
import com.theris.devl.blockchain_based_voting_system.response.SuccessResponse;
import com.theris.devl.blockchain_based_voting_system.response.interfce.Response;
import com.theris.devl.blockchain_based_voting_system.service.VotingService;

@RestController
@RequestMapping("/api/vote")
public class VotingController {

	@Autowired
	private VotingService votingService;

	private static final Logger LOGGER = LoggerFactory.getLogger(VotingController.class);

	@GetMapping("/getCandidateList")
	public ResponseEntity<?> getCandidateList() {
		LOGGER.debug("Execute getCandidateList() in VoteController");
		try {
			List<CandidateTable> candidateList = votingService.getCandidateList();
			LOGGER.info("getCandidateList() in VoteController executed successfully! with {} candidates",
					candidateList.size());
			return ResponseEntity.ok(candidateList); // 200
		} catch (CandidatesNotFoundException e) {
			LOGGER.error("{}", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage())); // 404
		} catch (Exception e) {
			LOGGER.error("An internal error occurred while fetching candidates: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse("An internal error occurred while fetching candidates")); // 500
		} finally {
			LOGGER.debug("Exit getCandidateList() in VoteController");
		}
	}

	@GetMapping("/getVoterList")
	public ResponseEntity<?> getVoterList() {
		LOGGER.debug("Execute getVoterList() in VoteController");
		try {
			List<VoterTable> voterList = votingService.getVoterList();
			LOGGER.info("getVoterList() in VoteController executed successfully! with {} voters", voterList.size());
			return ResponseEntity.ok(voterList); // 200
		} catch (VoterNotFoundException e) {
			LOGGER.error("{}", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage())); // 404
		} catch (Exception e) {
			LOGGER.error("An internal error occurred while fetching voters: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse("An internal error occurred while fetching voters")); // 500
		} finally {
			LOGGER.debug("Exit getVoterList() in VoteController");
		}
	}

	@PostMapping("/castVote")
	public ResponseEntity<Response> castVote(@RequestBody VoteRequestDTO voteRequestDTO) {
		LOGGER.debug("Execute castVote() in VoteController");
		try {
			VoteResponseDTO voteResponseDTO = votingService.castVote(voteRequestDTO);
			LOGGER.info("{}", voteResponseDTO.getMessage());
			return ResponseEntity.ok(voteResponseDTO); // 200
		} catch (InvalidCandidateException e) {
			LOGGER.error("{}", e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage())); // 401
		} catch (InvalidVoterException e) {
			LOGGER.error("{}", e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage())); // 401
		} catch (VoterAlreadyVoterException e) {
			LOGGER.error("{}", e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage())); // 409
		} catch (Exception e) {
			LOGGER.error("An internal error occurred while casting vote: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse("An internal error occurred while casting vote")); // 500
		} finally {
			LOGGER.debug("Exit castVote() in VoteController");
		}
	}

	@GetMapping("/validateBlockchain")
	public ResponseEntity<Response> validateBlockchain() {
		LOGGER.debug("Execute validateBlockchain() in VoteController");
		try {
			Response response = votingService.validateBlockchain();
			if (response instanceof SuccessResponse) {
				LOGGER.info("{}", response.getMessage());
				return ResponseEntity.status(HttpStatus.OK).body(response); // 200
			} else {
				LOGGER.error("{}", response.getMessage());
				return ResponseEntity.status(HttpStatus.CONFLICT).body(response);// 409
			}
		} catch (Exception e) {
			LOGGER.error("An unexpected error occurred while validating the blockchain: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse("An unexpected error occurred while validating the blockchain."));
		} finally {
			LOGGER.debug("Exit validateBlockchain() in VoteController");
		}

	}

	@GetMapping("/getBlockchain")
	public ResponseEntity<?> getBlockchain() {
		LOGGER.debug("Execute getBlockchain() in VoteController");
		try {
			List<Block> blockchain = votingService.getBlockchain();
			LOGGER.info("Fetched blockchain: ---->");
			if (blockchain != null && !blockchain.isEmpty()) {
				blockchain.forEach(block -> LOGGER.debug("{}", block));
			}
			return ResponseEntity.status(HttpStatus.OK).body(blockchain);
		} catch (Exception e) {
			LOGGER.error("An unexpected error occurred while retrieving the blockchain: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse("An unexpected error occurred while retrieving the blockchain."));
		} finally {
			LOGGER.debug("Exit getBlockchain() in VoteController");
		}
	}

	@PostMapping("/registerVoter")
	public ResponseEntity<?> registerVoter(@RequestBody VoterTableDTO newVoter) {
		LOGGER.debug("Execute registerVoter() in VoteController");
		try {
			VoterTable savedVoter = votingService.registerVoter(newVoter);
			VoterTableDTO voter = new VoterTableDTO(savedVoter.getId(), savedVoter.getFirstName(),
					savedVoter.getLastName(), savedVoter.getVoterId(), savedVoter.isHasVoted());
			LOGGER.info("Voter is saved");
			return ResponseEntity.status(HttpStatus.OK).body(voter);
		} catch (VoterAlreadyRegisteredException e) {
			LOGGER.error("{}", e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage())); // 409
		} catch (Exception e) {
			LOGGER.error("An unexpected error occurred while saving the voter: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse("An unexpected error occurred while saving the voter."));
		} finally {
			LOGGER.debug("Exit registerVoter() in VoteController");
		}
	}

	@PostMapping("/registerCandidate")
	public ResponseEntity<?> registerCandidate(@RequestBody CandidateTableDTO newCandidate) {
		LOGGER.debug("Execute registerCandidate() in VoteController");
		try {
			CandidateTable savedCandidate = votingService.registerCandidate(newCandidate);
			CandidateTableDTO candidate = new CandidateTableDTO(savedCandidate.getId(), savedCandidate.getName(),
					savedCandidate.getParty());
			LOGGER.info("Candidate is saved");
			return ResponseEntity.status(HttpStatus.OK).body(candidate);
		} catch (CandidateAlreadyRegisteredException e) {
			LOGGER.error("{}", e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage())); // 409
		} catch (Exception e) {
			LOGGER.error("An unexpected error occurred while saving the canidate: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse("An unexpected error occurred while saving the candidate."));
		} finally {
			LOGGER.debug("Exit registerCandidate() in VoteController");
		}
	}

	@PostMapping("/saveBlockchainToJson")
	public ResponseEntity<Response> saveBlockchainToJson() {
		try {
			LOGGER.debug("Execute saveBlockchainToJson() in VoteController");
			votingService.saveBlockchainToJson();
			Response successResponse = new SuccessResponse("Blockchain saved to blockchain.json file successfully.");
			LOGGER.info("Saved blockchain to json");
			return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(successResponse.getMessage()));
		} catch (IOException e) {
			LOGGER.error("Failed to save blokchain to blockchain.json file", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErrorResponse("Failed to save blokchain to blockchain.json file"));
		} catch (Exception e) {
			LOGGER.error("An unexpected error occurred while saving json to blockchain.json: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse("An unexpected error occurred while saving json to blockchain.json: {}")); // 500
		} finally {
			LOGGER.debug("Exit saveBlockchainToJson() in VoteController");
		}
	}
}
