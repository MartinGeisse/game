/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package game.martin;

import game.core.GameObject;
import game.core.blockmap.BlockMapBehavior;
import game.core.blockmap.BlockMapEditor;
import game.core.blockmap.TextureProvider;
import game.core.geometry.MutablePosition;
import game.core.geometry.Position;
import game.core.gfx.DrawSpriteBehavior;
import game.core.gfx.LeftRightSpriteProvider;
import game.core.gfx.Sprite;
import game.core.gfx.SpriteProvider;
import game.core.movement.AbstractJumpAndRunBehavior;
import game.core.movement.LeftRightOrientationBehavior;
import game.core.movement.PositionBehavior;
import game.engine.gfx.Texture;
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

		Sprite playerLeft = new Sprite(Resources.getTexture("sprites/player-left.png"), 0.7f, 0.7f, 0.7f, 0.7f);
		Sprite playerRight = new Sprite(Resources.getTexture("sprites/player-right.png"), 0.7f, 0.7f, 0.7f, 0.7f);
		SpriteProvider playerSpriteProvider = new LeftRightSpriteProvider(playerLeft, playerRight);
		
		TextureProvider blockMapTextureProvider = new TextureProvider() {
			@Override
			public Texture getBlockTexture(int blockValue) {
				switch (blockValue) {
				
				case 0:
					return null;
					
				case 1:
					return Resources.getTexture("blockmap/ground.png");

				case 2:
					return Resources.getTexture("blockmap/deep-ground.png");
					
				default:
					return Resources.getTexture("blockmap/tile1.png");

				}
			}
		};
		BlockMapBehavior blockMapBehavior = new BlockMapBehavior(30, 30);
		blockMapBehavior.setTextureProvider(blockMapTextureProvider);
		drawMap(new BlockMapEditor(blockMapBehavior));
		GameObject blockMap = new GameObject();
		blockMap.attachBehavior(blockMapBehavior);
		launcher.getInitialRegion().getGameObjects().add(blockMap);
		
		GameObject testObject = new GameObject();
		testObject.attachBehavior(new PositionBehavior(new Position(1.0f, 1.0f)));
		testObject.attachBehavior(new LeftRightOrientationBehavior());
		testObject.attachBehavior(new DrawSpriteBehavior(playerSpriteProvider));
		testObject.attachBehavior(new AbstractJumpAndRunBehavior() {
			
			/* (non-Javadoc)
			 * @see game.core.movement.AbstractJumpAndRunBehavior#getGravity(game.core.GameObject)
			 */
			@Override
			protected float getGravity(GameObject target) {
				return 0.10f;
			}
			
			/* (non-Javadoc)
			 * @see game.core.movement.AbstractJumpAndRunBehavior#adjustHorizontalMovement(game.core.GameObject, float)
			 */
			@Override
			protected float adjustHorizontalMovement(GameObject target, float dx) {
				MutablePosition mutablePosition = target.getBehavior(PositionBehavior.class).getMutablePosition();
				dx = Math.max(dx, 2.0f - mutablePosition.getX());
				dx = Math.min(dx, 40.f - mutablePosition.getX());
				return dx;
			}
			
			/* (non-Javadoc)
			 * @see game.core.movement.AbstractJumpAndRunBehavior#adjustVerticalMovement(game.core.GameObject, float)
			 */
			@Override
			protected float adjustVerticalMovement(GameObject target, float dy) {
				MutablePosition mutablePosition = target.getBehavior(PositionBehavior.class).getMutablePosition();
				return Math.min(dy, 20.0f - mutablePosition.getY());
			}
			
		});
		launcher.getInitialRegion().getGameObjects().add(testObject);
		
		launcher.loop();
		launcher.cleanup();
		System.exit(0);

	}
	
	/**
	 * 
	 */
	private static void drawMap(BlockMapEditor map) {
		map.withBlock(1).hline(0, 15, 20);
		map.withBlock(2).hline(0, 16, 20);
		map.withBlock(2).hline(0, 16, 20);
	}

}
