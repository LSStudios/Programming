package com.tiledworldwar.game;

import java.awt.Graphics;

public class World {

	public Area[] areas;

	public int width, height;

	public World() {

		width = 8;
		height = 6;

		areas = new Area[width * height];
		for (int i = 0; i < areas.length; i++) {
			areas[i] = new Area();
		}
		
	}

	public void update() {
		for (int i = 0; i < areas.length; i++) {
			if (areas[i] != null)
				areas[i].update();
		}
		
	

		
	}

	public void render(Graphics g) {
		
		int xOff = 200;
		int yOff = 200;
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (areas[x + y * width] != null) {
					
					if ((y % 2) == 1) {
						if ((x % 2) == 1) {
							areas[x + y * width].render(g, (x * 31) + xOff - 31, (y * 64) + yOff, false);
						} else {
							areas[x + y * width].render(g, (x * 31) + xOff - 31, (y * 64) + yOff, true);
						}
					} else {
						if ((x % 2) == 1) {
							areas[x + y * width].render(g, (x * 31) + xOff, (y * 64) + yOff, false);
						} else {
							areas[x + y * width].render(g, (x * 31) + xOff, (y * 64) + yOff, true);
						}
					}
					
					
					
					
				}
			}
		}
	}

}
