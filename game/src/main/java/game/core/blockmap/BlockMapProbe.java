/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core.blockmap;

import game.core.geometry.ReadablePosition;


/**
 * This class is able to sample a block map.
 */
public class BlockMapProbe {

	/**
	 * the leftExtent
	 */
	private final int leftExtent;

	/**
	 * the rightExtent
	 */
	private final int rightExtent;

	/**
	 * the topExtent
	 */
	private final int topExtent;

	/**
	 * the bottomExtent
	 */
	private final int bottomExtent;

	/**
	 * Constructor.
	 * @param leftExtent the extent to the left
	 * @param rightExtent the extent to the right
	 * @param topExtent the extent to the top
	 * @param bottomExtent the extent to the bottom
	 */
	public BlockMapProbe(int leftExtent, int rightExtent, int topExtent, int bottomExtent) {
		this.leftExtent = leftExtent;
		this.rightExtent = rightExtent;
		this.topExtent = topExtent;
		this.bottomExtent = bottomExtent;
	}

	/**
	 * Getter method for the leftExtent.
	 * @return the leftExtent
	 */
	public int getLeftExtent() {
		return leftExtent;
	}

	/**
	 * Getter method for the rightExtent.
	 * @return the rightExtent
	 */
	public int getRightExtent() {
		return rightExtent;
	}

	/**
	 * Getter method for the topExtent.
	 * @return the topExtent
	 */
	public int getTopExtent() {
		return topExtent;
	}

	/**
	 * Getter method for the bottomExtent.
	 * @return the bottomExtent
	 */
	public int getBottomExtent() {
		return bottomExtent;
	}

	/**
	 * Checks if any touched block is solid according to the specified solidity
	 * flags, assuming the probe is at the specified position.
	 * 
	 * @param position the probe position
	 * @param valuesSolid indicates which block map values are solid
	 * @return true if any block is solid, false if none is
	 */
	public boolean checkAny(ReadablePosition position, boolean[] valuesSolid) {
		return false;
	}
	
}
