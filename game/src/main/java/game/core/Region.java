/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

import java.util.ArrayList;
import java.util.List;

/**
 * A region in the game that contains a chunk of "currently active" content. Regions
 * allow to swap such chunks quickly, e.g. when entering a different area in the game.
 */
public class Region {

	/**
	 * the gameObjects
	 */
	private final List<GameObject> gameObjects = new ArrayList<GameObject>();
	
	/**
	 * Getter method for the gameObjects.
	 * @return the gameObjects
	 */
	public List<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	/**
	 * Draws the screen contents using OpenGL.
	 */
	public void draw() {
		for (GameObject gameObject : gameObjects) {
			gameObject.draw();
		}
	}

	/**
	 * Handles a game step. This method performs the game logic.
	 */
	public void handleStep() {
		for (GameObject gameObject : gameObjects) {
			gameObject.handleStep();
		}
	}
	
}
