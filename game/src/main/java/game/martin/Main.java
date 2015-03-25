/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package game.martin;

import game.engine.core.CoreModule;
import game.engine.frame.FrameLoop;
import game.engine.frame.handlers.ExitHandler;
import game.engine.frame.handlers.HandlerList;
import game.engine.game.FrameHandler;
import game.engine.game.Game;
import game.engine.game.Launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.lwjgl.input.Keyboard;

/**
 * The main class.
 */
public class Main {

	/**
	 * The main method.
	 * 
	 * @param args command-line arguments
	 * @throws Exception on errors
	 */
	public static void main(String[] args) throws Exception {
		
		// prepare scripting engine
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
		
		// apply standard definitions
		Game gameEngine = new Game();
		scriptEngine.put("game", gameEngine);
		scriptEngine.put("core", new CoreModule(scriptEngine));
		
		// apply game-specific definitions
		try (FileInputStream fileInputStream = new FileInputStream(new File("resource/scripts/main.js"))) {
			try (InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream)) {
				scriptEngine.eval(inputStreamReader);
			}
		}

		// define frame handlers and frame loop
		HandlerList handlers = new HandlerList();
		handlers.add(new FrameHandler(gameEngine));
		handlers.add(new ExitHandler(true, Keyboard.KEY_ESCAPE));
		FrameLoop frameLoop = new FrameLoop();
		frameLoop.getRootHandler().setWrappedHandler(handlers);
		
		// run the actual game
		Launcher launcher = new Launcher(args);
		launcher.startup();
		frameLoop.executeLoop(20);
		launcher.shutdown();

		System.exit(0);
	}

}
