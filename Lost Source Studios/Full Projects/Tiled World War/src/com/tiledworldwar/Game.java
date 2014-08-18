package com.tiledworldwar;

import java.awt.Graphics;

import com.lss.flasher.StateHandler.State;
import com.tiledworldwar.game.World;
import com.tiledworldwar.game.WorldHandler;

@SuppressWarnings("serial")
public class Game extends State {

	public Game(String name) {
		super(name);
		
		WorldHandler.addWorld(new World());
		
		
		
	}

	@Override
	public void update() {
		WorldHandler.update();
	}

	@Override
	public void render(Graphics g) {
		WorldHandler.render(g);
	}

	@Override
	public void selected() {

	}

	@Override
	public void unSelected() {

	}

}
