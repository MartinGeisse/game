/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

/**
 * A single object in the game.
 */
public class GameObject {

	/**
	 * the region
	 */
	private final Region region;

	/**
	 * the behaviors
	 */
	private final Map<String, Behavior> behaviors = new HashMap<>();

	/**
	 * Constructor.
	 * @param region the region that owns this object
	 */
	GameObject(Region region) {
		this.region = region;
	}
	
	/**
	 * Getter method for the region.
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * Attaches a behavior to this object. Throws an exception if another behavior with
	 * the same ID is already attached -- in this case, reasons exist to keep either
	 * one, so no sensible default handling can be defined. 
	 * 
	 * @param behavior the behavior to attach
	 */
	public void attachBehavior(Behavior behavior) {
		String id = behavior.getId();
		Behavior previous = behaviors.put(id, behavior);
		if (previous == null) {
			behavior.onAttach(this);
		} else if (previous != behavior) {
			behaviors.put(id, previous);
			throw new IllegalStateException("a behavior with ID " + id + " is already attached to this object");
		}
	}

	/**
	 * Attaches a behavior to this object. If another behavior with the same ID is
	 * already attached, then the "replace" parameter determines whether that
	 * behavior gets replaced (true) or if this method should have no effect (false). 
	 * 
	 * @param behavior the behavior to attach
	 * @param replace whether another behavior with the same ID should be replaced
	 */
	public void attachBehavior(Behavior behavior, boolean replace) {
		String id = behavior.getId();
		Behavior previous = behaviors.put(id, behavior);
		if (previous == null) {
			behavior.onAttach(this);
		} else if (previous == behavior) {
			// nothing
		} else if (replace) {
			previous.onDetach(this);
			behavior.onAttach(this);
		} else {
			behaviors.put(id, previous);
		}
	}

	/**
	 * Detaches the specified behavior from this object. Does nothing if the
	 * behavior isn't currently attached.
	 * 
	 * @param behavior the behavior to detach
	 * @return true if the behavior was attached and is now detached; false if the behavior
	 * wasn't attached
	 */
	public boolean detachBehavior(Behavior behavior) {
		String id = behavior.getId();
		Behavior previous = behaviors.remove(id);
		if (previous == null) {
			return false;
		} else if (previous == behavior) {
			behavior.onDetach(this);
			return true;
		} else {
			behaviors.put(id, previous);
			return false;
		}
	}

	/**
	 * Detaches a behavior with the specified ID from this object. Does nothing if
	 * no such behavior isn't currently attached.
	 * 
	 * @param id the ID of the behavior to detach
	 * @return true if the behavior was attached and is now detached; false if the behavior
	 * wasn't attached
	 */
	public boolean detachBehavior(String id) {
		Behavior previous = behaviors.remove(id);
		if (previous == null) {
			return false;
		} else {
			previous.onDetach(this);
			return true;
		}
	}

	/**
	 * Detaches a behavior from this object whose ID is the fully qualified name
	 * of the specified class. Does nothing if no such behavior isn't currently attached.
	 * 
	 * @param idClass the class whose fully qualified name is the ID of the behavior to detach
	 * @return true if the behavior was attached and is now detached; false if the behavior
	 * wasn't attached
	 */
	public boolean detachBehavior(Class<?> idClass) {
		return detachBehavior(idClass.getName());
	}

	/**
	 * Obtains the behavior object for the specified ID.
	 * 
	 * @param id the behavior ID
	 * @return the behavior object
	 */
	public Behavior getBehavior(String id) {
		return behaviors.get(id);
	}

	/**
	 * Obtains the behavior object whose ID is the fully qualified name of the
	 * specified class, cast to that class.
	 * 
	 * @param idClass the class whose fully qualified name is the ID of the behavior to return
	 * @return the behavior object
	 */
	public <T> T getBehavior(Class<? extends T> idClass) {
		return idClass.cast(getBehavior(idClass.getName()));
	}

	/**
	 * Draws this object using OpenGL.
	 */
	public void draw() {
		GL11.glPushMatrix();
		for (Behavior behavior : behaviors.values()) {
			behavior.prepareRenderState(this);
		}
		for (Behavior behavior : behaviors.values()) {
			behavior.draw(this);
		}
		GL11.glPopMatrix();
	}

	/**
	 * Handles the game logic for a single game step for this object.
	 */
	public void handleStep() {
		// TODO pass a GameStepProcessor that can copy the arrays into a
		// re-used "clone" array without creating new objects
		for (Behavior behavior : new ArrayList<>(behaviors.values())) {
			behavior.handleStep(this);
		}
	}

}
