package com.tiledworldwar.game.io;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class WorldMenuObject {

	public String name;

	public WorldMenuObject(String name) {
		this.name = name;
	}

	public void render(int x, int y, int width, int height, Graphics g) {
		g.setColor(new Color(20, 20, 20));
		g.fillRoundRect(x, y, width, height, 20, 20);
		g.setColor(new Color(200, 200, 200));
		g.fillRoundRect(x+5, y+5, width - 10, height - 10, 20, 20);

		g.setColor(new Color(20, 20, 20));
		g.setFont(new Font("Verdana", 1, 20));
		g.drawString(name, x + 15, y + 25);
		
	}

}
