package projectiles;

import wingman.GameObject;
import enums.AnimationType;
import enums.GameObjectType;

/**
 * @author Anthony Rodriguez
 *
 */
public class SecondaryWeapon extends GameObject {

	private final int MOVEMENT_SPEED = 5;
	private int y_speed = 0;

	public SecondaryWeapon(AnimationType image, int x_pos, int y_pos) {
		super(image, GameObjectType.PROJECTILE, x_pos + 16, y_pos - 10);
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
