/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

/**
 * Base class for a behavior that should be attached only to a single game object,
 * which is then remembered by this behavior.
 */
public abstract class AbstractOwnerAwareBehavior extends AbstractBehavior {
	
	/**
	 * the owner
	 */
	private GameObject owner;
	
	/**
	 * Getter method for the owner.
	 * @return the owner
	 */
	public GameObject getOwner() {
		return owner;
	}

	/* (non-Javadoc)
	 * @see game.core.AbstractBehavior#onAttach(game.core.GameObject)
	 */
	@Override
	public void onAttach(GameObject target) {
		if (owner != null) {
			throw new IllegalStateException("this behavior is already atatched to " + owner + " and thus cannot be attached to " + target);
		}
		owner = target;
	}
	
	/* (non-Javadoc)
	 * @see game.core.AbstractBehavior#onDetach(game.core.GameObject)
	 */
	@Override
	public void onDetach(GameObject target) {
		if (owner != target) {
			throw new IllegalStateException("this behavior is not currently attached to " + target);
		}
		owner = null;
	}
	
}
