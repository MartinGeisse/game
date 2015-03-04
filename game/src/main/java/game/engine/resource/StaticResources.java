/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package game.engine.resource;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import name.martingeisse.stackd.client.system.FixedWidthFont;
import name.martingeisse.stackd.client.system.Font;
import name.martingeisse.stackd.client.system.StackdTexture;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;

/**
 * Contains the "static" resources such as image files, sound files, etc.
 */
public final class StaticResources {

	/**
	 * the font
	 */
	private final Font font;

	/**
	 * the bonus
	 */
	private final StackdTexture bonus;

	/**
	 * the normalGameMusics
	 */
	private final Audio[] normalGameMusics;

	/**
	 * the powerPelletMusics
	 */
	private final Audio[] powerPelletMusics;

	/**
	 * the eatingSounds
	 */
	private final Audio[] eatingSounds;

	/**
	 * the powerPelletSound
	 */
	private final Audio powerPelletSound;

	/**
	 * the eatGhostSound
	 */
	private final Audio eatGhostSound;

	/**
	 * the pacmanDiesSound
	 */
	private final Audio pacmanDiesSound;

	/**
	 * the bonusSound
	 */
	private final Audio bonusSound;

	/**
	 * the levelCompletedSound
	 */
	private final Audio levelCompletedSound;

	/**
	 * Constructor.
	 * @throws IOException on I/O errors
	 */
	public StaticResources() throws IOException {
		bonus = new StackdTexture(StaticResources.class, "apple.png", false);
		font = new FixedWidthFont(loadImage("font.png"), 8, 16);
		normalGameMusics = loadOggSoundArray("music-$.ogg");
		powerPelletMusics = loadOggSoundArray("power-$.ogg");
		eatingSounds = loadOggSoundArray("nom-$.ogg");
		powerPelletSound = loadOggSound("power.ogg");
		eatGhostSound = loadOggSound("eat-ghost.ogg");
		pacmanDiesSound = loadOggSound("pacman-dies.ogg");
		bonusSound = loadOggSound("bonus.ogg");
		levelCompletedSound = loadOggSound("level-completed.ogg");
	}

	/**
	 * Loads a PNG image the AWT way.
	 * @param filename the filename of the PNG, relative to the assets folder
	 * @return the luminance buffer
	 * @throws IOException on I/O errors
	 */
	private BufferedImage loadImage(final String filename) throws IOException {
		try (InputStream inputStream = StaticResources.class.getResourceAsStream(filename)) {
			if (inputStream == null) {
				throw new FileNotFoundException("StaticResources: " + filename);
			}
			return ImageIO.read(inputStream);
		}
	}

	/**
	 * @param filename the filename of the OGG, relative to the assets folder
	 * @return the sound
	 * @throws IOException on I/O errors
	 */
	private Audio loadOggSound(final String filename) throws IOException {
		try (InputStream inputStream = StaticResources.class.getResourceAsStream(filename)) {
			if (inputStream == null) {
				throw new FileNotFoundException("StaticResources: " + filename);
			}
			return AudioLoader.getAudio("OGG", inputStream);
		}
	}

	/**
	 * Loads an array of OGG sounds whose names follow a pattern. The pattern
	 * is specified as a string containing a dollar sign that is a placeholder
	 * for the array indices, starting at 0. The lowest value for which no
	 * file exists determines the size of the array.
	 * 
	 * @param filenamePattern the file name pattern containing a dollar sign
	 * @return the sound objects
	 * @throws IOException on I/O errors
	 */
	private Audio[] loadOggSoundArray(final String filenamePattern) throws IOException {
		List<Audio> sounds = new ArrayList<>();
		while (true) {
			try {
				sounds.add(loadOggSound(filenamePattern.replace("$", "" + sounds.size())));
			} catch (FileNotFoundException e) {
				break;
			}
		}
		return sounds.toArray(new Audio[sounds.size()]);
	}

	/**
	 * Getter method for the bonus.
	 * @return the bonus
	 */
	public StackdTexture getBonus() {
		return bonus;
	}

	/**
	 * Getter method for the font.
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Getter method for the titleScreenMusic.
	 * @return the titleScreenMusic
	 */
	public Audio getTitleScreenMusic() {
		return normalGameMusics[0];
	}

	/**
	 * Getter method for the normalGameMusics.
	 * @return the normalGameMusics
	 */
	public Audio[] getNormalGameMusics() {
		return normalGameMusics;
	}

	/**
	 * Getter method for the powerPelletMusics.
	 * @return the powerPelletMusics
	 */
	public Audio[] getPowerPelletMusics() {
		return powerPelletMusics;
	}

	/**
	 * Getter method for the eatingSounds.
	 * @return the eatingSounds
	 */
	public Audio[] getEatingSounds() {
		return eatingSounds;
	}

	/**
	 * Getter method for the powerPelletSound.
	 * @return the powerPelletSound
	 */
	public Audio getPowerPelletSound() {
		return powerPelletSound;
	}

	/**
	 * Getter method for the eatGhostSound.
	 * @return the eatGhostSound
	 */
	public Audio getEatGhostSound() {
		return eatGhostSound;
	}

	/**
	 * Getter method for the pacmanDiesSound.
	 * @return the pacmanDiesSound
	 */
	public Audio getPacmanDiesSound() {
		return pacmanDiesSound;
	}

	/**
	 * Getter method for the bonusSound.
	 * @return the bonusSound
	 */
	public Audio getBonusSound() {
		return bonusSound;
	}

	/**
	 * Getter method for the levelCompletedSound.
	 * @return the levelCompletedSound
	 */
	public Audio getLevelCompletedSound() {
		return levelCompletedSound;
	}

}
