/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Util;


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
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth() << 8, Display.getHeight() << 8, 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		if (currentRegion != null) {
			currentRegion.draw();
		}
		Util.checkGLError();
	}

	/**
	 * Handles a game step. This method performs the game logic.
	 */
	public void handleStep() {
		Keyboard.poll();
		Mouse.poll();
		if (currentRegion != null) {
			currentRegion.handleStep();
		}
	}

}
