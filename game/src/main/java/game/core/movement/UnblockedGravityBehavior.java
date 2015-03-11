/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.movement;

import game.core.AbstractBehavior;
import game.core.GameObject;
import game.core.geometry.MutablePosition;

/**
 * Makes an object fall down with gravity but does not perform collision detection.
 * Can be used for effects.
 */
public class UnblockedGravityBehavior extends AbstractBehavior {

	/**
	 * the MAX_VERTICAL_SPEED
	 */
	private static final float MAX_VERTICAL_SPEED = 0.8f;

	/**
	 * the verticalSpeed
	 */
	private float verticalSpeed = 0;

	/**
	 * Getter method for the verticalSpeed.
	 * @return the verticalSpeed
	 */
	public float getVerticalSpeed() {
		return verticalSpeed;
	}

	/**
	 * Setter method for the verticalSpeed.
	 * @param verticalSpeed the verticalSpeed to set
	 */
	public void setVerticalSpeed(float verticalSpeed) {
		this.verticalSpeed = verticalSpeed;
	}

	/* (non-Javadoc)
	 * @see game.core.AbstractBehavior#handleStep(game.core.GameObject)
	 */
	@Override
	public void handleStep(GameObject target) {
		verticalSpeed += 0.1f;
		if (verticalSpeed > MAX_VERTICAL_SPEED) {
			verticalSpeed = MAX_VERTICAL_SPEED;
		}
		MutablePosition mutablePosition = target.getBehavior(PositionBehavior.class).getMutablePosition();
		mutablePosition.setY(mutablePosition.getY() + verticalSpeed);
	}

}
