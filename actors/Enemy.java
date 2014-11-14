package actors;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

import wingman.GameClass;
import enums.EnemyType;

/**
 * @author Anthony Rodriguez
 *
 */
public class Enemy extends Actor {

	private final float MOVEMENT_SPEED;

	private Dimension dimension;

	private Random generator;

	private EnemyType enemyType;

	// protected int healthMax;
	// protected int healthCurrent;

	/**
	 * @param imageArray
	 * @param x_pos
	 * @param y_pos
	 */
	public Enemy(Image[] imageArray, EnemyType enemyType, Random generator, Dimension dimension, ImageObserver observer) {
		super(imageArray, ( Math.abs(generator.nextInt() % dimension.width) ), -( Math.abs(generator.nextInt(( 700 - 100 ) + 1) + 100) ), observer);
		if (enemyType == EnemyType.ENEMY4) {
			y_pos = -y_pos + dimension.height;
			MOVEMENT_SPEED = 1f;
		} else {
			MOVEMENT_SPEED = 2f;
		}

		this.dimension = dimension;
		this.generator = generator;
		this.enemyType = enemyType;
	}

	public boolean isVisible() {
		if (enemyType == EnemyType.ENEMY4) {
			return !( x_pos > dimension.width - rightEdge || y_pos < -bottomEdge || x_pos < leftEdge );
		} else {
			return !( x_pos > dimension.width - rightEdge || y_pos > dimension.height || x_pos < leftEdge );
		}
	}

	@Override
	public void update(int width, int height) {
		dimension = GameClass.getDimension();

		int action = generator.nextInt(100) + 1;

		currentImage++;
		if (currentImage > 2) {
			currentImage = 0;
		}

		if (isMovingDown()) {
			y_pos += MOVEMENT_SPEED;
		}

		if (isMovingUp()) {
			y_pos -= MOVEMENT_SPEED;
		}

		if (enemyType == EnemyType.ENEMY4) {
			rectangleWings.setRect(x_pos, y_pos + 7, 32, 10);
			rectangleBodyTop.setRect(x_pos + 9, y_pos, 14, 32);
		} else {
			rectangleWings.setRect(x_pos, y_pos + 15, 32, 10);
			rectangleBodyTop.setRect(x_pos + 9, y_pos, 14, 32);
		}

		rectangleWings.setBounds(rectangleWings);
		rectangleBodyTop.setBounds(rectangleBodyTop);
	}

	@Override
	public boolean isMovingDown() {
		return !( enemyType == EnemyType.ENEMY4 );
	}

	@Override
	public boolean isMovingUp() {
		return ( enemyType == EnemyType.ENEMY4 );
	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveLeft() {
		x_pos -= MOVEMENT_SPEED;
	}

	@Override
	public void moveRight() {
		x_pos += MOVEMENT_SPEED;
	}

	@Override
	public void moveUpLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveUpRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDownLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDownRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void firePrimary() {
		// TODO Auto-generated method stub

	}

	@Override
	public void fireSecondary() {
		// TODO Auto-generated method stub

	}

	@Override
	public void explode() {
		System.out.println("Enemy Explodes");
	}
}
