/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.movement;

import game.core.AbstractBehavior;
import game.core.GameObject;
import game.core.geometry.MutablePosition;

import org.lwjgl.opengl.GL11;

/**
 * This behavior adds a {@link PositionFeature} to an object.
 */
public final class PositionBehavior extends AbstractBehavior {

	/* (non-Javadoc)
	 * @see game.core.AbstractBehavior#onBehaviorAttached(game.core.GameObject)
	 */
	@Override
	public void onBehaviorAttached(GameObject target) {
		target.attachFeature(new PositionFeature());
	}

	/* (non-Javadoc)
	 * @see game.core.AbstractBehavior#prepareRenderState(game.core.GameObject)
	 */
	@Override
	public void prepareRenderState(GameObject target) {
		PositionFeature feature = target.getFeature(PositionFeature.class);
		MutablePosition mutablePosition = feature.getMutablePosition();
		GL11.glTranslatef(mutablePosition.getX(), mutablePosition.getY(), 0.0f);
	}

}
