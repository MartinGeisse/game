/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

/**
 * This interface represents a "building block" for a game object.
 */
public interface Behavior {

	/**
	 * Called after this behavior gets attached to a game object.
	 * @param target the game object to which this behavior got attached
	 */
	public void onAttach(GameObject target);

	/**
	 * Called after this behavior gets detached from a game object.
	 * @param target the game object from which this behavior got detached
	 */
	public void onDetach(GameObject target);

	/**
	 * Establishes the general render state. This includes, first and foremost, the
	 * transformation.
	 */
	public void prepareRenderState();

	/**
	 * Draws the object that owns this behavior object (or part of it) using OpenGL.
	 */
	public void draw();
	
	/**
	 * Handles the game logic for a single game step for this behavior.
	 */
	public void handleStep();

}
