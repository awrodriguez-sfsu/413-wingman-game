package projectiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import enums.AnimationType;
import enums.GameObjectType;
import wingman.GameObject;

/**
 * @author Anthony Rodriguez
 *
 */
public abstract class Projectile extends GameObject {

	private Rectangle rectangle1 = new Rectangle(0, 0, 0, 0);
	private Rectangle rectangle2 = new Rectangle(0, 0, 0, 0);

	/**
	 * @param image
	 * @param type
	 * @param x_pos
	 * @param y_pos
	 */
	public Projectile(AnimationType image, int x_pos, int y_pos) {
		super(image, GameObjectType.PROJECTILE, x_pos, y_pos);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rectangle1 the rectangle1 to set
	 */
	public void setRectangle1(int x, int y, int width, int height) {
		this.rectangle1.setRect(x, y, width, height);
		this.rectangle1.setBounds(rectangle1);
	}

	/**
	 * @param rectangle2 the rectangle2 to set
	 */
	public void setRectangle2(int x, int y, int width, int height) {
		this.rectangle2.setRect(x, y, width, height);
		this.rectangle2.setBounds(rectangle2);
	}

	public Rectangle[] getAllRectangles() {
		Rectangle[] allRectangles = new Rectangle[3];

		allRectangles[0] = rectangle1;
		allRectangles[1] = rectangle2;

		return allRectangles;
	}

	public void drawCollisionRect(Graphics graphics, ImageObserver observer) {
		graphics.drawRect(rectangle1.x, rectangle1.y, rectangle1.width, rectangle1.height);
		graphics.drawRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
	}

	public abstract void update(int width, int height);

	public abstract boolean isVisible();

}
