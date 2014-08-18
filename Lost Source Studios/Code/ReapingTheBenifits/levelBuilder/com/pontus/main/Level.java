package com.pontus.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.game.main.level.Tile;
import com.lss.flasher.Input.Mouse;
import com.lss.flasher.MathOperations.Vector2i;
import com.lss.flasher.tools.Loader;

public class Level {

	public Tile[] tiles;

	public String file = "";
	public int width, height, tileSize;

	public com.pontus.main.Tile selectedTile;

	public Level(String file) {
		this.file = file;
		loadLevel();
	}

	public Vector2i selection = new Vector2i(0, 0);

	/**
	 * Update method
	 */
	public void update() {
//		if (((Mouse.getX() + Main.xOff) / Main.tileSize) >= 0)
//			if (((Mouse.getX() + Main.xOff) / Main.tileSize) < width)
//
//				selection.setX(((Mouse.getX() + Main.xOff) / Main.tileSize));
//		if (((Mouse.getY() + Main.yOff) / Main.tileSize) >= 0)
//			if (((Mouse.getY() + Main.yOff) / Main.tileSize) < height)
//
//				selection.setY(((Mouse.getY() + Main.yOff) / Main.tileSize));

		if (((Mouse.getX() * 2 + Main.xOff) / Main.tileSize) >= 0)
			if (((Mouse.getX() * 2 + Main.xOff) / Main.tileSize) < width)

				selection.setX(((Mouse.getX() * 2 + Main.xOff) / Main.tileSize));
		if (((Mouse.getY() * 2 + Main.yOff) / tileSize) >= 0)
			if (((Mouse.getY() * 2 + Main.yOff) / Main.tileSize) < height)

				selection.setY(((Mouse.getY() * 2 + Main.yOff) / Main.tileSize));
		
		
		
		
		System.out.println(selection.getX() + ", " + selection.getY());
		
		if (Mouse.getButton() == 1) {
			try {
				String dir = System.getProperty("user.dir");
				String path = selectedTile.path;

				String relative = path.replace(dir, "");

				relative = relative.replace("\\", "/");
				relative = relative.replace("/res", "");

				Tile tile = new Tile(relative, Loader.loadImage(relative.toString()));
				tile.solid = selectedTile.collision;
				setTile(selection.getX(), selection.getY(), tile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Render method
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				try {
				Tile t = getTile(x, y);
				t.renderEditor(g, x, y);
			} catch(Exception e) {
				}
			}
		}
		
		g.setColor(Color.blue);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				g.drawRect(i * 64 - Main.xOff, j * 64 - Main.yOff, 64, 64);
			}
		}
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public SAXBuilder builder;
	public Document d;

	private int parse(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			System.err.println(s);
		}
		return -1;
	}

	public Element get(List<Element> l, String name) {
		for (Element e : l) {
			if (e.getAttributeValue("name").equals(name)) {
				return e;
			}
		}
		return null;
	}

	public void setTile(int x, int y, Tile tile) {
		tiles[x + y * width] = tile;
	}

	public Tile getTile(int x, int y) {
		return tiles[x + y * width];
	}

	public void loadLevel() {
		builder = new SAXBuilder();
		try {
			d = builder.build(new File(file));
		} catch (Exception e) {
			e.printStackTrace();
		}

		width = parse(d.getRootElement().getAttributeValue("tileswide"));
		height = parse(d.getRootElement().getAttributeValue("tileshigh"));
		tileSize = parse(d.getRootElement().getAttributeValue("tilewidth"));

		/**
		 * All loaded layers, and other general level properties.
		 */
		List<Element> first = d.getRootElement().getChildren();

		List<Element> properties = get(first, "properties").getChildren();
		List<Element> mainLayer = get(first, "tiles").getChildren();

		tiles = new Tile[width * height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Element tile = mainLayer.get(x + y * width);

				if (!tile.getAttributeValue("tile").equals("null")) {
					Image image = Loader.loadImage(tile.getAttributeValue("tile"));
					Tile t = new Tile(tile.getAttributeValue("tile"), image, x, y);
					tiles[x + y * width] = t;

				} else {
					Tile t = new Tile("null", null, x, y);
					tiles[x + y * width] = t;
				}
				// if (parse(collisionTile.getAttributeValue("tile")) != -1) {
				// t.solid = true;
				// }

			}
		}

	}

}
