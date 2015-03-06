/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package game.martin;

import game.core.GameObject;
import game.core.geometry.Position;
import game.core.gfx.CursorMoveBehavior;
import game.core.gfx.DrawRectangleBehavior;
import game.core.gfx.PositionBehavior;
import game.engine.gfx.RgbaColor;
import game.engine.system.EngineLauncher;

/**
 * The main class.
 */
public class Main {

	/**
	 * The main method.
	 * @param args command-line arguments
	 * @throws Exception on errors
	 */
	public static void main(String[] args) throws Exception {
		
		GameObject testObject = new GameObject();
		testObject.attachBehavior(new DrawRectangleBehavior(new RgbaColor(255, 255, 255)));
		testObject.attachBehavior(new PositionBehavior(new Position(100 << 8, 100 << 8)));
		testObject.attachBehavior(new CursorMoveBehavior());
		
		EngineLauncher launcher = new EngineLauncher(args);
		launcher.getInitialRegion().getGameObjects().add(testObject);
		launcher.launch();
		launcher.loop();
		launcher.cleanup();
		System.exit(0);
		
	}
	
}
