package projectiles;

import enums.AnimationType;

/**
 * @author Anthony Rodriguez
 *
 */
public class SecondaryWeapon extends Projectile {

	private final int MOVEMENT_SPEED = 5;
	private int y_speed = 0;

	public SecondaryWeapon(AnimationType image, int x_pos, int y_pos) {
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
