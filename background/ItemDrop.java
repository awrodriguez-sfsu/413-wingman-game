package background;

import java.awt.Dimension;

import wingman.GameBase;
import actors.Actor;
import enums.AnimationType;
import enums.GameObjectType;

/**
 * @author Anthony Rodriguez
 *
 */
public class ItemDrop extends Actor {

	private final float MOVEMENT_SPEED = 1f;

	/**
	 * @param image
	 * @param type
	 * @param x_pos
	 * @param y_pos
	 */
	public ItemDrop(AnimationType image, GameObjectType type, int x_pos, int y_pos, Dimension dimension) {
		super(image, type, x_pos, y_pos);
		ItemDrop.dimension = dimension;
	}

	@Override
	public void update(int width, int height) {
		dimension = GameBase.getDimension();

		y_pos += MOVEMENT_SPEED;

		for (int i = 0; i < getCollisionCircles().size(); i++) {
			getCollisionCircles().get(i).update(x_pos, y_pos);
		}

		for (int i = 0; i < getCollisionRectangles().size(); i++) {
			getCollisionRectangles().get(i).update(x_pos, y_pos);
		}
	}

	@Override
	public boolean isVisible() {
		return !( x_pos > dimension.width - 32 || x_pos < 0 || y_pos > dimension.height );
	}

	@Override
	public boolean inPlay() {
		return isVisible();
	}

	@Override
	public void moveUp() {}

	@Override
	public void moveDown() {}

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
}
