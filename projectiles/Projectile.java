package projectiles;

import java.awt.Dimension;

import wingman.GameBase;
import wingman.Resources;
import actors.Actor;
import enums.AnimationType;
import enums.GameObjectType;

/**
 * @author Anthony Rodriguez
 *
 */
public class Projectile extends Actor {

	private final int MOVEMENT_SPEED = 5;

	protected AnimationType shooter;

	/**
	 * @param image
	 * @param type
	 * @param x_pos
	 * @param y_pos
	 */
	public Projectile(AnimationType image, AnimationType shooter, int x_pos, int y_pos, Dimension dimension) {
		super(image, GameObjectType.PROJECTILE, getX(x_pos, shooter, image), getY(y_pos, shooter));
		this.shooter = shooter;
		this.x_speed = 0;
		this.y_speed = 0;
		Projectile.dimension = dimension;
	}

	private static int getX(int x_pos, AnimationType shooter, AnimationType type) {
		return (int) ( Resources.getInstance().getSolidSpec(shooter.getName()).center_x + x_pos - Resources.getInstance().getSolidSpec(type.getName()).center_x );
	}

	private static int getY(int y_pos, AnimationType shooter) {
		return (int) ( Resources.getInstance().getSolidSpec(shooter.getName()).front + y_pos );
	}

	@Override
	public void update(int width, int height) {

		dimension = GameBase.getDimension();

		if (shooter == AnimationType.PLAYER1 || shooter == AnimationType.PLAYER2
				|| shooter == AnimationType.ENEMY4) {

			if (getAnimationType() == AnimationType.BULLET_LEFT) {
				moveUpLeft();
			} else if (getAnimationType() == AnimationType.BULLET_RIGHT) {
				moveUpRight();
			} else {
				moveUp();
			}

		} else {
			moveDown();
		}

		y_pos += y_speed;

		x_pos += x_speed;

		for (int i = 0; i < getCollisionCircles().size(); i++) {
			getCollisionCircles().get(i).update(x_pos, y_pos);
		}

		for (int i = 0; i < getCollisionRectangles().size(); i++) {
			getCollisionRectangles().get(i).update(x_pos, y_pos);
		}
	}

	public void moveUp() {
		y_speed = -MOVEMENT_SPEED;
	}

	public void moveDown() {
		y_speed = MOVEMENT_SPEED;
	}

	public boolean isVisible() {
		return !( x_pos > dimension.width - 32 || x_pos < 0 || y_pos > dimension.height );
	}

	@Override
	public boolean inPlay() {
		return isVisible();
	}

	@Override
	public void moveLeft() {}

	@Override
	public void moveRight() {}

	@Override
	public void moveUpLeft() {
		y_speed = -MOVEMENT_SPEED;
		x_speed = -MOVEMENT_SPEED;
	}

	@Override
	public void moveUpRight() {
		y_speed = -MOVEMENT_SPEED;
		x_speed = MOVEMENT_SPEED;
	}

	@Override
	public void moveDownLeft() {}

	@Override
	public void moveDownRight() {}

	@Override
	public void firePrimary() {}

	@Override
	public void fireSecondary() {}

	@Override
	public void explode() {}

	@Override
	public boolean isAlive() {
		return false;
	}
}
