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
	private static final float HORIZONTAL_SPEED = 0.5f;
	
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

		// horizontal movement
		handleHorizontalMovementKey(target, Keyboard.KEY_LEFT, -HORIZONTAL_SPEED, LeftRight.LEFT);
		handleHorizontalMovementKey(target, Keyboard.KEY_RIGHT, HORIZONTAL_SPEED, LeftRight.RIGHT);

		// vertical movement
		verticalSpeed += getGravity(target);
		float dy = adjustVerticalMovement(target, verticalSpeed);
		MutablePosition mutablePosition = target.getBehavior(PositionBehavior.class).getMutablePosition();
		mutablePosition.setY(mutablePosition.getY() + dy);
		if (dy != verticalSpeed) {
			verticalSpeed = 0;
			// TODO only if blocked by floor, not ceiling
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				verticalSpeed = -1.2f;
			}
		}

	}

	/**
	 * 
	 */
	private void handleHorizontalMovementKey(GameObject target, int key, float dx, LeftRight leftRight) {
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
	protected abstract float adjustHorizontalMovement(GameObject target, float dx);

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
	protected abstract float adjustVerticalMovement(GameObject target, float dy);

	/**
	 * Determines the gravity for the specified object.
	 * 
	 * @param target the target object
	 * @return the gravity
	 */
	protected abstract float getGravity(GameObject target);

}
