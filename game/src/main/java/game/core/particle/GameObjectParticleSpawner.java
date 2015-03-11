/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.particle;

import game.core.GameObject;
import game.core.Region;
import game.core.geometry.MutablePosition;
import game.core.geometry.Position;
import game.core.gfx.DrawSpriteBehavior;
import game.core.gfx.SpriteProvider;
import game.core.misc.TimerBehavior;
import game.core.movement.PositionBehavior;
import game.core.movement.UnblockedGravityBehavior;

/**
 * Spawns particle effects by creating a new game object for each particle.
 */
public class GameObjectParticleSpawner implements ParticleSpawner {

	/**
	 * the region
	 */
	private final Region region;

	/**
	 * Constructor.
	 * @param region the region to spawn particles in
	 */
	public GameObjectParticleSpawner(Region region) {
		this.region = region;
	}

	/* (non-Javadoc)
	 * @see game.core.particle.ParticleSpawner#spawnParticle(float, float, float, float, float, game.core.gfx.SpriteProvider)
	 */
	@Override
	public void spawnParticle(float x, float y, float dx, float dy, float gravity, SpriteProvider spriteProvider) {
		GameObject particle = new GameObject();
		particle.attachBehavior(new PositionBehavior(new Position(x, y)));
		particle.attachBehavior(new DrawSpriteBehavior(spriteProvider));		
		particle.attachBehavior(new TimerBehavior(17) {
			@Override
			protected void onExpire() {
				region.getGameObjects().remove(particle);
			}
		});
		region.getGameObjects().add(particle);
		
		MutablePosition mutablePosition = particle.getBehavior(PositionBehavior.class).getMutablePosition();
		mutablePosition.setX(x);
		mutablePosition.setY(y);
		
		UnblockedGravityBehavior movement = new UnblockedGravityBehavior();
		movement.setDx(dx);
		movement.setDy(dy);
		movement.setGravity(gravity);
		particle.attachBehavior(movement);

	}

}
