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
	 * the blockMap
	 */
	private final BlockMapBehavior blockMap;
	
	/**
	 * the leftExtent
	 */
	private final float leftExtent;

	/**
	 * the rightExtent
	 */
	private final float rightExtent;

	/**
	 * the topExtent
	 */
	private final float topExtent;

	/**
	 * the bottomExtent
	 */
	private final float bottomExtent;

	/**
	 * Constructor.
	 * @param blockMap the block map
	 * @param leftExtent the extent to the left
	 * @param rightExtent the extent to the right
	 * @param topExtent the extent to the top
	 * @param bottomExtent the extent to the bottom
	 */
	public BlockMapProbe(BlockMapBehavior blockMap, float leftExtent, float rightExtent, float topExtent, float bottomExtent) {
		this.blockMap = blockMap;
		this.leftExtent = leftExtent;
		this.rightExtent = rightExtent;
		this.topExtent = topExtent;
		this.bottomExtent = bottomExtent;
	}

	/**
	 * Getter method for the blockMap.
	 * @return the blockMap
	 */
	public BlockMapBehavior getBlockMap() {
		return blockMap;
	}
	
	/**
	 * Getter method for the leftExtent.
	 * @return the leftExtent
	 */
	public float getLeftExtent() {
		return leftExtent;
	}

	/**
	 * Getter method for the rightExtent.
	 * @return the rightExtent
	 */
	public float getRightExtent() {
		return rightExtent;
	}

	/**
	 * Getter method for the topExtent.
	 * @return the topExtent
	 */
	public float getTopExtent() {
		return topExtent;
	}

	/**
	 * Getter method for the bottomExtent.
	 * @return the bottomExtent
	 */
	public float getBottomExtent() {
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
		return check(position, valuesSolid, true);
	}
	
	/**
	 * Checks if all touched blocks are solid according to the specified solidity
	 * flags, assuming the probe is at the specified position.
	 * 
	 * @param position the probe position
	 * @param valuesSolid indicates which block map values are solid
	 * @return true if all blocks are solid, false if any is nonsolid
	 */
	public boolean checkAll(ReadablePosition position, boolean[] valuesSolid) {
		return check(position, valuesSolid, false);
	}

	/**
	 * Implementation of checkAny() / checkAll(). Returns mixedResult if the sampled
	 * blocks return mixed results.
	 */
	private boolean check(ReadablePosition position, boolean[] valuesSolid, boolean mixedResult) {
		int x1 = (int)(position.getX() - leftExtent);
		int x2 = (int)(position.getX() + rightExtent);
		int y1 = (int)(position.getY() - topExtent);
		int y2 = (int)(position.getY() + bottomExtent);
		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				boolean blockResult = valuesSolid[blockMap.getBlock(x, y)];
				if (blockResult == mixedResult) {
					return blockResult;
				}
			}
		}
		return !mixedResult;
	}
	
}
