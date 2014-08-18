package com.game.main.level;

import java.awt.Graphics;
import java.util.ArrayList;

public class LevelHandler {

	public static ArrayList<Level> levels = new ArrayList<>();
	
	public static int selected = 0;
	
	public static void add(Level l) {
		levels.add(l);
	}
	
	public static void select(int index) {
		selected = index;
	}
	
	public static void next() {
		selected++;
	}
	
	public static Level getLevel(int index) {
		return levels.get(index);
	}
	
	public static Level getSelected() {
		return getLevel(selected);
	}
	
	public static void update() {
		getSelected().update();
	}
	
	public static void render(Graphics g) {
		getSelected().render(g);
	}
	
}
