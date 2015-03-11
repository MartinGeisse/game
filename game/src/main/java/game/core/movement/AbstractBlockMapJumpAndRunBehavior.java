/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.movement;

import game.core.GameObject;
import game.core.blockmap.BlockMapProbe;
import game.core.geometry.MutablePosition;
import game.core.geometry.Position;

/**
 * Jump'n'run behavior based on a block map.
 */
public abstract class AbstractBlockMapJumpAndRunBehavior extends AbstractJumpAndRunBehavior {

	/**
	 * the blockMapProbe
	 */
	private final BlockMapProbe blockMapProbe;
	
	/**
	 * the blockValuesSolid
	 */
	private final boolean[] blockValuesSolid;
	

	/**
	 * Constructor.
	 * @param blockMapProbe the probe used for collision detection
	 * @param blockValuesSolid specifies which blocks are solid
	 */
	public AbstractBlockMapJumpAndRunBehavior(BlockMapProbe blockMapProbe, final boolean[] blockValuesSolid) {
		this.blockMapProbe = blockMapProbe;
		this.blockValuesSolid = blockValuesSolid;
	}

	/* (non-Javadoc)
	 * @see game.core.movement.AbstractJumpAndRunBehavior#adjustHorizontalMovement(game.core.GameObject, float)
	 */
	@Override
	protected float adjustHorizontalMovement(GameObject target, float dx) {
		return (isBlocked(target, dx, 0) ? 0 : dx);
	}

	/* (non-Javadoc)
	 * @see game.core.movement.AbstractJumpAndRunBehavior#adjustVerticalMovement(game.core.GameObject, float)
	 */
	@Override
	protected float adjustVerticalMovement(GameObject target, float dy) {
		return (isBlocked(target, 0, dy) ? 0 : dy);
	}
	
	/**
	 * 
	 */
	private boolean isBlocked(GameObject target, float dx, float dy) {
		MutablePosition position = target.getBehavior(PositionBehavior.class).getMutablePosition();
		Position newPosition = new Position(position.getX() + dx, position.getY() + dy);
		return blockMapProbe.checkAny(newPosition, blockValuesSolid);
	}

}
