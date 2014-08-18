package com.game.main;

import java.awt.Graphics;

public abstract class Mob {

	public String tag;
	public int x;
	public int y;
	
	public abstract void update();
	public abstract void render(Graphics g);
	
}
