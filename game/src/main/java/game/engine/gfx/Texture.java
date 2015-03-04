/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package game.engine.gfx;

import org.lwjgl.opengl.GL11;

/**
 * Represents a texture and provides OpenGL texture handling functions.
 */
public final class Texture {

	/**
	 * the slickTexture
	 */
	private final org.newdawn.slick.opengl.Texture slickTexture;
	
	/**
	 * Constructor.
	 * @param slickTexture the wrapped Slick texture
	 */
	public Texture(final org.newdawn.slick.opengl.Texture slickTexture) {
		this.slickTexture = slickTexture;
	}

	/**
	 * Calls glBindTexture() on this texture.
	 */
	public void glBindTexture() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, slickTexture.getTextureID());
	}
	
	/**
	 * Getter method for the width.
	 * @return the width
	 */
	public int getWidth() {
		return slickTexture.getImageWidth();
	}
	
	/**
	 * Getter method for the height.
	 * @return the height
	 */
	public int getHeight() {
		return slickTexture.getImageHeight();
	}
	
}
