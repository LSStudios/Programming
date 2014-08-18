package com.tiledworldwar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import com.lss.flasher.LEngine;
import com.lss.flasher.Input.Mouse;
import com.lss.flasher.Menu.TextField;
import com.lss.flasher.Menu.GUI.LButton;
import com.lss.flasher.MenuGUI.Button.Button;
import com.lss.flasher.MenuGUI.Button.ButtonObject;
import com.lss.flasher.StateHandler.State;
import com.lss.flasher.StateHandler.StateHandler;
import com.tiledworldwar.game.gui.NewSaveWindow;
import com.tiledworldwar.game.io.WorldMenuObject;

@SuppressWarnings("serial")
public class Saves extends State {

	
	
	public ArrayList<WorldMenuObject> wmo = new ArrayList<>();

	public void addWorldObject(WorldMenuObject wmo) {
		this.wmo.add(wmo);
	}

	public Button b;

	public TextField name;
	
	public NewSaveWindow nsw = new NewSaveWindow(LEngine.WIDTH / 2 - 300, LEngine.HEIGHT / 2 - 200, 600, 400);
	
	public Saves(String name) {
		super(name);

		this.name = new TextField(50, 50, 300, 30);
		nsw.add(this.name);
		nsw.show = false;
		nsw.movable = false;
		b = new Button("SAVES");

		b.addButton("new", LEngine.WIDTH / 2 - 175 + menuOffX, 300, 150, 75, Color.black, Color.GRAY);
		b.addButton("play", LEngine.WIDTH / 2 + 25 + menuOffX, 300, 150, 75, Color.black, Color.GRAY);
		b.addButton("back", 50 + menuOffX, 50, 100, 50, Color.black, Color.GRAY);
		b.addButton("back2", 50 + menuOffX, 50, 100, 50, Color.black, Color.GRAY);
		b.addButton("ok", 50 + menuOffX, 50, 100, 50, Color.black, Color.GRAY);

	}

	int menuOffX = LEngine.WIDTH + 200;
	int menuOffY = 0;

	int newButtonSize = 0;
	int playButtonSize = 0;
	int backButtonSize = 0;
	int okSize = 0;

	boolean pressNew = false;
	boolean pressBack = false;
	boolean pressPlay = false;

	boolean newSave = false;

	boolean done = false;

	boolean back = false;

	int newSaveBack = 0;

	@Override
	public void update() {

		

		
		
		Menu.menuSpeed = 5 + Menu.es;

		if (menuOffX >= 0 && !done && !back) {
			if (Menu.es <= 15)
				Menu.es += 1;
			menuOffX -= Menu.menuSpeed;
		} else if (!back) {
			Menu.es = 0;
			menuOffX = 0;
			done = true;
		}

		if (back) {
			if (Menu.es <= 15)
				Menu.es += 1;
			menuOffX += Menu.menuSpeed;
		}

		if (menuOffX > LEngine.WIDTH && back) {
			StateHandler.setState("MENU");
		}

		b.getButton("new").x = LEngine.WIDTH / 2 - 175 + menuOffX;
		b.getButton("new").y = LEngine.HEIGHT - 110;

		b.getButton("play").x = LEngine.WIDTH / 2 + 25 + menuOffX;
		b.getButton("play").y = LEngine.HEIGHT - 110;

		b.getButton("back").x = 5 + 10 + menuOffX;
		b.getButton("back").y = 25 + 10;

		b.getButton("back2").x = 665 + menuOffX;
		b.getButton("back2").y = 465;
		
		b.getButton("ok").x = 665 + menuOffX - 120;
		b.getButton("ok").y = 465;
		
		b.update();

		int speed = 3;

		if (!newSave || newButtonSize != 0) {
			newSaveBack = 0;

			// New button logic
			if (b.getButton("new").mouseOverButton) {

				if (Mouse.getButton() == 1 && !pressNew) {
					pressNew = true;

					if (wmo.size() < 5) {
						newSave = true;
					}

				} else if (Mouse.getButton() != 1 && pressNew) {
					pressNew = false;
				}

				if (newButtonSize < 20) {
					newButtonSize += speed;
				}
			} else if (newButtonSize >= 0) {
				newButtonSize -= speed;
			}

			// Play button logic
			if (b.getButton("play").mouseOverButton) {

				if (Mouse.getButton() == 1 && !pressPlay) {
					pressPlay = true;
					
					
					StateHandler.setState("GAME");
					
				} else if (Mouse.getButton() != 1 && pressPlay) {
					pressPlay = false;
				}

				if (playButtonSize < 20) {
					playButtonSize += speed;
				}
			} else if (playButtonSize >= 0) {
				playButtonSize -= speed;
			}

			// Back button logic
			if (b.getButton("back").mouseOverButton) {

				if (Mouse.getButton() == 1 && !pressBack) {
					pressBack = true;
					back = true;
				} else if (Mouse.getButton() != 1 && pressBack) {
					pressBack = false;
				}

				if (backButtonSize < 5) {
					backButtonSize += speed;
				}
			} else if (backButtonSize >= 0) {
				backButtonSize -= speed;
			}
		}
		
		
		if (newSave){

			
			
			nsw.update();

			
			
			// Back button logic
			if (b.getButton("back2").mouseOverButton) {

				if (Mouse.getButton() == 1 && !pressBack) {
					pressBack = true;
					
					newSave = false;
					
				} else if (Mouse.getButton() != 1 && pressBack) {
					pressBack = false;
				}

				if (newSaveBack < 5) {
					newSaveBack += speed;
				}
			} else if (newSaveBack >= 0) {
				newSaveBack -= speed;
			}
			
			// Back button logic
			if (b.getButton("ok").mouseOverButton) {

				if (Mouse.getButton() == 1 && !pressBack) {
					pressBack = true;
					
					addWorldObject(new WorldMenuObject(name.text));
					newSave = false;
					name.text = "";
					
				} else if (Mouse.getButton() != 1 && pressBack) {
					pressBack = false;
				}

				if (okSize < 5) {
					okSize += speed;
				}
			} else if (okSize >= 0) {
				okSize -= speed;
			}

		}


	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, LEngine.WIDTH, LEngine.HEIGHT);

