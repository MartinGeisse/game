/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.movement;

import game.core.AbstractBehavior;
import game.core.GameObject;
import game.core.geometry.LeftRight;
import game.core.geometry.MutablePosition;

import org.lwjgl.input.Keyboard;

/**
 * Makes an object with a {@link PositionBehavior} move like a jump'n'run character.
 * 
 * Concrete implementations must provide various methods to handle the details,
 * especially collision detection.
 */
public abstract class AbstractJumpAndRunBehavior extends AbstractBehavior {

	/**
	 * the HORIZONTAL_SPEED
	 */
	private static final int HORIZONTAL_SPEED = 1500;
	
	/**
	 * the verticalSpeed
	 */
	private int verticalSpeed = 0;

	/**
	 * Getter method for the verticalSpeed.
	 * @return the verticalSpeed
	 */
	public int getVerticalSpeed() {
		return verticalSpeed;
	}

	/**
	 * Setter method for the verticalSpeed.
	 * @param verticalSpeed the verticalSpeed to set
	 */
	public void setVerticalSpeed(int verticalSpeed) {
		this.verticalSpeed = verticalSpeed;
	}

	/* (non-Javadoc)
	 * @see game.core.AbstractBehavior#handleStep(game.core.GameObject)
	 */
	@Override
	public void handleStep(GameObject target) {

		// horizontal movement
		handleHorizontalMovementKey(target, Keyboard.KEY_LEFT, -HORIZONTAL_SPEED, LeftRight.LEFT);
		handleHorizontalMovementKey(target, Keyboard.KEY_RIGHT, HORIZONTAL_SPEED, LeftRight.RIGHT);

		// vertical movement
		verticalSpeed += getGravity(target);
		int dy = adjustVerticalMovement(target, verticalSpeed);
		MutablePosition mutablePosition = target.getBehavior(PositionBehavior.class).getMutablePosition();
		mutablePosition.setY(mutablePosition.getY() + dy);
		if (dy != verticalSpeed) {
			verticalSpeed = 0;
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				verticalSpeed = -5000;
			}
		}

	}

	/**
	 * 
	 */
	private void handleHorizontalMovementKey(GameObject target, int key, int dx, LeftRight leftRight) {
		if (Keyboard.isKeyDown(key)) {
			dx = adjustHorizontalMovement(target, dx);
			MutablePosition mutablePosition = target.getBehavior(PositionBehavior.class).getMutablePosition();
			mutablePosition.setX(mutablePosition.getX() + dx);
			LeftRightOrientationBehavior leftRightOrientationBehavior = target.getBehavior(LeftRightOrientationBehavior.class);
			if (leftRightOrientationBehavior != null) {
				leftRightOrientationBehavior.setOrientation(leftRight);
			}
		}
	}

	/**
	 * Checks whether horizontal movement is allowed or blocked, and adjusts the
	 * position delta accordingly.
	 * 
	 * @param target the target object
	 * @param dx the x position delta
	 * @return the adjusted position delta
	 */
	protected abstract int adjustHorizontalMovement(GameObject target, int dx);

	/**
	 * Checks whether vertical movement is allowed or blocked, and adjusts the
	 * position delta accordingly. If the delta is different from the parameter
	 * delta, then movement is assumed to be blocked and the vertical speed
	 * gets adjusted.
	 * 
	 * @param target the target object
	 * @param dy the y position delta
	 * @return the adjusted position delta
	 */
	protected abstract int adjustVerticalMovement(GameObject target, int dy);

	/**
	 * Determines the gravity for the specified object.
	 * 
	 * @param target the target object
	 * @return the gravity
	 */
	protected abstract int getGravity(GameObject target);

}
