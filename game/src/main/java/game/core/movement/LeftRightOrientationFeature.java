/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.movement;

import game.core.AbstractFeature;
import game.core.geometry.LeftRight;

/**
 * This feature is able to store a left/right orientation.
 */
public final class LeftRightOrientationFeature extends AbstractFeature {

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
