/**
 * Copyright (c) 2013 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package game.core.blockmap;

import game.core.AbstractBehavior;
import game.core.GameObject;
import game.engine.gfx.Texture;

import org.lwjgl.opengl.GL11;

/**
 * The main behavior to implement block maps.
 */
public class BlockMapBehavior extends AbstractBehavior {

	/**
	 * the width
	 */
	private final int width;

	/**
	 * the height
	 */
	private final int height;

	/**
	 * the data
	 */
	private final byte[] data;

	/**
	 * the borderValue
	 */
	private byte borderValue;

	/**
	 * the textureProvider
	 */
	private TextureProvider textureProvider;

	/**
	 * Constructor.
	 * @param width the map width
	 * @param height the map height
	 */
	public BlockMapBehavior(int width, int height) {
		this.width = width;
		this.height = height;
		this.data = new byte[width * height];
	}

	/**
	 * Getter method for the width.
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Getter method for the height.
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns a single map block.
	 * @param x the x position
	 * @param y the y position
	 * @return the block
	 */
	public int getBlock(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return borderValue;
		} else {
			return data[y * width + x] & 0xff;
		}
	}

	/**
	 * Changes a single map block.
	 * @param x the x position
	 * @param y the y position
	 * @param value the value to set, in the range 0..255
	 */
	public void setBlock(int x, int y, int value) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			throw new IllegalArgumentException("invalid position (" + x + ", " + y + ") for map size (" + width + " x " + height + ")");
		}
		if (value < 0 || value > 255) {
			throw new IllegalArgumentException("invalid block value: " + value);
		}
		data[y * width + x] = (byte)value;
	}

	/**
	 * Getter method for the borderValue.
	 * @return the borderValue
	 */
	public byte getBorderValue() {
		return borderValue;
	}

	/**
	 * Setter method for the borderValue.
	 * @param borderValue the borderValue to set
	 */
	public void setBorderValue(byte borderValue) {
		this.borderValue = borderValue;
	}

	/**
	 * Getter method for the textureProvider.
	 * @return the textureProvider
	 */
	public TextureProvider getTextureProvider() {
		return textureProvider;
	}

	/**
	 * Setter method for the textureProvider.
	 * @param textureProvider the textureProvider to set
	 * @return this
	 */
	public BlockMapBehavior setTextureProvider(TextureProvider textureProvider) {
		this.textureProvider = textureProvider;
		return this;
	}

	/* (non-Javadoc)
	 * @see game.core.AbstractBehavior#draw(game.core.GameObject)
	 */
	@Override
	public void draw(GameObject target) {
		if (textureProvider == null) {
			return;
		}
		int w = Math.min(width, 100);
		int h = Math.min(height, 30);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor3ub((byte)255, (byte)255, (byte)255);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Texture texture = textureProvider.getBlockTexture(getBlock(x, y));
				if (texture == null) {
					continue;
				}
				texture.glBindTexture();
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0.0f, 1.0f);
				GL11.glVertex2i(x, y);
				GL11.glTexCoord2f(1.0f, 1.0f);
				GL11.glVertex2i(x + 1, y);
				GL11.glTexCoord2f(1.0f, 0.0f);
				GL11.glVertex2i(x + 1, y + 1);
				GL11.glTexCoord2f(0.0f, 0.0f);
				GL11.glVertex2i(x, y + 1);
				GL11.glEnd();
			}
		}
	}

}
