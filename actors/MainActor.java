package actors;

import java.awt.Image;
import java.awt.image.ImageObserver;

import projectiles.PrimaryWeapon;

/**
 * @author Anthony Rodriguez
 *
 */
public class MainActor extends Actor {

	private ImageObserver observer;

	private final float MOVEMENT_SPEED = 3.5f;

	/**
	 * @param imageArray
	 * @param x_pos
	 * @param y_pos
	 */
	public MainActor(Image[] imageArray, Image primaryWeapon, int x_pos, int y_pos, ImageObserver observer) {
		super(imageArray, x_pos, y_pos, observer);
		this.observer = observer;
		this.primaryWeapon = primaryWeapon;
	}

	@Override
	public void update(int width, int height) {
		currentImage++;
		if (currentImage > 2) {
			currentImage = 0;
		}

		if (isMovingLeft() || isMovingRight()) {
			x_pos += x_speed;
		}

		if (isMovingUp() || isMovingDown()) {
			y_pos += y_speed;
		}

		if (x_pos <= leftEdge + 5) {
			x_pos = leftEdge + 5;
		}

		if (x_pos >= width - rightEdge + 5) {
			x_pos = width - rightEdge + 5;
		}

		if (y_pos <= topEdge + 5) {
			y_pos = topEdge + 5;
		}

		if (y_pos >= height - bottomEdge + 5) {
			y_pos = height - bottomEdge + 5;
		}

		rectangleWings.setRect(x_pos + 2, y_pos + 21, 59, 13);
		rectangleBodyTop.setRect(x_pos + 17, y_pos + 12, 31, 7);
		rectangleBodyBottom.setRect(x_pos + 13, y_pos + 34, 37, 22);

		rectangleWings.setBounds(rectangleWings);
		rectangleBodyTop.setBounds(rectangleBodyTop);
		rectangleBodyBottom.setBounds(rectangleBodyBottom);
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
		if (canFire()) {
			PrimaryWeapon pShot = new PrimaryWeapon(primaryWeapon, x_pos, y_pos, observer);
			primaryWeaponShots.add(pShot);
			setCanFire(false);
		}
	}

	@Override
	public void fireSecondary() {
		System.out.println("Fire Secondary Weapon");
	}

	@Override
	public void explode() {
		System.out.println("Player.explode()");
	}

	@Override
	public boolean isVisible() {
		return true;
	}
}
