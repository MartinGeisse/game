/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

/**
 * Base class to implement behaviors.
 */
public abstract class AbstractBehavior implements Behavior {

	/* (non-Javadoc)
	 * @see game.core.Behavior#onAttach(game.core.GameObject)
	 */
	@Override
	public void onAttach(GameObject target) {
	}
	
	/* (non-Javadoc)
	 * @see game.core.Behavior#onDetach(game.core.GameObject)
	 */
	@Override
	public void onDetach(GameObject target) {
	}
	
	/* (non-Javadoc)
	 * @see game.core.Behavior#prepareRenderState()
	 */
	@Override
	public void prepareRenderState() {
	}
	
	/* (non-Javadoc)
	 * @see game.engine.game.Behavior#draw()
	 */
	@Override
	public void draw() {
	}

	/* (non-Javadoc)
	 * @see game.engine.game.Behavior#handleStep()
	 */
	@Override
	public void handleStep() {
	}

}
