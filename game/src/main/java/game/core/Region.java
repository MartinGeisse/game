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
	public Iterable<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	/**
	 * Creates a new game object in this region.
	 * 
	 * TODO: I don't like this function. I don't like that game objects
	 * must know their region at all. This was intended so the player's
	 * block map behavior can spawn "bouncing coin" effects. But this doesn't
	 * work at all. And even then, it would be better to give the player
	 * an EffectSpawner that knows the region instead of making each
	 * game object know its region.
	 * 
	 * @return the game object
	 */
	public GameObject createGameObject() {
		GameObject gameObject = new GameObject(this);
		gameObjects.add(gameObject);
		return gameObject;
	}
	
	/**
	 * Removes a game object from this region.
	 * @param gameObject the game object
	 */
	public void removeGameObject(GameObject gameObject) {
		gameObjects.remove(gameObject);
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
		// TODO pass a GameStepProcessor that can copy the arrays into a
		// re-used "clone" array without creating new objects
		List<GameObject> gameObjectsListClone = new ArrayList<>(gameObjects);
		for (GameObject gameObject : gameObjectsListClone) {
			gameObject.handleStep();
		}
	}
	
}
