/**
 * Copyright (c) 2014 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package game.core.geometry;

/**
 *
 */
public final class Position extends ImmutablePositionBase {

	/**
	 * Constructor.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Position(float x, float y) {
		super(x, y);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Position) {
			return baseFieldsEqual((Position)other);
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return baseFieldsHashCode();
	}

	/* (non-Javadoc)
	 * @@see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("{Position ");
		buildBaseFieldsDescription(builder);
		builder.append('}');
		return builder.toString();
	}

}
