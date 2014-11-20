package projectiles;

import java.util.ArrayList;

import shapes.CollisionCircle;
import shapes.CollisionRectangle;
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
	private int y_speed = 0;

	protected AnimationType shooter;

	/**
	 * @param image
	 * @param type
	 * @param x_pos
	 * @param y_pos
	 */
	public Projectile(AnimationType image, AnimationType shooter, int x_pos, int y_pos) {
		super(image, GameObjectType.PROJECTILE, getX(x_pos, shooter, image), getY(y_pos, shooter));
		this.shooter = shooter;
	}

	private static int getX(int x_pos, AnimationType shooter, AnimationType type) {
		return (int) ( Resources.getSpec(shooter.getName()).center_x + x_pos - Resources.getSpec(type.getName()).center_x );
	}

	private static int getY(int y_pos, AnimationType shooter) {
		return (int) ( Resources.getSpec(shooter.getName()).front + y_pos );
	}

	@Override
	public void update(int width, int height) {

		if (shooter == AnimationType.PLAYER1) {
			moveUp();
		} else if (shooter == AnimationType.ENEMY4) {
			moveUp();
		} else {
			moveDown();
		}

		y_pos += y_speed;

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
		return y_pos > -5 || y_pos < 800;
	}

	public boolean isColliding(Actor actor) {
		ArrayList<CollisionCircle> projectileCircles = getCollisionCircles();
		ArrayList<CollisionRectangle> projectileRectangles = getCollisionRectangles();
		ArrayList<CollisionCircle> actorCircles = actor.getCollisionCircles();
		ArrayList<CollisionRectangle> actorRectangles = actor.getCollisionRectangles();

		for (int i = 0; i < projectileCircles.size(); i++) {
			for (int j = 0; j < actorCircles.size(); j++) {
				if (projectileCircles.get(i).intersects(actorCircles.get(j).getBounds2D())) {
					return true;
				}
			}

			for (int j = 0; j < actorRectangles.size(); j++) {
				if (projectileCircles.get(i).intersects(actorRectangles.get(j))) {
					return true;
				}
			}
		}

		for (int i = 0; i < projectileRectangles.size(); i++) {
			for (int j = 0; j < actorCircles.size(); j++) {
				if (projectileRectangles.get(i).intersects(actorCircles.get(j).getBounds2D())) {
					return true;
				}
			}

			for (int j = 0; j < actorRectangles.size(); j++) {
				if (projectileRectangles.get(i).intersects(actorRectangles.get(j))) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void moveLeft() {}

	@Override
	public void moveRight() {}

	@Override
	public void moveUpLeft() {}

	@Override
	public void moveUpRight() {}

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

	@Override
	public void setAlive(boolean isAlive) {}

	@Override
	public void removeCollision() {}
}
