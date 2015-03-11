/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package game.martin;

import game.core.GameObject;
import game.core.blockmap.BlockMapBehavior;
import game.core.blockmap.BlockMapEditor;
import game.core.blockmap.BlockMapProbe;
import game.core.blockmap.BlockMapProbe.IsolatedBlockHandler;
import game.core.blockmap.TextureProvider;
import game.core.geometry.Position;
import game.core.gfx.DrawSpriteBehavior;
import game.core.gfx.LeftRightSpriteProvider;
import game.core.gfx.Sprite;
import game.core.gfx.SpriteProvider;
import game.core.movement.AbstractBlockMapJumpAndRunBehavior;
import game.core.movement.LeftRightOrientationBehavior;
import game.core.movement.PositionBehavior;
import game.engine.gfx.Texture;
import game.engine.resource.Resources;
import game.engine.system.EngineLauncher;

import java.util.List;

/**
 * The main class.
 */
public class Main {

	
	public interface ListFactory {
		public List<?> createString();
	}

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
		
		IsolatedBlockHandler<Boolean> solidity = (map, x, y, block) -> (x < 0 || x >= map.getWidth() || y < 0 || y >= map.getHeight() || block == 1 || block == 2);
		BlockMapProbe blockMapProbe = new BlockMapProbe(blockMapBehavior, 0.4f, 0.4f, 0.5f, 0.55f);
		GameObject testObject = new GameObject();
		testObject.attachBehavior(new PositionBehavior(new Position(1.0f, 1.0f)));
		testObject.attachBehavior(new LeftRightOrientationBehavior());
		testObject.attachBehavior(new DrawSpriteBehavior(playerSpriteProvider));
		testObject.attachBehavior(new AbstractBlockMapJumpAndRunBehavior(blockMapProbe, solidity) {
			@Override
			protected float getGravity(GameObject target) {
				return 0.04f;
			}
		});
		launcher.getInitialRegion().getGameObjects().add(testObject);
		
		launcher.loop(20);
		launcher.cleanup();
		System.exit(0);

	}
	
	/**
	 * 
	 */
	private static void drawMap(BlockMapEditor map) {
		map.withBlock(1).hline(0, 15, 20);
		map.withBlock(2).hline(0, 16, 20);
		map.withBlock(2).vline(10, 13, 4);
		map.withBlock(1).setBlock(10, 12);
		map.withBlock(1).setBlock(5, 11);
	}

}
