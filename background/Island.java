package background;

import java.awt.Dimension;
import java.awt.Image;
import java.util.Random;

import wingman.GameObject;
import enums.IslandType;

/**
 * @author Anthony Rodriguez
 *
 */
public class Island extends GameObject {

	private float y_speed = 1f;

	private Dimension dimension;

	/**
	 * @param image
	 * @param x_pos
	 * @param y_pos
	 */
	public Island(Image image, IslandType islandType, Random generator, Dimension dimension) {
		super(image, ( Math.abs(generator.nextInt() % dimension.width) ), -( Math.abs(generator.nextInt(( 1000 - 10 ) + 1) + 10) ));

		this.dimension = dimension;
	}

	@Override
	public boolean isVisible() {
		return !( x_pos > dimension.width - 32 || x_pos < 0 || y_pos > dimension.height );
	}

	/*
	 * (non-Javadoc)
	 * @see wingman.GameObject#update(int, int)
	 */
	@Override
	public void update(int width, int height) {
		y_pos += y_speed;
	}
}
