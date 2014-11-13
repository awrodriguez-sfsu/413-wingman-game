package wingman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 * @author Anthony Rodriguez
 *
 */
public abstract class GameObject {

	protected Image image = null;
	protected Image[] imageArray = null;
	protected int x_pos;
	protected int y_pos;
	protected int currentImage = 0;

	public GameObject(Image image, int x_pos, int y_pos) {
		this.image = image;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}

	public GameObject(Image[] image, int x_pos, int y_pos) {
		this.imageArray = image;
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
