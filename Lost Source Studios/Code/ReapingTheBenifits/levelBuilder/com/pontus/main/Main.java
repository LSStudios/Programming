package com.pontus.main;

import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.MenuBar;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.JToolBar.Separator;
import javax.swing.UIManager;

import com.lss.flasher.LEngine;
import com.lss.flasher.Input.Mouse;
import com.lss.flasher.MathOperations.Vector2i;

public class Main extends LEngine {

	public static int xOff, yOff;
	public static int tileSize = 64;

	public static MenuBar menu = new MenuBar();

	public static Menu tiles = new Menu("Tiles");

	public static Level level;

	public TileWindow t;

	public Main(int width, int height, String frameName) {
		super(width, height, frameName);

		t = new TileWindow(frame);

		key.addKey(KeyEvent.VK_W);
		key.addKey(KeyEvent.VK_A);
		key.addKey(KeyEvent.VK_S);
		key.addKey(KeyEvent.VK_D);

		Menu file = new Menu("File");

		MenuItem newLevel = new MenuItem("New");
		MenuItem loadLevel = new MenuItem("Load");
		MenuItem saveLevel = new MenuItem("Save");

		saveLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (level != null)
					FileGenerator.generateFile(level.width, level.height, level.tileSize, level.file, level.tiles);
			}
		});

		final JFileChooser chooser = new JFileChooser();

		newLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewLevel nl = new NewLevel(frame);
				

			}
		});

		loadLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				chooser.setCurrentDirectory(new File("./res"));
				int returnVal = chooser.showOpenDialog(frame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String path;
					try {
						path = chooser.getSelectedFile().getAbsolutePath();

						level = new Level(path);

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		});

		file.add(newLevel);
		file.add(loadLevel);
		file.add(saveLevel);

		menu.add(file);

		MenuItem add = new MenuItem("Add Tile..");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewTile t = new NewTile(frame);
			}
		});
		tiles.add(add);

		tiles.addSeparator();

		MenuItem showTiles = new MenuItem("Show/Hide Tile Window");
		showTiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				t.setVisible(!t.isVisible());
			}
		});
		tiles.add(showTiles);

		menu.add(tiles);

		frame.setMenuBar(menu);

		String path = "G:\\ReapingTheBenifits\\res\\grass.png";
		int size = 32;
		String n = "Grass";
		boolean collision = false;

		TileWindow.tiles.put(n, new Tile(path, collision, n, size));
		TileWindow.listModel.addElement(n);
		TileWindow.list.setModel(TileWindow.listModel);

		skipIntro();
		start();
	}

	public int speed = 10;

	public Vector2i selection = new Vector2i(0, 0);

	@Override
	public void update() {

		speed = 20;
		
		tileSize = 64;
		
		if (key.getKey(0)) {
			yOff -= speed;
		}
		if (key.getKey(1)) {
			xOff -= speed;
		}
		if (key.getKey(2)) {
			yOff += speed;
		}
		if (key.getKey(3)) {
			xOff += speed;
		}

		if (level != null) {

			level.update();
			if (((Mouse.getX() * 2 + xOff) / tileSize) >= 0)
				if (((Mouse.getX() * 2 + xOff) / tileSize) < level.width)

					selection.setX(((Mouse.getX() * 2 + xOff) / tileSize));
			if (((Mouse.getY() * 2 + yOff) / tileSize) >= 0)
				if (((Mouse.getY() * 2 + yOff) / tileSize) < level.height)

					selection.setY(((Mouse.getY() * 2 + yOff) / tileSize));

//			if (((Mouse.getX() + xOff) / tileSize) >= 0)
//				if (((Mouse.getX() + xOff) / tileSize) < level.width)
//
//					selection.setX(((Mouse.getX() + xOff) / tileSize));
//			if (((Mouse.getY() + yOff) / tileSize) >= 0)
//				if (((Mouse.getY() + yOff) / tileSize) < level.height)
//
//					selection.setY(((Mouse.getY() + yOff) / tileSize));
			
			if (Mouse.getButton() == 3) {
				level.setTile(selection.getX(), selection.getY(), null);
			}

		}
	}

	@Override
	public void render() {

		g.scale(0.5, 0.5);
		
		if (level != null) {
			level.render(g);
			g.setColor(Color.RED);
			g.drawRect(selection.getX() * tileSize - xOff, selection.getY() * tileSize - yOff, tileSize, tileSize);
			g.drawRect(selection.getX() * tileSize - xOff + 1, selection.getY() * tileSize - yOff + 1, tileSize - 2, tileSize - 2);
			g.drawRect(selection.getX() * tileSize - xOff + 2, selection.getY() * tileSize - yOff + 2, tileSize - 4, tileSize - 4);

		}

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}
		new Main(1024, 720, "Level Builder");
	}

}
