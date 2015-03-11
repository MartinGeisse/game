/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.particle;

import game.core.gfx.SpriteProvider;

/**
 * This interface can be used by game objects to spawn particle effects.
 */
public interface ParticleSpawner {

	/**
	 * Spawns a new particle.
	 * @param x the initial x position
	 * @param y the initial y position
	 * @param dx the initial x velocity
	 * @param dy the initial y velocity
	 * @param gravity the gravity
	 * @param spriteProvider the sprite provider
	 */
	public void spawnParticle(float x, float y, float dx, float dy, float gravity, SpriteProvider spriteProvider);
	
}
