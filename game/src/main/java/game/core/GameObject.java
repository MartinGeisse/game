/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

import game.engine.util.ClassKeyedContainer;

/**
 * A single object in the game.
 */
public class GameObject {

	/**
	 * the behaviors
	 */
	private final ClassKeyedContainer<Behavior> behaviors = new ClassKeyedContainer<>();
	
	/**
	 * Getter method for the behaviors.
	 * @return the behaviors
	 */
	public ClassKeyedContainer<Behavior> getBehaviors() {
		return behaviors;
	}

	/**
	 * Draws this object using OpenGL.
	 */
	public void draw() {
		for (Behavior behavior : behaviors.getValues()) {
			behavior.draw();
		}
	}

	/**
	 * Handles the game logic for a single game step for this object.
	 */
	public void handleStep() {
		for (Behavior behavior : behaviors.getValues()) {
			behavior.handleStep();
		}
	}

}
