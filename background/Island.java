/**
 * 
 */
package background;

import java.awt.Dimension;
import java.util.Random;

import enums.AnimationType;
import enums.GameObjectType;
import wingman.GameObject;

/**
 * @author Anthony Rodriguez
 *
 */
public class Island extends GameObject {

	private final float MOVEMENT_SPEED = 1f;

	private Dimension dimension;

	/**
	 * @param image
	 * @param type
	 * @param x_pos
	 * @param y_pos
	 */
	public Island(AnimationType image, Random generator, Dimension dimension) {
		super(image, GameObjectType.BACKGROUND, ( Math.abs(generator.nextInt() % dimension.width) ), -( Math.abs(generator.nextInt(( 1000 - 10 ) + 1) + 10) ));
		this.dimension = dimension;
	}

	/*
	 * (non-Javadoc)
	 * @see wingman.GameObject#update(int, int)
	 */
	@Override
	public void update(int width, int height) {
		y_pos += MOVEMENT_SPEED;
	}

	/*
	 * (non-Javadoc)
	 * @see wingman.GameObject#isVisible()
	 */
	@Override
	public boolean isVisible() {
		return !( x_pos > dimension.width - 32 || x_pos < 0 || y_pos > dimension.height );
	}

}
