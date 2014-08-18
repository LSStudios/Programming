package com.game.main.level;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.List;

import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.lss.flasher.tools.Loader;

public class Level {

	public String file = "";
	public int width, height, tileSize;

	public Tile[] tiles;

	public Level(String file) {
		this.file = file;
		loadLevel();
	}

	public void update() {

	}

	public void render(Graphics g) {
		for (Tile t : tiles) {
			t.render(g);
		}
		// g.setColor(Color.blue);
		// for (int i = 0; i < width; i++) {
		// for (int j = 0; j < height; j++) {
		// g.drawRect(i * 64 - Game.xOff, j * 64 - Game.yOff, 64, 64);
		// }
		// }
	}

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

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return null;
		else
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
					try {
						t.solid = tile.getAttribute("collision").getBooleanValue();
					} catch (DataConversionException e) {
						e.printStackTrace();
					}
					tiles[x + y * width] = t;

				} else {
					Tile t = new Tile(tile.getAttributeValue("tile"), null, x, y);
					tiles[x + y * width] = t;
				}
				// if (parse(collisionTile.getAttributeValue("tile")) != -1) {
				// t.solid = true;
				// }

			}
		}

	}

}
