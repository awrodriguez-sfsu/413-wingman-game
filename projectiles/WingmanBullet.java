package projectiles;

import java.awt.Image;

import wingman.GameObject;

/**
 * @author Anthony Rodriguez
 *
 */
public class WingmanBullet extends GameObject {

	private final int MOVEMENT_SPEED = 5;
	private int y_speed = 0;

	public WingmanBullet(Image image, int x_pos, int y_pos) {
		super(image, x_pos + 16, y_pos - 10);
	}

	@Override
	public void update(int width, int height) {
		y_pos += y_speed;
	}

	public void moveUp() {
		y_speed = -MOVEMENT_SPEED;
	}

	public boolean isVisible() {
		return y_pos > -5;
	}
}
