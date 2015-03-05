/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

/**
 * This interface represents a "building block" for a game object.
 */
public interface Behavior {

	/**
	 * Draws the object that owns this behavior object (or part of it) using OpenGL.
	 */
	public void draw();
	
	/**
	 * Handles the game logic for a single game step for this behavior.
	 */
	public void handleStep();

}
