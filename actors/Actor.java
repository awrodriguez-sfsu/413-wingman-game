/**
 * 
 */
package actors;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import projectiles.PrimaryWeapon;
import projectiles.SecondaryWeapon;
import wingman.GameObject;

/**
 * @author arod
 *
 */
public abstract class Actor extends GameObject {

	private boolean isMovingUp;
	private boolean isMovingDown;
	private boolean isMovingLeft;
	private boolean isMovingRight;
	private boolean canFire = true;

	protected int maxHealth;
	protected int currentHealth;

	protected float x_speed = 0;
	protected float y_speed = 0;

	protected Image primaryWeapon;
	protected Image secondaryWeapon;

	public static Rectangle rectangleWings = new Rectangle(0, 0, 0, 0);
	public static Rectangle rectangleBodyTop = new Rectangle(0, 0, 0, 0);
	public static Rectangle rectangleBodyBottom = new Rectangle(0, 0, 0, 0);

	protected ArrayList<PrimaryWeapon> primaryWeaponShots = new ArrayList<PrimaryWeapon>();
	protected ArrayList<SecondaryWeapon> secondaryWeaponShots = new ArrayList<SecondaryWeapon>();

	public Actor(String image, int x_pos, int y_pos, ImageObserver observer) {
		super(image, x_pos, y_pos, observer);
	}

	/**
	 * @return isMovingUp
	 */
	public boolean isMovingUp() {
		return isMovingUp;
	}

	/**
	 * @param isMovingUp the isMovingUp to set
	 */
	public void setMovingUp(boolean isMovingUp) {
		this.isMovingUp = isMovingUp;
	}

	/**
	 * @return isMovingDown
	 */
	public boolean isMovingDown() {
		return isMovingDown;
	}

	/**
	 * @param isMovingDown the isMovingDown to set
	 */
	public void setMovingDown(boolean isMovingDown) {
		this.isMovingDown = isMovingDown;
	}

	/**
	 * @return isMovingLeft
	 */
	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	/**
	 * @param isMovingLeft the isMovingLeft to set
	 */
	public void setMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}

	/**
	 * @return isMovingRight
	 */
	public boolean isMovingRight() {
		return isMovingRight;
	}

	/**
	 * @param isMovingRight the isMovingRight to set
	 */
	public void setMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}

	/**
	 * @return the maxHealth
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * @param maxHealth the maxHealth to set
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
	 * @return the currentHealth
	 */
	public int getCurrentHealth() {
		return currentHealth;
	}

	/**
	 * @param currentHealth the currentHealth to set
	 */
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	/**
	 * @return canFire
	 */
	public boolean canFire() {
		return canFire;
	}

	/**
	 * @param canFire the canFire to set
	 */
	public void setCanFire(boolean canFire) {
		this.canFire = canFire;
	}

	/**
	 * @return the mainWeaponShots
	 */
	public ArrayList<PrimaryWeapon> getMainWeaponShots() {
		return primaryWeaponShots;
	}

	/**
	 * @return the secondaryWeaponShots
	 */
	public ArrayList<SecondaryWeapon> getSecondaryWeaponShots() {
		return secondaryWeaponShots;
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

	public void drawCollisionRect(Graphics graphics, ImageObserver observer) {
		graphics.drawRect(rectangleWings.x, rectangleWings.y, rectangleWings.width, rectangleWings.height);
		graphics.drawRect(rectangleBodyTop.x, rectangleBodyTop.y, rectangleBodyTop.width, rectangleBodyTop.height);
		graphics.drawRect(rectangleBodyBottom.x, rectangleBodyBottom.y, rectangleBodyBottom.width, rectangleBodyBottom.height);
	}
}
