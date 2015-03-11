/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package game.martin;

import game.core.GameObject;
import game.core.blockmap.AbstractForeachBlockBehaviour;
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
import game.core.particle.GameObjectParticleSpawner;
import game.core.particle.ParticleSpawner;
import game.engine.gfx.Texture;
import game.engine.resource.Resources;
import game.engine.system.EngineLauncher;

/**
 * The main class.
 */
public class Main {

	/**
	 * the coinSprite
	 */
	private static Sprite coinSprite;
	
	/**
	 * the particleSpawner
	 */
	private static ParticleSpawner particleSpawner;

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
		coinSprite = new Sprite(Resources.getTexture("sprites/coin.png"), 0.5f, 0.5f, 0.5f, 0.5f);
		particleSpawner = new GameObjectParticleSpawner(launcher.getInitialRegion());

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

				case 3:
					return Resources.getTexture("blockmap/coin.png");

				case 4:
					return Resources.getTexture("blockmap/coinbox.png");

				case 5:
					return Resources.getTexture("blockmap/emptybox.png");

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

		IsolatedBlockHandler<Boolean> solidity = new Solidity();
		BlockMapProbe blockMapProbe = new BlockMapProbe(blockMapBehavior, 0.4f, 0.4f, 0.5f, 0.55f);
		GameObject player = new GameObject();
		player.attachBehavior(new PositionBehavior(new Position(1.0f, 1.0f)));
		player.attachBehavior(new LeftRightOrientationBehavior());
		player.attachBehavior(new DrawSpriteBehavior(playerSpriteProvider));
		player.attachBehavior(new AbstractBlockMapJumpAndRunBehavior(blockMapProbe, solidity) {
			@Override
			protected float getGravity(GameObject target) {
				return 0.04f;
			}
		});
		player.attachBehavior(new AbstractForeachBlockBehaviour(blockMapProbe) {
			@Override
			public Void handle(BlockMapBehavior blockMap, int x, int y, int blockValue) {
				if (blockValue == 3) {
					Resources.getSound("coin.wav").playAsSoundEffect(1.0f, 0.5f, false);
					blockMap.setBlock(x, y, 0);
				}
				return null;
			}
		});
		launcher.getInitialRegion().getGameObjects().add(player);

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
		map.withBlock(3).hline(15, 14, 5);
		map.withBlock(4).setBlock(15, 11);
		map.withBlock(4).setBlock(17, 11);
		map.withBlock(4).setBlock(19, 11);
	}

	/**
	 *
	 */
	private static class Solidity implements IsolatedBlockHandler<Boolean> {

		/* (non-Javadoc)
		 * @see game.core.blockmap.BlockMapProbe.IsolatedBlockHandler#handle(game.core.blockmap.BlockMapBehavior, int, int, int)
		 */
		@Override
		public Boolean handle(BlockMapBehavior map, int x, int y, int block) {
			if (x < 0 || x >= map.getWidth() || y < 0 || y >= map.getHeight()) {
				return true;
			}
			if (block == 0 || block == 3) {
				return false;
			}
			if (block == 4) {
				map.setBlock(x, y, 5);
				Resources.getSound("coin.wav").playAsSoundEffect(1.0f, 0.5f, false);
				particleSpawner.spawnParticle(x + 0.5f, y + 0.5f, 0, -1.0f, 0.1f, coinSprite);
			}
			return true;
		}

	};

}
