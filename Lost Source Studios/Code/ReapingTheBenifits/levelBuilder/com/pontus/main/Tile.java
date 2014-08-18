package com.pontus.main;

public class Tile {

	public String path = "";
	public boolean collision = false;
	public String name;
	public int tileSize;
	
	
	public Tile(String path, boolean collision, String name, int size) {
		this.path = path;
		tileSize = size;
		this.name = name;
		this.collision = collision;
	}
	
}
