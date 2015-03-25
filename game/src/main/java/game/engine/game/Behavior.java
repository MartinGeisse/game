/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.engine.game;

import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * A behavior that can be applied to a game object.
 */
public class Behavior {

	/**
	 * the applicator
	 */
	private final Applicator applicator;

	/**
	 * Constructor.
	 * @param applicator the behavior applicator
	 */
	public Behavior(Applicator applicator) {
		if (applicator == null) {
			throw new IllegalArgumentException("applicator cannot be null");
		}
		this.applicator = applicator;
	}

	/**
	 * Applies this behavior to the specified game object.
	 * @param target the target game object
	 */
	public void apply(GameObject target) {
		apply(target, null);
	}

	/**
	 * Applies this behavior to the specified game object.
	 * @param target the target game object
	 */
	public void apply(GameObject target, ScriptObjectMirror parameters) {
		applicator.apply(this, target, parameters);
	}
	
	/**
	 * Applies this behavior to the specified game object.
	 * @param target the target game object
	 */
	public void applyNative(GameObject target, Object parameters) {
		applicator.applyNative(this, target, parameters);
	}

	/**
	 * This function applies a behavior to a game object.
	 */
	public static interface Applicator {

		/**
		 * Applies the behavior to the specified game object.
		 * 
		 * @param behavior the behavior to apply
		 * @param target the target game object
		 * @param parameters the parameters object
		 */
		public void apply(Behavior behavior, GameObject target, ScriptObjectMirror parameters);

		/**
		 * Applies the behavior to the specified game object.
		 * 
		 * @param behavior the behavior to apply
		 * @param target the target game object
		 * @param parameters the parameters object
		 */
		public void applyNative(Behavior behavior, GameObject target, Object parameters);

	}

	/**
	 * Implements an {@link Applicator} written in Javascript.
	 */
	public static final class ScriptedApplicator implements Applicator {

		/**
		 * the function
		 */
		private final JSObject function;

		/**
		 * Constructor.
		 * @param function the function to call
		 */
		public ScriptedApplicator(JSObject function) {
			this.function = function;
		}

		/* (non-Javadoc)
		 * @see game.engine.game.Behavior.Applicator#apply(game.engine.game.Behavior, game.engine.game.GameObject, jdk.nashorn.api.scripting.ScriptObjectMirror)
		 */
		@Override
		public void apply(Behavior behavior, GameObject target, ScriptObjectMirror parameters) {
			function.call(behavior, target, parameters);
		}
		
		/* (non-Javadoc)
		 * @see game.engine.game.Behavior.Applicator#applyNative(game.engine.game.Behavior, game.engine.game.GameObject, java.lang.Object)
		 */
		@Override
		public void applyNative(Behavior behavior, GameObject target, Object parameters) {
			function.call(behavior, target, parameters);
		}

	}

}
