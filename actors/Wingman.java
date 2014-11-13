package actors;

import java.awt.Image;
import java.util.ArrayList;

import projectiles.WingmanBullet;

/**
 * @author Anthony Rodriguez
 *
 */
public class Wingman extends Actors {

	private final float MOVEMENT_SPEED = 3.5f;
	private float x_speed = 0;
	private float y_speed = 0;

	private Image bullet;

	private ArrayList<WingmanBullet> projectiles = new ArrayList<WingmanBullet>();

	public Wingman(Image[] image, Image bullet, int width, int height) {
		super(image, width / 2, height);
		this.bullet = bullet;
		this.canFire = true;
	}

	@Override
	public void update(int width, int height) {
		currentImage++;
		if (currentImage > 2) {
			currentImage = 0;
		}

		if (isMovingLeft || isMovingRight) {
			x_pos += x_speed;
		}

		if (isMovingUp || isMovingDown) {
			y_pos += y_speed;
		}

		if (x_pos <= 5) {
			x_pos = 5;
		}

		if (x_pos >= width - 70) {
			x_pos = width - 70;
		}

		if (y_pos <= 0) {
			y_pos = 0;
		}

		if (y_pos >= height - 70) {
			y_pos = height - 70;
		}
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
	public void moveDiagUpLeft() {
		x_speed = -MOVEMENT_SPEED;
		y_speed = -MOVEMENT_SPEED;

		setMovingUp(true);
		setMovingDown(false);
		setMovingLeft(true);
		setMovingRight(false);
	}

	@Override
	public void moveDiagUpRight() {
		x_speed = MOVEMENT_SPEED;
		y_speed = -MOVEMENT_SPEED;

		setMovingUp(true);
		setMovingDown(false);
		setMovingLeft(false);
		setMovingRight(true);
	}

	@Override
	public void moveDiagDownLeft() {
		x_speed = -MOVEMENT_SPEED;
		y_speed = MOVEMENT_SPEED + 1;

		setMovingUp(false);
		setMovingDown(true);
		setMovingLeft(true);
		setMovingRight(false);
	}

	@Override
	public void moveDiagDownRight() {
		x_speed = MOVEMENT_SPEED;
		y_speed = MOVEMENT_SPEED + 1;

		setMovingUp(false);
		setMovingDown(true);
		setMovingLeft(false);
		setMovingRight(true);
	}

	@Override
	public void stop() {
		x_speed = 0;
		y_speed = 0;
	}

	@Override
	public void firePrimary() {
		if (canFire) {
			WingmanBullet b = new WingmanBullet(bullet, x_pos, y_pos);
			projectiles.add(b);
			canFire = false;
		}
	}

	@Override
	public void explode() {
		System.out.println("Player.explode()");
	}

	public ArrayList<WingmanBullet> getProjectiles() {
		return projectiles;
	}

	@Override
	public boolean isVisible() {
		return true;
	}
}
