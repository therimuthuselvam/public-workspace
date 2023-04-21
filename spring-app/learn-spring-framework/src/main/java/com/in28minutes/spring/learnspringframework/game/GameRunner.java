package com.in28minutes.spring.learnspringframework.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {

//	private MarioGame game;
	@Autowired
	private GamingConsole game;

//	public GameRunner(MarioGame game) {
//		this.game = game;
//	}
	
//	public GameRunner(GamingConsole game) {
//		this.game = game;
//	}

	public void run() {
		game.up();
		game.down();
		game.left();
		game.right();
	}

}
