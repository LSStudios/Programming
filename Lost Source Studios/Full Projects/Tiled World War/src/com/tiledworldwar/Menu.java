package com.tiledworldwar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.lss.flasher.LEngine;
import com.lss.flasher.Input.Mouse;
import com.lss.flasher.MenuGUI.Button.Button;
import com.lss.flasher.MenuGUI.Button.ButtonObject;
import com.lss.flasher.StateHandler.State;
import com.lss.flasher.StateHandler.StateHandler;

@SuppressWarnings("serial")
public class Menu extends State {

	public boolean openedForTheFirstTime = true;

	public Button b;

	public Menu(String name) {
		super(name);

		b = new Button("MENU");

		b.addButton("play", 300, 300, 150, 75, Color.black, Color.GRAY);
		b.addButton("options", 300, 300 + 75 + 20, 150, 75, Color.black, Color.GRAY);
		b.addButton("exit", 300, 300 + 75 + 75 + 40, 150, 75, Color.black, Color.GRAY);
		b.setShow("play", false);
		b.setShow("options", false);
		b.setShow("exit", false);
	}

	int menuOffX = 0;
	int menuOffY = 0;

	// Values to hold each button size and position.
	int playButtonSize = 0;
	int optionsButtonSize = 0;
	int exitButtonSize = 0;

	boolean pressPlay = false;
	boolean pressOptions = false;
	boolean pressExit = false;

	public boolean play = false;
	static int es = 0;


	public static int menuSpeed = 5 + es;

	boolean done = false;
	
	@Override
	public void update() {

		if (menuOffX <= 0 && !done && !play) {
			if (Menu.es <= 15)
				Menu.es += 1;
			menuOffX += Menu.menuSpeed;
		} else if (!play) {
			Menu.es = 0;
			menuOffX = 0;
			done = true;
		}
		
		b.update();

		// The speed at the buttons scale.
		int speed = 3;

		// The offset for the buttons

		b.getButton("play").x = 300 + menuOffX;
		b.getButton("options").x = 300 + menuOffX;
		b.getButton("exit").x = 300 + menuOffX;

		// Play button logic
		if (b.getButton("play").mouseOverButton) {

			if (Mouse.getButton() == 1 && !pressPlay) {
				pressPlay = true;
				play = true;
			} else if (Mouse.getButton() != 1 && pressPlay) {
				pressPlay = false;
			}
			
			if (playButtonSize < 20) {
				playButtonSize += speed;
			}
		} else if (playButtonSize >= 0) {
			playButtonSize -= speed;
		}

		// Options button logic
		if (b.getButton("options").mouseOverButton) {
			if (optionsButtonSize < 20) {
				optionsButtonSize += speed;
			}
		} else if (optionsButtonSize >= 0) {
			optionsButtonSize -= speed;
		}

		// Exit button logic
		if (b.getButton("exit").mouseOverButton) {
			
			if (Mouse.getButton() == 1) {
				LEngine.onExit();
			}
			
			if (exitButtonSize < 20) {
				exitButtonSize += speed;
			}
		} else if (exitButtonSize >= 0) {
			exitButtonSize -= speed;
		}

		menuSpeed = 5 + es;
		if (play) {
			es += 1;
			menuOffX -= menuSpeed;
		}
		
		if (menuOffX < -500) {
			StateHandler.setState("SAVES");
		}
		
		// This gets called when the menu first startup, use for animation etc.
		if (openedForTheFirstTime) {
			openedForTheFirstTime = false;
		}

	}
	

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, LEngine.WIDTH, LEngine.HEIGHT);

		g.setColor(new Color(20, 20, 20));
		ButtonObject p = b.getButton("play");
		g.fillRoundRect(p.x - playButtonSize, p.y - playButtonSize, p.xa + playButtonSize * 2, p.ya + playButtonSize * 2, 10, 10);

		ButtonObject o = b.getButton("options");
		g.fillRoundRect(o.x - optionsButtonSize, o.y - optionsButtonSize, o.xa + optionsButtonSize * 2, o.ya + optionsButtonSize * 2, 10, 10);

		ButtonObject e = b.getButton("exit");
		g.fillRoundRect(e.x - exitButtonSize, e.y - exitButtonSize, e.xa + exitButtonSize * 2, e.ya + exitButtonSize * 2, 10, 10);

		
		
		g.setColor(new Color(230, 230, 230));
		g.setFont(new Font("Verdana", 1, 30 + playButtonSize));
		g.drawString("Play", p.x + 40 - playButtonSize, p.y + 50);

		g.setFont(new Font("Verdana", 1, 30 + optionsButtonSize / 2));
		g.drawString("Options", o.x + 15 - optionsButtonSize, o.y + 50);
		g.setFont(new Font("Verdana", 1, 30 + exitButtonSize));
		g.drawString("Exit", e.x + 40 - exitButtonSize, e.y + 50);

		b.render(g);
	}

	@Override
	public void selected() {
		play = false;
		menuOffX = -450;
		menuOffY = 0; done = false;
	}

	@Override
	public void unSelected() {

	}
	

}
