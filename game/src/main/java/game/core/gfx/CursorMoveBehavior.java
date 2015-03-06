/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.gfx;

import org.lwjgl.input.Keyboard;

import game.core.AbstractOwnerAwareBehavior;
import game.core.geometry.MutablePosition;

/**
 * Can be combined with a {@link PositionBehavior} to move the object using the arrow keys.
 */
public final class CursorMoveBehavior extends AbstractOwnerAwareBehavior {

	/**
	 * the speed
	 */
	private int speed;

	/**
	 * Constructor.
	 */
	public CursorMoveBehavior() {
		this(1000);
	}

	/**
	 * Constructor.
	 * @param speed the movement speed (units per frame)
	 */
	public CursorMoveBehavior(int speed) {
		this.speed = speed;
	}

	/**
	 * Getter method for the speed.
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Setter method for the speed.
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/* (non-Javadoc)
	 * @see game.core.AbstractBehavior#handleStep()
	 */
	@Override
	public void handleStep() {
		handleKey(Keyboard.KEY_UP, 0, -speed);
		handleKey(Keyboard.KEY_DOWN, 0, speed);
		handleKey(Keyboard.KEY_LEFT, -speed, 0);
		handleKey(Keyboard.KEY_RIGHT, speed, 0);
	}

	/**
	 * 
	 */
	private void handleKey(int key, int dx, int dy) {
		if (Keyboard.isKeyDown(key)) {
			MutablePosition mutablePosition = getOwner().getBehavior(PositionBehavior.class).getMutablePosition();
			mutablePosition.setX(mutablePosition.getX() + dx);
			mutablePosition.setY(mutablePosition.getY() + dy);
		}
	}

}
