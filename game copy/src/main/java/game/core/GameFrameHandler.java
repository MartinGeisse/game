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
	 * the game
	 */
	private final Game game;

	/**
	 * Constructor.
	 * @param game the game
	 */
	public GameFrameHandler(Game game) {
		this.game = game;
	}

	/* (non-Javadoc)
	 * @see game.engine.frame.AbstractFrameHandler#draw()
	 */
	@Override
	public void draw() {
		game.draw();
	}

	/* (non-Javadoc)
	 * @see game.engine.frame.AbstractFrameHandler#handleStep()
	 */
	@Override
	public void handleStep() throws BreakFrameLoopException {
		game.handleStep();
	}

}
