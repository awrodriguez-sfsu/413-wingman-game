/**
 * 
 */
package actors;

import java.awt.Dimension;
import java.util.Random;

import projectiles.Projectile;
import wingman.GameBase;
import wingman.Resources;
import animations.Animation;
import enums.AnimationType;
import enums.GameObjectType;

/**
 * @author Anthony Rodriguez
 *
 */
public class Enemy extends Actor {

	private final float MOVEMENT_SPEED;

	private boolean isAlive;

	private int actionTime = 0;

	private int primaryVolley = 4;

	private int secondaryVolley = 2;

	private AnimationType type;

	private Dimension dimension;

	private Random generator;

	private AnimationType primaryWeapon;
	private AnimationType secondaryWeapon;

	/**
	 * @param image
	 * @param x_pos
	 * @param y_pos
	 */
	public Enemy(AnimationType image, AnimationType primaryWeapon, AnimationType secondaryWeapon, Random generator, Dimension dimension) {
		super(image, GameObjectType.ENEMY, ( Math.abs(generator.nextInt() % dimension.width) ), -( Math.abs(generator.nextInt(( 700 - 100 ) + 1) + 100) ));

		if (image == AnimationType.ENEMY4) {
			y_pos = -y_pos + dimension.height;
			MOVEMENT_SPEED = 1f;
		} else {
			MOVEMENT_SPEED = 2f;
		}

		this.dimension = dimension;
		this.generator = generator;
		this.type = image;

		this.primaryWeapon = primaryWeapon;
		this.secondaryWeapon = secondaryWeapon;
		this.isAlive = true;
	}

	@Override
	public boolean isVisible() {
		if (type == AnimationType.ENEMY4) {
			return !( y_pos < -bottom_edge );
		} else {
			return !( y_pos > dimension.height );
		}
	}

	@Override
	public boolean isAlive() {
		return isAlive;
	}

	@Override
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	@Override
	public void update(int width, int height) {
		dimension = GameBase.getDimension();

		if (actionTime > 1000) {
			int action = generator.nextInt(5) + 1;
			switch (action) {
				case 1: // Main movement direction
					if (!isMovingLeft() && !isMovingRight()) {
						setMovingUp(type == AnimationType.ENEMY4);
						setMovingDown(type == AnimationType.ENEMY4);
						setMovingLeft(false);
						setMovingRight(false);
					}
					break;
				case 2: // Left movement
					if (!isMovingLeft() && !isMovingRight()) {
						setMovingUp(type == AnimationType.ENEMY4);
						setMovingDown(type == AnimationType.ENEMY4);
						setMovingLeft(true);
						setMovingRight(false);
					}
					break;
				case 3: // Right movement
					if (!isMovingLeft() && !isMovingRight()) {
						setMovingUp(type == AnimationType.ENEMY4);
						setMovingDown(type == AnimationType.ENEMY4);
						setMovingLeft(false);
						setMovingRight(true);
					}
					break;
				case 4:
					if (canFirePrimary()) {
						firePrimary();
					} else {
						setCanFirePrimary(true);
					}
					break;
				case 5:
					if (canFireSecondary()) {
						fireSecondary();
					} else {
						setCanFireSecondary(true);
					}
					break;
				default:
					System.out.println("6?");
					break;
			}
			actionTime = -16;
		}

		if (isMovingUp()) {
			y_pos -= MOVEMENT_SPEED;
		}

		if (isMovingDown()) {
			y_pos += MOVEMENT_SPEED;
		}

		if (isMovingLeft()) {
			x_pos -= MOVEMENT_SPEED;
		}

		if (isMovingRight()) {
			x_pos += MOVEMENT_SPEED;
		}

		// Boundaries
		if (x_pos <= -left_edge + GAME_BORDER) {
			x_pos = (int) ( -left_edge + GAME_BORDER );
			setMovingLeft(false);
		}

		if (x_pos >= width - right_edge - GAME_BORDER) {
			x_pos = (int) ( width - right_edge - GAME_BORDER );
			setMovingRight(false);
		}

		for (int i = 0; i < getCollisionCircles().size(); i++) {
			getCollisionCircles().get(i).update(x_pos, y_pos);
		}

		for (int i = 0; i < getCollisionRectangles().size(); i++) {
			getCollisionRectangles().get(i).update(x_pos, y_pos);
		}

		actionTime += 16;
	}

	@Override
	public boolean isMovingUp() {
		return ( type == AnimationType.ENEMY4 ) && !isExploding();
	}

	@Override
	public boolean isMovingDown() {
		return !( type == AnimationType.ENEMY4 ) && !isExploding();
	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#moveUp()
	 */
	@Override
	public void moveUp() {
		y_pos -= MOVEMENT_SPEED + 1;
	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#moveDown()
	 */
	@Override
	public void moveDown() {
		y_pos += MOVEMENT_SPEED;
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
	public void moveUpLeft() {}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#moveUpRight()
	 */
	@Override
	public void moveUpRight() {}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#moveDownLeft()
	 */
	@Override
	public void moveDownLeft() {}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#moveDownRight()
	 */
	@Override
	public void moveDownRight() {}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#firePrimary()
	 */
	@Override
	public void firePrimary() {
		int spread = 20;
		if (type == AnimationType.ENEMY4) {
			spread = -spread;
		}

		if (canFirePrimary()) {
			Projectile pShot = new Projectile(primaryWeapon, type, x_pos, y_pos);

			shots.add(pShot);

			primaryVolley--;
			if (primaryVolley == 0) {
				setCanFirePrimary(false);
				primaryVolley = 4;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#fireSecondary()
	 */
	@Override
	public void fireSecondary() {
		int spread = 20;
		if (type == AnimationType.ENEMY4) {
			spread = -spread;
		}

		if (canFireSecondary()) {
			Projectile sShot = new Projectile(secondaryWeapon, type, x_pos, y_pos);
			Projectile sShot1 = new Projectile(secondaryWeapon, type, x_pos, y_pos + spread);
			Projectile sShot2 = new Projectile(secondaryWeapon, type, x_pos, y_pos + spread + spread);

			shots.add(sShot);
			shots.add(sShot1);
			shots.add(sShot2);

			secondaryVolley--;
			if (secondaryVolley == 0) {
				setCanFireSecondary(false);
				secondaryVolley = 2;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#explode()
	 */
	@Override
	public void explode() {
		setExploding(true);

		clearCollisions();

		Animation animation = new Animation(true);
		animation.addFrame(Resources.getInstance().explosion1_1, 250);
		animation.addFrame(Resources.getInstance().explosion1_2, 250);
		animation.addFrame(Resources.getInstance().explosion1_3, 250);
		animation.addFrame(Resources.getInstance().explosion1_4, 250);
		animation.addFrame(Resources.getInstance().explosion1_5, 250);
		animation.addFrame(Resources.getInstance().explosion1_6, 250);

		setAnimation(animation);
	}

	@Override
	public void removeCollision() {

	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#isColliding(actors.Actor)
	 */
	@Override
	public boolean isColliding(Actor actor) {
		return false;
	}
}
