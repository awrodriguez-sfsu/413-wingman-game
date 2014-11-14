package wingman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 * @author Anthony Rodriguez
 *
 */
public abstract class GameObject {

	protected boolean isSolid;

	protected int topEdge = 0;
	protected int bottomEdge;
	protected int leftEdge = 0;
	protected int rightEdge;
	protected int widthMiddle;
	protected int heightMiddle;

	protected int x_pos;
	protected int y_pos;
	protected int currentImage = 0;

	protected Image image = null;
	protected Image[] imageArray = null;

	public GameObject(String image, int x_pos, int y_pos, ImageObserver observer) {
		this.image = GameClass.images.get(image);
		this.rightEdge = this.image.getWidth(observer);
		this.bottomEdge = this.image.getHeight(observer);
		this.widthMiddle = rightEdge / 2;
		this.heightMiddle = bottomEdge / 2;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}

	public GameObject(String[] imageArray, int x_pos, int y_pos, ImageObserver observer) {
		this.imageArray = GameClass.imageArrays.get(imageArray[0]);
		this.rightEdge = this.imageArray[0].getWidth(observer);
		this.bottomEdge = this.imageArray[0].getHeight(observer);
		this.widthMiddle = rightEdge / 2;
		this.heightMiddle = bottomEdge / 2;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}

	public void draw(Graphics graphics, ImageObserver observer) {
		if (image != null) {
			graphics.drawImage(image, x_pos, y_pos, observer);
		} else if (imageArray.length > 0) {
			graphics.drawImage(imageArray[currentImage], x_pos, y_pos, observer);
		}
	}

	public abstract void update(int width, int height);

	public abstract boolean isVisible();

}
