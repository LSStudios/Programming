package com.tiledworldwar.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Area {

	public Color color;

	public Area() {
		Random r = new Random();

		color = new Color(50, r.nextInt(100) + 100, 50);
	}

	public void update() {

	}

	public static int size = 64;

	public void render(Graphics g, int xx, int yy, boolean flipped) {
		g.setColor(color);
		if (!flipped) {
			int[] x = { xx + size / 2, xx + size, xx };
			int[] y = { yy, yy + size, yy + size };
			g.fillPolygon(x, y, 3);
		} else {
			int[] x = { xx, xx + size, xx + size / 2 };
			int[] y = { yy, yy, yy + size };
			g.fillPolygon(x, y, 3);
		}

	}

}
