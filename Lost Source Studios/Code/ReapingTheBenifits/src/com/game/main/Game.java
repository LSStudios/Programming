package com.game.main;

import java.awt.Graphics;

import com.game.main.level.Level;
import com.game.main.level.LevelHandler;
import com.lss.flasher.LEngine;
import com.lss.flasher.StateHandler.State;

@SuppressWarnings("serial")
public class Game extends State {

	public static int tileSize = 64;
	
	public static int xOff, yOff;
	
	public static Player player;
	
	public Game(String name) {
		super(name);
		
		player = new Player();
		
		LevelHandler.add(new Level("res/town.xml"));
		
	}

	@Override
	public void update() {
		
		xOff = player.x - LEngine.WIDTH / 2;
		yOff = player.y - LEngine.HEIGHT / 2;
		
		LevelHandler.update();
		player.update();
	}

	@Override
	public void render(Graphics g) {
		LevelHandler.render(g);
		player.render(g);
		
		Gui.drawHealthBar(g, 10, 30);
		Gui.drawMoney(g, -10, 620);
	}

	@Override
	public void selected() {

	}

	@Override
	public void unSelected() {

	}

}
