/**
 * Copyright (c) 2010 Martin Geisse
 * <p>
 * This file is distributed under the terms of the MIT license.
 */

package game.engine.frame.handlers;

import game.engine.frame.AbstractFrameHandler;
import game.engine.frame.BreakFrameLoopException;
import game.engine.gfx.Font;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.util.concurrent.ConcurrentLinkedQueue;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.glWindowPos2i;

/**
 * Displays one or more messages for a certain time. Messages are rendered
 * using a {@link Font}. Each message will stay for the same configurable
 * time. Multiple messages will be stacked vertically if necessary.
 *
 * Stacked messages will be drawn downwards, starting at a configurable
 * raster position. Subclasses might want to implement {@link #prepareOpenGlState()}
 * since there is no way to predict OpenGL state in a frame handler.
 */
public class FlashMessageHandler extends AbstractFrameHandler {

	/**
	 * the font
	 */
	private Font font;

	/**
	 * the displayTime
	 */
	private long displayTime;

	/**
	 * the fadeTime
	 */
	private long fadeTime;

	/**
	 * the leftOffset
	 */
	private int leftOffset;

	/**
	 * the topOffset
	 */
	private int topOffset;

	/**
	 * the queue
	 */
	private final ConcurrentLinkedQueue<Entry> queue;

	/**
	 * Constructor.
	 * @param font the font to use
	 */
	public FlashMessageHandler(final Font font) {
		this.font = font;
		this.displayTime = 3000;
		this.fadeTime = 1000;
		this.leftOffset = 0;
		this.topOffset = 0;
		this.queue = new ConcurrentLinkedQueue<FlashMessageHandler.Entry>();
	}

	/**
	 * Getter method for the font.
	 * @return the font
	 */
	public final Font getFont() {
		return font;
	}

	/**
	 * Setter method for the font.
	 * @param font the font to set
	 */
	public final void setFont(final Font font) {
		this.font = font;
	}

	/**
	 * Getter method for the displayTime.
	 * @return the displayTime
	 */
	public final long getDisplayTime() {
		return displayTime;
	}

	/**
	 * Setter method for the displayTime.
	 * @param displayTime the displayTime to set
	 */
	public final void setDisplayTime(final long displayTime) {
		this.displayTime = displayTime;
	}

	/**
	 * Getter method for the fadeTime.
	 * @return the fadeTime
	 */
	public final long getFadeTime() {
		return fadeTime;
	}

	/**
	 * Setter method for the fadeTime.
	 * @param fadeTime the fadeTime to set
	 */
	public final void setFadeTime(final long fadeTime) {
		this.fadeTime = fadeTime;
	}

	/**
	 * Getter method for the leftOffset.
	 * @return the leftOffset
	 */
	public int getLeftOffset() {
		return leftOffset;
	}

	/**
	 * Setter method for the leftOffset.
	 * @param leftOffset the leftOffset to set
	 */
	public void setLeftOffset(final int leftOffset) {
		this.leftOffset = leftOffset;
	}

	/**
	 * Getter method for the topOffset.
	 * @return the topOffset
	 */
	public int getTopOffset() {
		return topOffset;
	}

	/**
	 * Setter method for the topOffset.
	 * @param topOffset the topOffset to set
	 */
	public void setTopOffset(final int topOffset) {
		this.topOffset = topOffset;
	}

	/**
	 * Adds a message to show to the user.
	 * @param message the message to add
	 */
	public final void addMessage(final String message) {
		queue.add(new Entry(System.currentTimeMillis(), message));
	}

	/**
	 * This method may be implemented to prepare the necessary OpenGL
	 * state, such as the current raster position, drawing color, and so on.
	 */
	protected void prepareOpenGlState() {
	}

	/* (non-Javadoc)
	 * @see game.engine.frame.AbstractFrameHandler#draw()
	 */
	@Override
	public void draw() {
		prepareOpenGlState();
		int height = Display.getHeight();
		final long now = System.currentTimeMillis();
		int i = 0;
		glBindTexture(GL_TEXTURE_2D, 0);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		GL11.glPixelTransferf(GL11.GL_RED_SCALE, 0.0f);
		GL11.glPixelTransferf(GL11.GL_GREEN_SCALE, 0.0f);
		GL11.glPixelTransferf(GL11.GL_BLUE_SCALE, 0.0f);
		GL11.glPixelTransferf(GL11.GL_RED_BIAS, 1.0f);
		GL11.glPixelTransferf(GL11.GL_GREEN_BIAS, 1.0f);
		GL11.glPixelTransferf(GL11.GL_BLUE_BIAS, 1.0f);
		GL11.glPixelTransferf(GL11.GL_ALPHA_BIAS, 0.0f);
		for (final Entry entry : queue) {
			// fading doesn't work yet
			final long age = (now - entry.publishTime);
			float brightness;
			if (age < displayTime) {
				brightness = 1.0f;
			} else {
				brightness = 1.0f - ((age - displayTime) / (float) fadeTime);
			}
			GL11.glPixelTransferf(GL11.GL_ALPHA_SCALE, brightness);
			glWindowPos2i(leftOffset, height - topOffset - i * (2 * font.getCharacterHeight() + 4));
			font.drawText(entry.message, 2, Font.ALIGN_LEFT, Font.ALIGN_TOP);
			i++;
		}
		GL11.glPixelTransferf(GL11.GL_RED_SCALE, 1.0f);
		GL11.glPixelTransferf(GL11.GL_GREEN_SCALE, 1.0f);
		GL11.glPixelTransferf(GL11.GL_BLUE_SCALE, 1.0f);
		GL11.glPixelTransferf(GL11.GL_ALPHA_SCALE, 1.0f);
		GL11.glPixelTransferf(GL11.GL_RED_BIAS, 0.0f);
		GL11.glPixelTransferf(GL11.GL_GREEN_BIAS, 0.0f);
		GL11.glPixelTransferf(GL11.GL_BLUE_BIAS, 0.0f);
		GL11.glPixelTransferf(GL11.GL_ALPHA_BIAS, 0.0f);
	}

	/* (non-Javadoc)
	 * @see name.martingeisse.stackd.client.frame.AbstractFrameHandler#handleStep()
	 */
	@Override
	public final void handleStep() throws BreakFrameLoopException {
		final long now = System.currentTimeMillis();
		final long totalTimeToLive = (displayTime + fadeTime);
		while (!queue.isEmpty()) {
			final Entry next = queue.peek();
			final long age = (now - next.publishTime);
			if (age < totalTimeToLive) {
				break;
			}
			queue.remove();
		}
	}

	/**
	 * Used to represent messages being displayed.
	 */
	final static class Entry {

		/**
		 * the publishTime
		 */
		final long publishTime;

		/**
		 * the message
		 */
		final String message;

		/**
		 * Constructor.
		 * @param publishTime the time at which this message was published
		 * @param message the message text
		 */
		Entry(final long publishTime, final String message) {
			this.publishTime = publishTime;
			this.message = message;
		}

	}

}
