/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

import org.lwjgl.opengl.GL11;

import game.engine.util.ClassKeyedContainer;

/**
 * A single object in the game.
 */
public class GameObject {

	/**
	 * the behaviors
	 */
	private final ClassKeyedContainer<Behavior> behaviors = new ClassKeyedContainer<>();

	/**
	 * Attaches a behavior to this object. Throws an exception if another behavior of
	 * the same type is already attached -- in this case, reasons exist to keep either
	 * one, so no sensible default handling can be defined. 
	 * 
	 * @param behavior the behavior to attach
	 */
	public void attachBehavior(Behavior behavior) {
		@SuppressWarnings("unchecked")
		Class<? extends Behavior> key = behavior.getClass();
		Behavior previous = behaviors.set(key, behavior);
		if (previous == null) {
			behavior.onAttach(this);
		} else if (previous != behavior) {
			behaviors.set(key, previous);
			throw new IllegalStateException("a behavior of type " + behavior.getClass() + " is already attached to this object");
		}
	}
	
	/**
	 * Attaches a behavior to this object. If another behavior of the same type is
	 * already attached, then the "replace" parameter determines whether that
	 * behavior gets replaced (true) or if this method should have no effect (false). 
	 * 
	 * @param behavior the behavior to attach
	 * @param replace whether another behavior of the same type should be replaced
	 */
	public void attachBehavior(Behavior behavior, boolean replace) {
		@SuppressWarnings("unchecked")
		Class<? extends Behavior> key = behavior.getClass();
		Behavior previous = behaviors.set(key, behavior);
		if (previous == null) {
			behavior.onAttach(this);
		} else if (previous == behavior) {
			// nothing
		} else if (replace) {
			previous.onDetach(this);
			behavior.onAttach(this);
		} else {
			behaviors.set(key, previous);
		}
	}
	
	
	/**
	 * Detaches a behavior from this object. Does nothing if the behavior isn't currently
	 * attached.
	 * 
	 * @param behavior the behavior to detach
	 * @return true if the behavior was attached and is now detached; false if the behavior
	 * wasn't attached
	 */
	public boolean detachBehavior(Behavior behavior) {
		Class<? extends Behavior> key = behavior.getClass();
		Behavior previous = behaviors.remove(key);
		if (previous == null) {
			return false;
		} else if (previous == behavior) {
			behavior.onDetach(this);
			return true;
		} else {
			behaviors.set(key, previous);
			return false;
		}
	}
	
	/**
	 * Obtains the behavior object for the specified behavior class.
	 * 
	 * @param behaviorClass the behavior class
	 * @return the behavior object
	 */
	public <T extends Behavior> T getBehavior(Class<? extends T> behaviorClass) {
		return behaviors.get(behaviorClass);
	}

	/**
	 * Draws this object using OpenGL.
	 */
	public void draw() {
		GL11.glPushMatrix();
		for (Behavior behavior : behaviors.getValues()) {
			behavior.prepareRenderState();
		}
		for (Behavior behavior : behaviors.getValues()) {
			behavior.draw();
		}
		GL11.glPopMatrix();
	}

	/**
	 * Handles the game logic for a single game step for this object.
	 */
	public void handleStep() {
		for (Behavior behavior : behaviors.getValues()) {
			behavior.handleStep();
		}
	}

}
