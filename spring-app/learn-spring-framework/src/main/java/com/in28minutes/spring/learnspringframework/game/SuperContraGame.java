package com.in28minutes.spring.learnspringframework.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class SuperContraGame implements GamingConsole {

	public void up() {
		System.out.println("SuperContraGame jump");
	}

	public void down() {
		System.out.println("SuperContraGame down in to the hole");
	}

	public void left() {
		System.out.println("SuperContraGame stop");
	}

	public void right() {
		System.out.println("SuperContraGame accelerate");
	}

}
