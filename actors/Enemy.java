/**
 * 
 */
package actors;

import java.awt.Dimension;
import java.util.Random;

import wingman.GameBase;
import wingman.Resources;
import animations.Animation;
import enums.AnimationType;
import enums.EnemyType;
import enums.GameObjectType;

/**
 * @author Anthony Rodriguez
 *
 */
public class Enemy extends Actor {

	private final float MOVEMENT_SPEED;

	private EnemyType enemyType;

	private Dimension dimension;

	private Random generator;

	private AnimationType primaryWeapon;
	private AnimationType secondaryWeapon;

	private AnimationType explosion;

	private Animation animation = null;

	/**
	 * @param image
	 * @param x_pos
	 * @param y_pos
	 */
	public Enemy(AnimationType image, AnimationType primaryWeapon, AnimationType secondaryWeapon, AnimationType explosion, EnemyType type, Random generator, Dimension dimension) {
		super(image, GameObjectType.ENEMY, ( Math.abs(generator.nextInt() % dimension.width) ), -( Math.abs(generator.nextInt(( 700 - 100 ) + 1) + 100) ));

		if (type == EnemyType.ENEMY4) {
			y_pos = -y_pos + dimension.height;
			MOVEMENT_SPEED = 1f;
		} else {
			MOVEMENT_SPEED = 2f;
		}

		this.dimension = dimension;
		this.generator = generator;
		this.enemyType = type;

		this.primaryWeapon = primaryWeapon;
		this.secondaryWeapon = secondaryWeapon;
		this.explosion = explosion;
	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#isVisible()
	 */
	@Override
	public boolean isVisible() {
		if (enemyType == EnemyType.ENEMY4) {
			return !( x_pos > dimension.width || y_pos < 0 || x_pos < 0 );
		} else {
			return !( x_pos > dimension.width || y_pos > dimension.height || x_pos < 0 );
		}
	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#update(int, int)
	 */
	@Override
	public void update(int width, int height) {
		dimension = GameBase.getDimension();

		int action = generator.nextInt(6) + 1;

		if (isMovingUp()) {
			y_pos -= MOVEMENT_SPEED;
		}

		if (isMovingDown()) {
			y_pos += MOVEMENT_SPEED;
		}

		switch (enemyType) {
			case ENEMY1:
				setRectangleWings(x_pos, y_pos + 15, 32, 10);
				setRectangleBodyTop(x_pos + 9, y_pos + 1, 14, 14);
				setRectangleBodyBottom(x_pos + 11, y_pos + 25, 10, 7);
				break;
			case ENEMY2:
				setRectangleWings(x_pos, y_pos + 15, 32, 10);
				setRectangleBodyTop(x_pos + 10, y_pos, 12, 15);
				setRectangleBodyBottom(x_pos + 11, y_pos + 25, 10, 7);
				break;
			case ENEMY3:
				setRectangleWings(x_pos, y_pos + 15, 32, 10);
				setRectangleBodyTop(x_pos + 9, y_pos + 1, 14, 14);
				setRectangleBodyBottom(x_pos + 11, y_pos + 25, 10, 7);
				break;
			case ENEMY4:
				setRectangleWings(x_pos, y_pos + 7, 32, 10);
				setRectangleBodyTop(x_pos + 11, y_pos, 10, 7);
				setRectangleBodyBottom(x_pos + 8, y_pos + 17, 16, 15);
				break;
		}

		if (animation != null) {
			animation.update(17, this);
		}
	}

	@Override
	public boolean isMovingUp() {
		return ( enemyType == EnemyType.ENEMY4 ) && !isExploding();
	}

	@Override
	public boolean isMovingDown() {
		return !( enemyType == EnemyType.ENEMY4 ) && !isExploding();
	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#moveUp()
	 */
	@Override
	public void moveUp() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#moveDown()
	 */
	@Override
	public void moveDown() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#moveLeft()
	 */
	@Override
	public void moveLeft() {
		x_pos -= MOVEMENT_SPEED;
	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#moveRight()
	 */
	@Override
	public void moveRight() {
		x_pos += MOVEMENT_SPEED;
	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#moveUpLeft()
	 */
	@Override
	public void moveUpLeft() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#moveUpRight()
	 */
	@Override
	public void moveUpRight() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#moveDownLeft()
	 */
	@Override
	public void moveDownLeft() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#moveDownRight()
	 */
	@Override
	public void moveDownRight() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#firePrimary()
	 */
	@Override
	public void firePrimary() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#fireSecondary()
	 */
	@Override
	public void fireSecondary() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#explode()
	 */
	@Override
	public void explode() {
		setExploding(true);

		animation = new Animation(true);
		animation.addFrame(Resources.getInstance().explosion1_1, 250);
		animation.addFrame(Resources.getInstance().explosion1_2, 250);
		animation.addFrame(Resources.getInstance().explosion1_3, 250);
		animation.addFrame(Resources.getInstance().explosion1_4, 250);
		animation.addFrame(Resources.getInstance().explosion1_5, 250);
		animation.addFrame(Resources.getInstance().explosion1_6, 250);

		setAnimation(animation);
	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#isColliding(actors.Actor)
	 */
	@Override
	public boolean isColliding(Actor actor) {
		// TODO Auto-generated method stub
		return false;
	}

}
