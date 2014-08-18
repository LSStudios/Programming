package com.tiledworldwar.game;

import java.awt.Graphics;
import java.util.ArrayList;


/**
 * World handler keeps track of every loaded world, there will probably only be one world per save
 * but this is thinking ahead...
 * @author Pontus Wirsching 
 * @since 2014-08-17
 */
public class WorldHandler {

	/**
	 * Holds all loaded worlds in the array list
	 */
	public static ArrayList<World> worlds = new ArrayList<>();
	
	/**
	 * The index of the currently selected world
	 */
	public static int selected = 0;
	
	/**
	 * Adds another world to the loaded list witch can be
	 * accessed by changing the selected world to it's index.
	 * @param world - the new world to add.
	 */
	public static void addWorld(World world) {
		worlds.add(world);
	}
	
	/**
	 * Returns a world from the loaded worlds list
	 * depending on the index parsed in.
	 * @param index - index of the world to get.
	 * @return A world specified by the index above.
	 */
	public static World getWorld(int index) {
		return worlds.get(index);
	}
	
	/**
	 * Returns the currently active and selected world.
	 * This is the only world that should for example
	 * get rendered and updated.
	 * @return The active world.
	 */
	public static World getSelected() {
		return getWorld(selected);
	}
	
	/**
	 * Updates the selected world.
	 */
	public static void update() {
		getSelected().update();
	}
	
	/**
	 * Renders the selected world.
	 * @param g - LEngine graphics variable.
	 */
	public static void render(Graphics g) {
		getSelected().render(g);
	}
	
}
