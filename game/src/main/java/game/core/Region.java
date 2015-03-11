/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

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
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		for (GameObject gameObject : gameObjects) {
			gameObject.draw();
		}
	}

	/**
	 * Handles a game step. This method performs the game logic.
	 */
	public void handleStep() {
		List<GameObject> gameObjectsListClone = new ArrayList<>(gameObjects);
		for (GameObject gameObject : gameObjectsListClone) {
			gameObject.handleStep();
		}
	}
	
}
