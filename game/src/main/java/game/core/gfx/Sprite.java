/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.gfx;

import org.lwjgl.opengl.GL11;

import game.core.GameObject;
import game.engine.gfx.Texture;

/**
 * Contains the information for a textured quad that represents
 * a game object. In concrete terms, this class stores a texture
 * as well as the extents of the quad from the "hotspot", i.e.
 * the position of the game object.
 * 
 * A sprite acting as a {@link SpriteProvider} just returns itself.
 */
public final class Sprite implements SpriteProvider {

	/**
	 * the texture
	 */
	private final Texture texture;

	/**
	 * the leftExtent
	 */
	private final int leftExtent;

	/**
	 * the rightExtent
	 */
	private final int rightExtent;

	/**
	 * the topExtent
	 */
	private final int topExtent;

	/**
	 * the bottomExtent
	 */
	private final int bottomExtent;

	/**
	 * Constructor.
	 * @param texture the texture
	 * @param leftExtent the extent to the left
	 * @param rightExtent the extent to the right
	 * @param topExtent the extent to the top
	 * @param bottomExtent the extent to the bottom
	 */
	public Sprite(Texture texture, int leftExtent, int rightExtent, int topExtent, int bottomExtent) {
		this.texture = texture;
		this.leftExtent = leftExtent;
		this.rightExtent = rightExtent;
		this.topExtent = topExtent;
		this.bottomExtent = bottomExtent;
	}

	/**
	 * Getter method for the texture.
	 * @return the texture
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * Getter method for the leftExtent.
	 * @return the leftExtent
	 */
	public int getLeftExtent() {
		return leftExtent;
	}

	/**
	 * Getter method for the rightExtent.
	 * @return the rightExtent
	 */
	public int getRightExtent() {
		return rightExtent;
	}

	/**
	 * Getter method for the topExtent.
	 * @return the topExtent
	 */
	public int getTopExtent() {
		return topExtent;
	}

	/**
	 * Getter method for the bottomExtent.
	 * @return the bottomExtent
	 */
	public int getBottomExtent() {
		return bottomExtent;
	}

	/**
	 * Calls glBindTexture() on the sprite texture.
	 */
	public void glBindTexture() {
		texture.glBindTexture();
	}

	/**
	 * Calls glTexCoord() / glVertex() on the sprite vertices, assuming the hotspot is at (0, 0).
	 */
	public void glVertices() {
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex2i(-leftExtent << 8, -topExtent << 8);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex2i(rightExtent << 8, -topExtent << 8);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex2i(rightExtent << 8, bottomExtent << 8);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex2i(-leftExtent << 8, bottomExtent << 8);
	}

	/**
	 * Draws this sprite at the current origin.
	 */
	public void draw() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		glBindTexture();
		GL11.glColor3ub((byte)255, (byte)255, (byte)255);
		GL11.glBegin(GL11.GL_QUADS);
		glVertices();
		GL11.glEnd();
	}

	/* (non-Javadoc)
	 * @see game.core.gfx.SpriteProvider#provideSprite(game.core.GameObject)
	 */
	@Override
	public Sprite provideSprite(GameObject object) {
		return this;
	}

}
