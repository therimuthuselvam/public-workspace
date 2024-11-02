package com.theris.devl.blockchain_based_voting_system.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Block {

	private String previousHash;
	private String currentHash;
	private String voterId;
	private String candidateId;
	private LocalDateTime timestamp;

	public Block(String voterId, String candidateId, String previousHash) {
		this.voterId = voterId;
		this.candidateId = candidateId;
		this.timestamp = LocalDateTime.now();
		this.previousHash = previousHash;
		this.currentHash = calculateHash();
	}

	// This hash is essential for maintaining the uniqueness, integrity and security of the
	// blockchain, ensuring that any alterations to the data can be easily detected.
	public String calculateHash() {
		String dataToHash = previousHash + voterId + candidateId + timestamp.toString();
		return Integer.toHexString(dataToHash.hashCode()); // hashes are often expressed in hexadecimal format for
															// readability
	}

}
