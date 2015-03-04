/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package game;

import game.engine.frame.FrameLoop;
import name.martingeisse.pacman.application.ApplicationFrameHandlerManager;
import name.martingeisse.pacman.application.static_resources.StaticResources;
import name.martingeisse.stackd.client.util.LwjglNativeLibraryHelper;

import org.apache.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

/**
 * The main class.
 */
public class Main {

	/**
	 * the logger
	 */
	private static Logger logger = Logger.getLogger(Main.class);

	/**
	 * the screenWidth
	 */
	public static int screenWidth = 600;
	
	/**
	 * the screenHeight
	 */
	public static int screenHeight = 600;

	/**
	 * the frameLoop
	 */
	public static FrameLoop frameLoop;
	
	/**
	 * The main method.
	 * @param args command-line arguments
	 * @throws Exception on errors
	 */
	public static void main(String[] args) throws Exception {
		logger.info("Pacman started");
		try {
			
			// parse command-line options
			logger.trace("parsing command line options...");
			boolean fullscreen = false;
			for (final String arg : args) {
				if (arg.equals("-fs")) {
					fullscreen = true;
				}
			}
			logger.trace("command line options parsed");

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

			// initialize LWJGL
			logger.trace("preparing mouse...");
			Mouse.create();
			Mouse.poll();
			
			logger.trace("mouse prepared");
			
			// load images and sounds
			logger.trace("loading resources...");
			final StaticResources staticResources = new StaticResources();
			logger.trace("resources loaded...");

			// build the frame loop
			ApplicationFrameHandlerManager frameHandlerManager = new ApplicationFrameHandlerManager(staticResources);
			frameLoop = new FrameLoop(null);
			frameLoop.getRootHandler().setWrappedHandler(frameHandlerManager.getRootFrameHandler());
			
			// run the main loop
			frameLoop.executeLoop(30);
			
			// clean up
			Display.destroy();
			
		} catch (final Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
		
	}
}
