/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

/**
 * This interface represents a "building block" for a game object.
 * 
 * Each behavior has an ID that is used to obtain it from a game
 * object. Two different behaviors on the same game object cannot
 * have the same ID.
 */
public interface Behavior {

	/**
	 * Getter method for the ID of this behavior.
	 * 
	 * @return the ID
	 */
	public String getId();
	
	/**
	 * Called after this behavior gets attached to a game object.
	 * 
	 * @param target the game object from which this behavior got detached
	 */
	public void onAttach(GameObject target);

	/**
	 * Called after this behavior gets detached from a game object.
	 * 
	 * @param target the game object from which this behavior got detached
	 */
	public void onDetach(GameObject target);

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
