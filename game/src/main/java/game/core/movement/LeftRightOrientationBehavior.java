/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.movement;

import game.core.AbstractBehavior;
import game.core.LeftRight;

/**
 * This behavior is able to store a left/right orientation.
 */
public final class LeftRightOrientationBehavior extends AbstractBehavior {

	/**
	 * the orientation
	 */
	private LeftRight orientation = LeftRight.LEFT;

	/**
	 * Getter method for the orientation.
	 * @return the orientation
	 */
	public LeftRight getOrientation() {
		return orientation;
	}

	/**
	 * Setter method for the orientation.
	 * @param orientation the orientation to set
	 */
	public void setOrientation(LeftRight orientation) {
		this.orientation = orientation;
	}

}
