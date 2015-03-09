/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

/**
 * Features are provided by behaviors and can be obtained using
 * their class. For each feature type, at most one feature can be
 * stored for each game object.
 */
public interface Feature {

	/**
	 * Called after this feature gets attached to a game object.
	 * 
	 * @param target the game object from which this feature got detached
	 */
	public void onFeatureAttached(GameObject target);

	/**
	 * Called after this feature gets detached from a game object.
	 * 
	 * @param target the game object from which this feature got detached
	 */
	public void onFeatureDetached(GameObject target);

}
