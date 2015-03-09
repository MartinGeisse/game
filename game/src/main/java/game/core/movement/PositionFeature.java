/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.movement;

import game.core.AbstractFeature;
import game.core.geometry.MutablePosition;

/**
 * Objects with this feature have a position in the world and can be moved.
 * The feature should be added by using a {@link PositionBehavior}.
 */
public final class PositionFeature extends AbstractFeature {

	/**
	 * the mutablePosition
	 */
	private final MutablePosition mutablePosition = new MutablePosition(0, 0);

	/**
	 * Constructor.
	 */
	PositionFeature() {
	}

	/**
	 * Getter method for the mutablePosition.
	 * @return the mutablePosition
	 */
	public MutablePosition getMutablePosition() {
		return mutablePosition;
	}

}