package com.theris.devl.blockchain_based_voting_system.model;

import java.util.ArrayList;
import java.util.List;

public class VotingBlockchainManager {

	private List<Block> blockchain;

	public VotingBlockchainManager() {
		this.blockchain = new ArrayList<>();
		// Adding the genesis block (the first block in a block chain)
		blockchain.add(new Block("Genesis", "None", "0"));
	}

	public Block getLatestBlock() {
		return blockchain.get(blockchain.size() - 1);
	}

	public void addBlock(Block newBlock) {
		newBlock.setPreviousHash(getLatestBlock().getCurrentHash());
		newBlock.setCurrentHash(newBlock.calculateHash());
		this.blockchain.add(newBlock);
	}

	public boolean isBlockchainValid() {
		for (int i = 1; i < blockchain.size(); i++) {
			Block currentBlock = blockchain.get(i);
			Block previousBlock = blockchain.get(i - 1);
			if (!currentBlock.getCurrentHash().equals(currentBlock.calculateHash())) {
				return false;
			}
			if (!currentBlock.getPreviousHash().equals(previousBlock.getCurrentHash())) {
				return false;
			}
		}
		return true;
	}

	public List<Block> getBlockchain() {
		return blockchain;
	}

	public void setBlockchain(List<Block> blockchain) {
		this.blockchain = blockchain;
	}

}
