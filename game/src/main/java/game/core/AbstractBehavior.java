/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

/**
 * Base class to implement behaviors.
 */
public abstract class AbstractBehavior implements Behavior {

	/* (non-Javadoc)
	 * @see game.core.Behavior#onBehaviorAttached(game.core.GameObject)
	 */
	@Override
	public void onBehaviorAttached(GameObject target) {
	}

	/* (non-Javadoc)
	 * @see game.core.Behavior#onBehaviorDetached(game.core.GameObject)
	 */
	@Override
	public void onBehaviorDetached(GameObject target) {
	}

	/* (non-Javadoc)
	 * @see game.core.Behavior#prepareRenderState(game.core.GameObject)
	 */
	@Override
	public void prepareRenderState(GameObject target) {
	}

	/* (non-Javadoc)
	 * @see game.core.Behavior#draw(game.core.GameObject)
	 */
	@Override
	public void draw(GameObject target) {
	}

	/* (non-Javadoc)
	 * @see game.core.Behavior#handleStep(game.core.GameObject)
	 */
	@Override
	public void handleStep(GameObject target) {
	}

}
