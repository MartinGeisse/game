/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.gfx;

import game.core.AbstractBehavior;
import game.core.GameObject;

/**
 * Draws a colored rectangle to represent an object.
 */
public final class DrawSpriteBehavior extends AbstractBehavior {

	/**
	 * the spriteProvider
	 */
	private SpriteProvider spriteProvider;

	/**
	 * Constructor.
	 */
	public DrawSpriteBehavior() {
	}

	/**
	 * Constructor.
	 * @param spriteProvider the sprite provider
	 */
	public DrawSpriteBehavior(SpriteProvider spriteProvider) {
		this.spriteProvider = spriteProvider;
	}

	/**
	 * Getter method for the spriteProvider.
	 * @return the spriteProvider
	 */
	public SpriteProvider getSpriteProvider() {
		return spriteProvider;
	}

	/**
	 * Setter method for the spriteProvider.
	 * @param spriteProvider the spriteProvider to set
	 */
	public void setSpriteProvider(SpriteProvider spriteProvider) {
		this.spriteProvider = spriteProvider;
	}

	/* (non-Javadoc)
	 * @see game.core.AbstractBehavior#draw(game.core.GameObject)
	 */
	@Override
	public void draw(GameObject target) {
		Sprite sprite = (spriteProvider == null ? null : spriteProvider.provideSprite(target));
		if (sprite != null) {
			sprite.draw();
		}
	}

}
