package com.game.main.level;

import java.awt.Graphics;
import java.awt.Image;

import com.game.main.Game;
import com.pontus.main.Main;

public class Tile {

	public Image image;
	public String name;
	public int x, y;
	public boolean solid = false;

	public Tile(String name, Image image, int x, int y) {
		this.name = name;
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	public Tile(String name, Image image) {
		this.name = name;
		this.image = image;
	}

	public void render(Graphics g) {
		g.drawImage(image, x * Game.tileSize - Game.xOff, y * Game.tileSize - Game.yOff, Game.tileSize, Game.tileSize, null);
	}
	
	public void renderEditor(Graphics g) {
		g.drawImage(image, x * Main.tileSize - Main.xOff, y * Main.tileSize - Main.yOff, Main.tileSize, Main.tileSize, null);
	}

	public void renderEditor(Graphics g, int x, int y) {
		g.drawImage(image, x * Main.tileSize - Main.xOff, y * Main.tileSize - Main.yOff, Main.tileSize, Main.tileSize, null);
	}

}