		g.setColor(new Color(20, 20, 20));
		ButtonObject n = b.getButton("new");
		g.fillRoundRect(n.x - newButtonSize, n.y - newButtonSize, n.xa + newButtonSize * 2, n.ya + newButtonSize * 2, 10, 10);

		g.setColor(new Color(20, 20, 20));
		ButtonObject p = b.getButton("play");
		g.fillRoundRect(p.x - playButtonSize, p.y - playButtonSize, p.xa + playButtonSize * 2, p.ya + playButtonSize * 2, 10, 10);

		g.setColor(new Color(20, 20, 20));
		ButtonObject bb = b.getButton("back");
		g.fillRoundRect(bb.x - backButtonSize, bb.y - backButtonSize, bb.xa + backButtonSize * 2, bb.ya + backButtonSize * 2, 10, 10);


		
		g.setColor(new Color(230, 230, 230));
		g.setFont(new Font("Verdana", 1, 30 + newButtonSize));
		g.drawString("New", n.x + 40 - newButtonSize, n.y + 50);

		g.setFont(new Font("Verdana", 1, 30 + playButtonSize / 2));
		g.drawString("Play", p.x + 38 - playButtonSize, p.y + 50);

		g.setFont(new Font("Verdana", 1, 30 + backButtonSize / 2));
		g.drawString("Back", bb.x + 10 - backButtonSize, bb.y + 35);

		
		for (WorldMenuObject o : wmo) {
			int index = wmo.indexOf(o);
			o.render(LEngine.WIDTH / 2 - 175 + menuOffX, 50 + (index * 100), 350, 95, g);
		}
		
		
		if (newSave) {

			g.setColor(new Color(20, 20, 20));
			g.fillRoundRect(LEngine.WIDTH / 2 - 300, LEngine.HEIGHT / 2 - 200, 600, 400, 20, 20);
			g.setColor(new Color(200, 200, 200));
			g.fillRoundRect(LEngine.WIDTH / 2 - 275, LEngine.HEIGHT / 2 - 175, 275 * 2, 175 * 2, 20, 20);
			
			
			g.setColor(new Color(20, 20, 20));
			g.setFont(new Font("Verdana", 1, 30));
			g.drawString("Save name: ", nsw.x + 40, nsw.y + 75);
			
			nsw.components.get(0).x = 250 + nsw.x;
			
			nsw.render(g);
			
			
			g.setColor(new Color(20, 20, 20));
			ButtonObject o = b.getButton("ok");
			g.fillRoundRect(o.x - okSize, o.y - okSize, o.xa + okSize * 2, o.ya + okSize * 2, 10, 10);

			g.setColor(new Color(230, 230, 230));
			g.setFont(new Font("Verdana", 1, 30 + okSize / 2));
			g.drawString("Ok", o.x + 28 - okSize / 2, o.y + 35);
			
			g.setColor(new Color(20, 20, 20));
			ButtonObject bbb = b.getButton("back2");
			g.fillRoundRect(bbb.x - newSaveBack, bbb.y - newSaveBack, bbb.xa + newSaveBack * 2, bbb.ya + newSaveBack * 2, 10, 10);

			g.setColor(new Color(230, 230, 230));
			g.setFont(new Font("Verdana", 1, 30 + newSaveBack / 2));
			g.drawString("Back", bbb.x + 10 - newSaveBack, bbb.y + 35);

		}

	}

	@Override
	public void selected() {

	}

	@Override
	public void unSelected() {
		menuOffX = LEngine.WIDTH + 200;
		menuOffY = 0;
		back = false;
		done = false;
	}

}
