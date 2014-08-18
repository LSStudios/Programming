package com.game.main;

import java.awt.event.KeyEvent;

import com.lss.flasher.LEngine;
import com.lss.flasher.StateHandler.StateHandler;

public class Start extends LEngine {

	public Start(int width, int height, String frameName) {
		super(width, height, frameName);

		key.addKey(KeyEvent.VK_W);
		key.addKey(KeyEvent.VK_A);
		key.addKey(KeyEvent.VK_S);
		key.addKey(KeyEvent.VK_D);

		StateHandler.addState(new Game("GAME"));
		
		StateHandler.setState("GAME");
		
		skipIntro();
		start();
	}

	@Override
	public void update() {
		StateHandler.update();
	}

	@Override
	public void render() {
		StateHandler.render(g);
	}

	public static void main(String[] args) {
		new Start(1024, 720, "Reaping the Benifits! - alpha");

	}

}
