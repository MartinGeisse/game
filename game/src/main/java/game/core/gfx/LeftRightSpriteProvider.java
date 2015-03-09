/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.gfx;

import game.core.GameObject;
import game.core.geometry.LeftRight;
import game.core.movement.LeftRightOrientationFeature;

/**
 * A sprite provider that delegates to either one of two providers
 * based on the {@link LeftRightOrientationFeature} of the game
 * object.
 */
public final class LeftRightSpriteProvider implements SpriteProvider {

	/**
	 * the leftProvider
	 */
	private final SpriteProvider leftProvider;

	/**
	 * the rightProvider
	 */
	private final SpriteProvider rightProvider;

	/**
	 * Constructor.
	 * @param leftProvider the left provider
	 * @param rightProvider the right provider
	 */
	public LeftRightSpriteProvider(SpriteProvider leftProvider, SpriteProvider rightProvider) {
		this.leftProvider = leftProvider;
		this.rightProvider = rightProvider;
	}

	/**
	 * Getter method for the leftProvider.
	 * @return the leftProvider
	 */
	public SpriteProvider getLeftProvider() {
		return leftProvider;
	}

	/**
	 * Getter method for the rightProvider.
	 * @return the rightProvider
	 */
	public SpriteProvider getRightProvider() {
		return rightProvider;
	}

	/* (non-Javadoc)
	 * @see game.core.gfx.SpriteProvider#provideSprite(game.core.GameObject)
	 */
	@Override
	public Sprite provideSprite(GameObject object) {
		LeftRightOrientationFeature feature = object.getFeature(LeftRightOrientationFeature.class);
		if (feature == null || feature.getOrientation() == null) {
			return null;
		} else if (feature.getOrientation() == LeftRight.LEFT) {
			return leftProvider.provideSprite(object);
		} else {
			return rightProvider.provideSprite(object);
		}
	}

}
