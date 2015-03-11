/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.engine.system;

import game.core.Game;
import game.core.GameFrameHandler;
import game.core.Region;
import game.engine.frame.FrameLoop;
import game.engine.frame.handlers.ExitHandler;
import game.engine.frame.handlers.HandlerList;
import game.engine.resource.DefaultResouceLoader;
import game.engine.resource.DefaultResourceManager;
import game.engine.resource.Resources;

import org.apache.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

/**
 * This class prepares the system for running the game and is used to construct
 * the engine object and the game logic objects.
 */
public class EngineLauncher {

	/**
	 * the logger
	 */
	private static Logger logger = Logger.getLogger(EngineLauncher.class);

	/**
	 * the engine
	 */
	private final Game engine;
	
	/**
	 * the frameLoop
	 */
	private final FrameLoop frameLoop;

	/**
	 * the screenWidth
	 */
	private int screenWidth = 800;

	/**
	 * the screenHeight
	 */
	private int screenHeight = 600;

	/**
	 * the fullscreen
	 */
	private boolean fullscreen = false;

	/**
	 * Constructor.
	 * @param args command-line arguments
	 */
	public EngineLauncher(String[] args) {
		engine = new Game();
		engine.setCurrentRegion(new Region());
		frameLoop = new FrameLoop();

		// parse command-line options
		logger.trace("parsing command line options...");
		for (final String arg : args) {
			if (arg.equals("-fs")) {
				fullscreen = true;
			}
		}
		logger.trace("command line options parsed");

	}

	/**
	 * Getter method for the engine.
	 * @return the engine
	 */
	public Game getEngine() {
		return engine;
	}

	/**
	 * Getter method for the initial region.
	 * @return the initial region
	 */
	public Region getInitialRegion() {
		return engine.getCurrentRegion();
	}

	/**
	 * Setter method for the initialRegion.
	 * @param initialRegion the initialRegion to set
	 */
	public void setInitialRegion(Region initialRegion) {
		engine.setCurrentRegion(initialRegion);
	}

	/**
	 * Getter method for the screenWidth.
	 * @return the screenWidth
	 */
	public int getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Setter method for the screenWidth.
	 * @param screenWidth the screenWidth to set
	 */
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	/**
	 * Getter method for the screenHeight.
	 * @return the screenHeight
	 */
	public int getScreenHeight() {
		return screenHeight;
	}

	/**
	 * Setter method for the screenHeight.
	 * @param screenHeight the screenHeight to set
	 */
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	/**
	 * Getter method for the fullscreen.
	 * @return the fullscreen
	 */
	public boolean isFullscreen() {
		return fullscreen;
	}

	/**
	 * Setter method for the fullscreen.
	 * @param fullscreen the fullscreen to set
	 */
	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	/**
	 * Launches the engine.
	 * 
	 * @throws Exception on errors during initialization
	 */
	public void launch() throws Exception {
		logger.info("EngineLauncher.launch() started");

		// include LWJGL native libraries
		LwjglNativeLibraryHelper.prepareNativeLibraries();

		// configure the display
		logger.trace("finding optimal display mode...");
		DisplayMode bestMode = null;
		int bestModeFrequency = -1;
		for (DisplayMode mode : Display.getAvailableDisplayModes()) {
			if (mode.getWidth() == screenWidth && mode.getHeight() == screenHeight && (mode.isFullscreenCapable() || !fullscreen)) {
				if (mode.getFrequency() > bestModeFrequency) {
					bestMode = mode;
					bestModeFrequency = mode.getFrequency();
				}
			}
		}
		if (bestMode == null) {
			bestMode = new DisplayMode(screenWidth, screenHeight);
		}
		logger.trace("setting intended display mode...");
		Display.setDisplayMode(bestMode);
		if (fullscreen) {
			Display.setFullscreen(true);
		}
		logger.trace("switching display mode...");
		Display.create(new PixelFormat(0, 24, 0));
		logger.trace("display initialized");

		// initialize the keyboard
		logger.trace("initializing keyboard...");
		Keyboard.create();
		Keyboard.poll();
		logger.trace("keyboard initialized");
		
		// initialize the mouse
		logger.trace("initializing mouse...");
		Mouse.create();
		Mouse.poll();
		logger.trace("mouse initialized");

		// prepare loading images and sounds
		Resources.setResourceManager(new DefaultResourceManager(new DefaultResouceLoader()));

		// build the frame loop
		HandlerList handlers = new HandlerList();
		handlers.add(new GameFrameHandler(engine));
		handlers.add(new ExitHandler(true, Keyboard.KEY_ESCAPE));
		frameLoop.getRootHandler().setWrappedHandler(handlers);

	}

	/**
	 * Runs the main loop.
	 * 
	 * @param frameInterval the (minimum) frame interval in milliseconds
	 */
	public void loop(int frameInterval) {
		frameLoop.executeLoop(frameInterval);
	}
	
	/**
	 * Cleans up the system resources.
	 */
	public void cleanup() {
		Mouse.destroy();
		Keyboard.destroy();
		Display.destroy();
	}
	
}
