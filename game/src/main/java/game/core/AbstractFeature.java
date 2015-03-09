/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

/**
 * Base class to implement features.
 */
public abstract class AbstractFeature implements Feature {

	/* (non-Javadoc)
	 * @see game.core.Feature#onFeatureAttached(game.core.GameObject)
	 */
	@Override
	public void onFeatureAttached(GameObject target) {
	}

	/* (non-Javadoc)
	 * @see game.core.Feature#onFeatureDetached(game.core.GameObject)
	 */
	@Override
	public void onFeatureDetached(GameObject target) {
	}

}
