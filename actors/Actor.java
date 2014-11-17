/**
 * 
 */
package actors;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import projectiles.PrimaryWeapon;
import projectiles.SecondaryWeapon;
import wingman.GameObject;
import enums.AnimationType;
import enums.GameObjectType;

/**
 * @author Anthony Rodriguez
 *
 */
public abstract class Actor extends GameObject {

	private Rectangle rectangleWings = new Rectangle(0, 0, 0, 0);
	private Rectangle rectangleBodyTop = new Rectangle(0, 0, 0, 0);
	private Rectangle rectangleBodyBottom = new Rectangle(0, 0, 0, 0);

	private boolean isMovingUp;
	private boolean isMovingDown;
	private boolean isMovingLeft;
	private boolean isMovingRight;
	private boolean canFirePrimary;
	private boolean canFireSecondary;
	private boolean isAlive;
	private boolean isExploding;

	protected float x_speed;
	protected float y_speed;

	protected ArrayList<PrimaryWeapon> primaryWeaponShots = new ArrayList<PrimaryWeapon>();
	protected ArrayList<SecondaryWeapon> secondaryWeaponShots = new ArrayList<SecondaryWeapon>();

	public Actor(AnimationType image, GameObjectType type, int x_pos, int y_pos) {
		super(image, type, x_pos, y_pos);
		this.isAlive = true;
	}

	/**
	 * @return the isMovingUp
	 */
	public boolean isMovingUp() {
		return isMovingUp;
	}

	/**
	 * @return the isMovingDown
	 */
	public boolean isMovingDown() {
		return isMovingDown;
	}

	/**
	 * @return the isMovingLeft
	 */
	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	/**
	 * @return the isMovingRight
	 */
	public boolean isMovingRight() {
		return isMovingRight;
	}

	/**
	 * @return the canFire
	 */
	public boolean canFirePrimary() {
		return canFirePrimary;
	}

	/**
	 * @return the canFireSecondary
	 */
	public boolean canFireSecondary() {
		return canFireSecondary;
	}

	/**
	 * @return the isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * @return the isExploding
	 */
	public boolean isExploding() {
		return isExploding;
	}

	/**
	 * @param isMovingUp the isMovingUp to set
	 */
	public void setMovingUp(boolean isMovingUp) {
		this.isMovingUp = isMovingUp;
	}

	/**
	 * @param isMovingDown the isMovingDown to set
	 */
	public void setMovingDown(boolean isMovingDown) {
		this.isMovingDown = isMovingDown;
	}

	/**
	 * @param isMovingLeft the isMovingLeft to set
	 */
	public void setMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}

	/**
	 * @param isMovingRight the isMovingRight to set
	 */
	public void setMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}

	/**
	 * @param canFire the canFire to set
	 */
	public void setCanFirePrimary(boolean canFire) {
		this.canFirePrimary = canFire;
	}

	/**
	 * @param canFireSecondary the canFireSecondary to set
	 */
	public void setCanFireSecondary(boolean canFireSecondary) {
		this.canFireSecondary = canFireSecondary;
	}

	/**
	 * @param isAlive the isAlive to set
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * @param isExploding the isExploding to set
	 */
	public void setExploding(boolean isExploding) {
		this.isExploding = isExploding;
	}

	/**
	 * @return the primaryWeaponShots
	 */
	public ArrayList<PrimaryWeapon> getPrimaryWeaponShots() {
		return primaryWeaponShots;
	}

	/**
	 * @return the secondaryWeaponShots
	 */
	public ArrayList<SecondaryWeapon> getSecondaryWeaponShots() {
		return secondaryWeaponShots;
	}

	/**
	 * @param rectangleWings the rectangleWings to set
	 */
	public void setRectangleWings(int x, int y, int width, int height) {
		this.rectangleWings.setRect(x, y, width, height);
		this.rectangleWings.setBounds(rectangleWings);
	}

	/**
	 * @param rectangleBodyTop the rectangleBodyTop to set
	 */
	public void setRectangleBodyTop(int x, int y, int width, int height) {
		this.rectangleBodyTop.setRect(x, y, width, height);
		this.rectangleBodyTop.setBounds(rectangleBodyTop);
	}

	/**
	 * @param rectangleBodyBottom the rectangleBodyBottom to set
	 */
	public void setRectangleBodyBottom(int x, int y, int width, int height) {
		this.rectangleBodyBottom.setRect(x, y, width, height);
		this.rectangleBodyBottom.setBounds(rectangleBodyBottom);
	}

	public Rectangle[] getAllRectangles() {
		Rectangle[] allRectangles = new Rectangle[3];

		allRectangles[0] = rectangleWings;
		allRectangles[1] = rectangleBodyTop;
		allRectangles[2] = rectangleBodyBottom;

		return allRectangles;
	}

	public void drawCollisionRect(Graphics graphics, ImageObserver observer) {
		graphics.drawRect(rectangleWings.x, rectangleWings.y, rectangleWings.width, rectangleWings.height);
		graphics.drawRect(rectangleBodyTop.x, rectangleBodyTop.y, rectangleBodyTop.width, rectangleBodyTop.height);
		graphics.drawRect(rectangleBodyBottom.x, rectangleBodyBottom.y, rectangleBodyBottom.width, rectangleBodyBottom.height);
	}

	public void stop() {
		x_speed = 0;
		y_speed = 0;
	}

	public abstract void moveUp();

	public abstract void moveDown();

	public abstract void moveLeft();

	public abstract void moveRight();

	public abstract void moveUpLeft();

	public abstract void moveUpRight();

	public abstract void moveDownLeft();

	public abstract void moveDownRight();

	public abstract void firePrimary();

	public abstract void fireSecondary();

	public abstract void explode();

	public abstract void update(int width, int height);

	public abstract boolean isVisible();

	public abstract boolean isColliding(Actor actor);
}
