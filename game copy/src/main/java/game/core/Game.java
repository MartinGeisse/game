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
 * The main game object.
 */
public class Game {

	/**
	 * the currentRegion
	 */
	private Region currentRegion;

	/**
	 * the zoom
	 */
	private float zoom = 1.0f;

	/**
	 * the screenX
	 */
	private float screenX = 0.0f;

	/**
	 * the screenY
	 */
	private float screenY = 0.0f;

	/**
	 * Constructor.
	 */
	public Game() {
	}

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
	 * Getter method for the zoom.
	 * @return the zoom
	 */
	public float getZoom() {
		return zoom;
	}

	/**
	 * Setter method for the zoom.
	 * @param zoom the zoom to set
	 */
	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	/**
	 * Getter method for the screenX.
	 * @return the screenX
	 */
	public float getScreenX() {
		return screenX;
	}

	/**
	 * Setter method for the screenX.
	 * @param screenX the screenX to set
	 */
	public void setScreenX(float screenX) {
		this.screenX = screenX;
	}

	/**
	 * Getter method for the screenY.
	 * @return the screenY
	 */
	public float getScreenY() {
		return screenY;
	}

	/**
	 * Setter method for the screenY.
	 * @param screenY the screenY to set
	 */
	public void setScreenY(float screenY) {
		this.screenY = screenY;
	}

	/**
	 * Getter method for the screen width, in units.
	 * @return the screen width, in units
	 */
	public float getScreenWidthUnits() {
		return 30.0f * Display.getWidth() / Display.getHeight() / zoom;
	}

	/**
	 * Getter method for the screen height, in units.
	 * @return the screen height, in units
	 */
	public float getScreenHeightUnits() {
		return 30.0f / zoom;
	}

	/**
	 * Draws the screen contents using OpenGL.
	 */
	public void draw() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, getScreenWidthUnits(), getScreenHeightUnits(), 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glTranslatef(-screenX, -screenY, 0.0f);
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
