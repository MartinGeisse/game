/**
 * Copyright (c) 2013 Shopgate GmbH
 */

package game.core;

import game.engine.frame.AbstractFrameHandler;
import game.engine.frame.BreakFrameLoopException;

/**
 * The frame handler for the game.
 */
public class GameFrameHandler extends AbstractFrameHandler {

	/**
	 * the engine
	 */
	private final Engine engine;

	/**
	 * Constructor.
	 * @param engine the game engine
	 */
	public GameFrameHandler(Engine engine) {
		this.engine = engine;
	}

	/* (non-Javadoc)
	 * @see game.engine.frame.AbstractFrameHandler#draw()
	 */
	@Override
	public void draw() {
		engine.draw();
	}

	/* (non-Javadoc)
	 * @see game.engine.frame.AbstractFrameHandler#handleStep()
	 */
	@Override
	public void handleStep() throws BreakFrameLoopException {
		engine.handleStep();
	}

}
