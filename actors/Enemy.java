package actors;

import java.awt.Dimension;
import java.awt.Image;
import java.util.Random;

import wingman.GameClass;
import wingman.GameObject;
import enums.EnemyType;

/**
 * @author Anthony Rodriguez
 *
 */
public class Enemy extends GameObject {

	private float y_speed = 2f;

	private Dimension dimension;

	private EnemyType enemyType;

	// protected int healthMax;
	// protected int healthCurrent;

	/**
	 * @param imageArray
	 * @param x_pos
	 * @param y_pos
	 */
	public Enemy(Image[] imageArray, EnemyType enemyType, Random generator, Dimension dimension) {
		super(imageArray, ( Math.abs(generator.nextInt() % dimension.width) ), -( Math.abs(generator.nextInt(( 700 - 100 ) + 1) + 100) ));
		if (enemyType == EnemyType.ENEMY4) {
			y_pos = -y_pos + dimension.height;
			y_speed = 1f;
		}

		this.dimension = dimension;
		this.enemyType = enemyType;
	}

	public boolean isVisible() {
		switch (enemyType) {
			case ENEMY4:
				return !( x_pos > dimension.width - 32 || y_pos < -32 || x_pos < 0 );
			default:
				return !( x_pos > dimension.width - 32 || y_pos > dimension.height || x_pos < 0 );
		}
	}

	@Override
	public void update(int width, int height) {
		dimension = GameClass.getDimension();

		currentImage++;
		if (currentImage > 2) {
			currentImage = 0;
		}

		switch (enemyType) {
			case ENEMY4:
				y_pos -= y_speed;
				break;
			default:
				y_pos += y_speed;
				break;
		}
	}
}
