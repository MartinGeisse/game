/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;


/**
 * The main game engine object.
 */
public class Engine {

	/**
	 * the currentRegion
	 */
	private Region currentRegion;

	/**
	 * Getter method for the currentRegion.
	 * @return the currentRegion
	 */
	public Region getCurrentRegion() {
		return currentRegion;
	}

	/**
	 * Setter method for the currentRegion.
	 * @param currentRegion the currentRegion to set
	 */
	public void setCurrentRegion(Region currentRegion) {
		this.currentRegion = currentRegion;
	}

	/**
	 * Draws the screen contents using OpenGL.
	 */
	public void draw() {
		if (currentRegion != null) {
			currentRegion.draw();
		}
	}

	/**
	 * Handles a game step. This method performs the game logic.
	 */
	public void handleStep() {
		if (currentRegion != null) {
			currentRegion.handleStep();
		}
	}

}
