/**
 * 
 */
package actors;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import projectiles.PrimaryWeapon;
import projectiles.SecondaryWeapon;
import shapes.Circle;
import enums.AnimationType;
import enums.GameObjectType;

/**
 * @author Anthony Rodriguez
 *
 */
public class MainActor extends Actor {

	private AnimationType primaryWeapon;
	private AnimationType secondaryWeapon;

	private final float MOVEMENT_SPEED = 3.5f;

	public Circle circle;

	/**
	 * @param image
	 * @param type
	 * @param x_pos
	 * @param y_pos
	 */
	public MainActor(AnimationType image, AnimationType primaryWeapon, AnimationType secondaryWeapon, int x_pos, int y_pos) {
		super(image, GameObjectType.PLAYER1, x_pos, y_pos);
		this.primaryWeapon = primaryWeapon;
		this.secondaryWeapon = secondaryWeapon;
		circle = new Circle(x_pos, y_pos, 64);
	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#update(int, int)
	 */
	@Override
	public void update(int width, int height) {
		if (isMovingLeft() || isMovingRight()) {
			x_pos += x_speed;
		}

		if (isMovingUp() || isMovingDown()) {
			y_pos += y_speed;
		}

		if (x_pos <= 5) {
			x_pos = 5;
		}

		if (x_pos >= width - 50) {
			x_pos = width - 50;
		}

		if (y_pos <= 50) {
			y_pos = 50;
		}

		if (y_pos >= height - 50) {
			y_pos = height - 50;
		}
	}

	public void drawCircle(Graphics graphics, ImageObserver observer) {
		graphics.drawOval((int) circle.getX(), (int) circle.getY(), (int) circle.getWidth(), (int) circle.getWidth());
	}

	@Override
	public void moveUp() {
		y_speed = -MOVEMENT_SPEED;

		setMovingUp(true);
		setMovingDown(false);
		setMovingLeft(false);
		setMovingRight(false);
	}

	@Override
	public void moveDown() {
		y_speed = MOVEMENT_SPEED + 1;

		setMovingUp(false);
		setMovingDown(true);
		setMovingLeft(false);
		setMovingRight(false);
	}

	@Override
	public void moveLeft() {
		x_speed = -MOVEMENT_SPEED;

		setMovingUp(false);
		setMovingDown(false);
		setMovingLeft(true);
		setMovingRight(false);
	}

	@Override
	public void moveRight() {
		x_speed = MOVEMENT_SPEED;

		setMovingUp(false);
		setMovingDown(false);
		setMovingLeft(false);
		setMovingRight(true);
	}

	@Override
	public void moveUpLeft() {
		x_speed = -MOVEMENT_SPEED;
		y_speed = -MOVEMENT_SPEED;

		setMovingUp(true);
		setMovingDown(false);
		setMovingLeft(true);
		setMovingRight(false);
	}

	@Override
	public void moveUpRight() {
		x_speed = MOVEMENT_SPEED;
		y_speed = -MOVEMENT_SPEED;

		setMovingUp(true);
		setMovingDown(false);
		setMovingLeft(false);
		setMovingRight(true);
	}

	@Override
	public void moveDownLeft() {
		x_speed = -MOVEMENT_SPEED;
		y_speed = MOVEMENT_SPEED + 1;

		setMovingUp(false);
		setMovingDown(true);
		setMovingLeft(true);
		setMovingRight(false);
	}

	@Override
	public void moveDownRight() {
		x_speed = MOVEMENT_SPEED;
		y_speed = MOVEMENT_SPEED + 1;

		setMovingUp(false);
		setMovingDown(true);
		setMovingLeft(false);
		setMovingRight(true);
	}

	@Override
	public void firePrimary() {
		if (canFirePrimary()) {
			PrimaryWeapon pShot = new PrimaryWeapon(primaryWeapon, x_pos, y_pos);
			primaryWeaponShots.add(pShot);
			setCanFirePrimary(false);
		}
	}

	@Override
	public void fireSecondary() {
		if (canFireSecondary()) {
			SecondaryWeapon sShot = new SecondaryWeapon(secondaryWeapon, x_pos, y_pos);
			secondaryWeaponShots.add(sShot);
			setCanFireSecondary(false);
		}
	}

	@Override
	public void explode() {
		// setAnimation();
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public boolean isColliding(Actor actor) {
		Rectangle[] wingman = getAllRectangles();
		Rectangle[] enemy = actor.getAllRectangles();

		for (int i = 0; i < wingman.length; i++) {
			for (int j = 0; j < enemy.length; j++) {
				if (!actor.isExploding() && circle.intersects(enemy[i])) {
					// if (!actor.isExploding() &&
					// wingman[i].intersects(enemy[j])) {
					return true;
				}
			}
		}

		return false;
	}

}
