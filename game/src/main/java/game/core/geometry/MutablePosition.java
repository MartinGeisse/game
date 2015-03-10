/**
 * Copyright (c) 2014 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package game.core.geometry;

/**
 *
 */
public class MutablePosition extends ReadablePosition {

	/**
	 * the x
	 */
	public float x;

	/**
	 * the y
	 */
	public float y;

	/**
	 * Constructor.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public MutablePosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter method for the x.
	 * @return the x
	 */
	@Override
	public float getX() {
		return x;
	}

	/**
	 * Setter method for the x.
	 * @param x the x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Getter method for the y.
	 * @return the y
	 */
	@Override
	public float getY() {
		return y;
	}

	/**
	 * Setter method for the y.
	 * @param y the y
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Copies field values from the specified object.
	 * @param other the object to copy values from
	 */
	public void copyFrom(ReadablePosition other) {
		x = other.getX();
		y = other.getY();
	}

}
