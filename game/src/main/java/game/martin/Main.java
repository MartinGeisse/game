/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package game.martin;

import game.core.GameObject;
import game.core.geometry.MutablePosition;
import game.core.geometry.Position;
import game.core.gfx.DrawSpriteBehavior;
import game.core.gfx.LeftRightSpriteProvider;
import game.core.gfx.Sprite;
import game.core.gfx.SpriteProvider;
import game.core.movement.AbstractJumpAndRunBehavior;
import game.core.movement.LeftRightOrientationBehavior;
import game.core.movement.PositionBehavior;
import game.engine.resource.Resources;
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

		Sprite playerLeft = new Sprite(Resources.getTexture("sprites/player-left.png"), 20, 20, 20, 20);
		Sprite playerRight = new Sprite(Resources.getTexture("sprites/player-right.png"), 20, 20, 20, 20);
		SpriteProvider playerSpriteProvider = new LeftRightSpriteProvider(playerLeft, playerRight);
		
		GameObject testObject = new GameObject();
		testObject.attachBehavior(new PositionBehavior(new Position(100 << 8, 100 << 8)));
		testObject.attachBehavior(new LeftRightOrientationBehavior());
		testObject.attachBehavior(new DrawSpriteBehavior(playerSpriteProvider));
		// testObject.attachBehavior(new CursorMoveBehavior(1500));
		testObject.attachBehavior(new AbstractJumpAndRunBehavior() {
			
			/* (non-Javadoc)
			 * @see game.core.movement.AbstractJumpAndRunBehavior#getGravity(game.core.GameObject)
			 */
			@Override
			protected int getGravity(GameObject target) {
				return 300;
			}
			
			/* (non-Javadoc)
			 * @see game.core.movement.AbstractJumpAndRunBehavior#adjustHorizontalMovement(game.core.GameObject, int)
			 */
			@Override
			protected int adjustHorizontalMovement(GameObject target, int dx) {
				MutablePosition mutablePosition = target.getBehavior(PositionBehavior.class).getMutablePosition();
				dx = Math.max(dx, (100 << 8) - mutablePosition.getX());
				dx = Math.min(dx, (700 << 8) - mutablePosition.getX());
				return dx;
			}
			
			/* (non-Javadoc)
			 * @see game.core.movement.AbstractJumpAndRunBehavior#adjustVerticalMovement(game.core.GameObject, int)
			 */
			@Override
			protected int adjustVerticalMovement(GameObject target, int dy) {
				MutablePosition mutablePosition = target.getBehavior(PositionBehavior.class).getMutablePosition();
				return Math.min(dy, (500 << 8) - mutablePosition.getY());
			}
			
		});

		launcher.getInitialRegion().getGameObjects().add(testObject);
		launcher.loop();
		launcher.cleanup();
		System.exit(0);

	}

}
