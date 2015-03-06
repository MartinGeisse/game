/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.gfx;

import org.lwjgl.opengl.GL11;

import game.core.AbstractBehavior;
import game.engine.gfx.RgbaColor;

/**
 * Draws a colored rectangle to represent an object.
 */
public final class DrawRectangleBehavior extends AbstractBehavior {

	/**
	 * the color
	 */
	private RgbaColor color;

	/**
	 * Constructor.
	 */
	public DrawRectangleBehavior() {
		this(new RgbaColor(255, 255, 255));
	}
	
	/**
	 * Constructor.
	 * @param color the color
	 */
	public DrawRectangleBehavior(RgbaColor color) {
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see game.core.AbstractBehavior#draw()
	 */
	@Override
	public void draw() {
		color.glColor();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2i(10 << 8, 10 << 8);
		GL11.glVertex2i(10 << 8, -10 << 8);
		GL11.glVertex2i(-10 << 8, -10 << 8);
		GL11.glVertex2i(-10 << 8, 10 << 8);
		GL11.glEnd();
	}
	
}
