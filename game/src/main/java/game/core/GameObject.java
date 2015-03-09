/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

import game.engine.util.ClassKeyedContainer;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

/**
 * A single object in the game.
 */
public class GameObject {

	/**
	 * the behaviors
	 */
	private final List<Behavior> behaviors = new ArrayList<>();

	/**
	 * the features
	 */
	private final ClassKeyedContainer<Feature> features = new ClassKeyedContainer<>();

	/**
	 * Attaches a behavior to this object. 
	 * 
	 * @param behavior the behavior to attach
	 */
	public void attachBehavior(Behavior behavior) {
		behaviors.add(behavior);
		behavior.onBehaviorAttached(this);
	}

	/**
	 * Detaches a behavior from this object. Does nothing if the behavior isn't currently
	 * attached to this object.
	 * 
	 * @param behavior the behavior to detach
	 * @return true if the behavior was attached and is now detached; false if the behavior
	 * wasn't attached
	 */
	public boolean detachBehavior(Behavior behavior) {
		if (behaviors.remove(behavior)) {
			behavior.onBehaviorDetached(this);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Obtains an iterable for the behaviors of this game object.
	 * 
	 * @return the behaviors
	 */
	public Iterable<Behavior> getBehaviors() {
		return behaviors;
	}

	/**
	 * Attaches a feature to this object. Throws an exception if another feature of
	 * the same type is already attached -- in this case, reasons exist to keep either
	 * one, so no sensible default handling can be defined.
	 * 
	 * This function derives the feature type automatically by taking the class of
	 * the feature object.
	 * 
	 * @param feature the feature to attach
	 */
	public void attachFeature(Feature feature) {
		@SuppressWarnings("unchecked")
		Class<? extends Feature> type = feature.getClass();
		attachFeature(type, feature);
	}
	
	/**
	 * Attaches a feature to this object. Throws an exception if another feature of
	 * the same type is already attached -- in this case, reasons exist to keep either
	 * one, so no sensible default handling can be defined.
	 * 
	 * This function allows to specify the feature type explicitly.
	 * 
	 * @param type the feature type
	 * @param feature the feature to attach
	 */
	public void attachFeature(Class<? extends Feature> type, Feature feature) {
		Feature previous = features.set(type, feature);
		if (previous == null) {
			feature.onFeatureAttached(this);
		} else if (previous != feature) {
			features.set(type, previous);
			throw new IllegalStateException("a feature of type " + feature.getClass() + " is already attached to this object");
		}
	}

	/**
	 * Detaches a feature from this object. Does nothing if the feature isn't currently
	 * attached.
	 * 
	 * This function derives the feature type automatically by taking the class of
	 * the feature object.
	 * 
	 * @param feature the feature to detach
	 * @return true if the feature was removed, false if it wasn't attached
	 */
	public boolean detachFeature(Feature feature) {
		return detachFeature(feature.getClass(), feature);
	}
	
	/**
	 * Detaches a feature from this object. Does nothing if the feature isn't currently
	 * attached.
	 * 
	 * This function allows to specify the feature type explicitly.
	 * 
	 * @param type the feature type
	 * @param feature the feature to detach
	 * @return true if the feature was removed, false if it wasn't attached
	 */
	public boolean detachFeature(Class<? extends Feature> type, Feature feature) {
		Feature previous = features.remove(type);
		if (previous == null) {
			return false;
		} else if (previous == feature) {
			feature.onFeatureDetached(this);
			return true;
		} else {
			features.set(type, previous);
			return false;
		}
	}
	
	/**
	 * Detaches the feature with the specified type from this object. Does nothing
	 * if no such feature is currently attached.
	 * 
	 * @param type the feature type
	 * @return true if the feature was removed, false if it wasn't attached
	 */
	public boolean detachFeature(Class<? extends Feature> type) {
		Feature previous = features.remove(type);
		if (previous == null) {
			return false;
		} else {
			previous.onFeatureDetached(this);
			return true;
		}
	}

	/**
	 * Obtains the feature object for the specified feature class.
	 * 
	 * @param featureClass the feature class
	 * @return the feature object
	 */
	public <T extends Feature> T getFeature(Class<? extends T> featureClass) {
		return features.get(featureClass);
	}

	/**
	 * Draws this object using OpenGL.
	 */
	public void draw() {
		GL11.glPushMatrix();
		for (Behavior behavior : behaviors) {
			behavior.prepareRenderState(this);
		}
		for (Behavior behavior : behaviors) {
			behavior.draw(this);
		}
		GL11.glPopMatrix();
	}

	/**
	 * Handles the game logic for a single game step for this object.
	 */
	public void handleStep() {
		for (Behavior behavior : behaviors) {
			behavior.handleStep(this);
		}
	}

}
