/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package game.martin;

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
		EngineLauncher launcher = new EngineLauncher(args);
		launcher.launch();
		launcher.loop();
		launcher.cleanup();
		System.exit(0);
	}
	
}
