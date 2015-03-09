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
	 * 
	 * @param target the game object from which this behavior got detached
	 */
	public void onBehaviorAttached(GameObject target);

	/**
	 * Called after this behavior gets detached from a game object.
	 * 
	 * @param target the game object from which this behavior got detached
	 */
	public void onBehaviorDetached(GameObject target);

	/**
	 * Establishes the general render state. This includes, first and foremost, the
	 * transformation.
	 * 
	 * @param target the game object from which this behavior got detached
	 */
	public void prepareRenderState(GameObject target);

	/**
	 * Draws the object that owns this behavior object (or part of it) using OpenGL.
	 * 
	 * @param target the game object from which this behavior got detached
	 */
	public void draw(GameObject target);
	
	/**
	 * Handles the game logic for a single game step for this behavior.
	 * 
	 * @param target the game object from which this behavior got detached
	 */
	public void handleStep(GameObject target);

}
