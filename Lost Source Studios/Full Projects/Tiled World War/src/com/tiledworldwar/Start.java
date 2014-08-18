package com.tiledworldwar;

import java.io.File;

import com.lss.flasher.LEngine;
import com.lss.flasher.StateHandler.StateHandler;

/**
 * "Tiled World War" is a game created by Pontus Wirsching, © Lost Source Studios - All rights reserved.
 * The game is a Real Time Strategy or RTS game witch means you control multiple units to complete the game.
 * As I'm writing this the game idea is still in early stages but hopefully as we go it will grow.
 * 
 * @author Pontus Wirsching
 * @since Started: 2014-08-17, Finnished: ????-??-??
 */
public class Start extends LEngine {

	
	
	public static String s = File.separator;
	public static String PATH = System.getenv("APPDATA") + s + "LostSourceStudios" + s + "TiledWorldWar";
	
	
	public Start(int width, int height, String frameName) {
		super(width, height, frameName);
		
		System.out.println("\nPath: " + PATH);

		if (!new File(PATH).exists()) {
			
			System.err.println("Game path not found, creating a new one now.");
			new File(PATH).mkdirs();
		}
		
		if (new File(PATH + s + "saves").exists() == false) {
			
			System.err.println("Creating saves folder..");
			
			new File(PATH + s + "saves").mkdirs();
			
		}
		
		
		enable(LE_ANTI_ALIASING);
		
		StateHandler.addState(new Game("GAME"));
		StateHandler.addState(new Menu("MENU"));
		StateHandler.addState(new Saves("SAVES"));

		StateHandler.setState("MENU");
		
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
		new Start(1000,700,"Tiled World War - ALPHA");
	}
}
