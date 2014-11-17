/**
 * 
 */
package wingman;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import animations.Animation;
import enums.AnimationType;
import enums.GameObjectType;

/**
 * @author Anthony Rodriguez
 *
 */
public abstract class GameObject {

	protected int x_pos;
	protected int y_pos;

	private Animation animation = null;

	private GameObjectType type;

	public GameObject(AnimationType image, GameObjectType type, int x_pos, int y_pos) {
		this.animation = GameBase.animations.get(image);
		this.type = type;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}

	public void draw(Graphics graphics, ImageObserver observer) {
		graphics.drawImage(animation.getImage(), x_pos, y_pos, observer);
		animation.update(16, this);
	}

	/**
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * @return the type
	 */
	public GameObjectType getType() {
		return type;
	}

	/**
	 * @param animation the animation to set
	 */
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(GameObjectType type) {
		this.type = type;
	}

	public abstract void update(int width, int height);

	public abstract boolean isVisible();
}
