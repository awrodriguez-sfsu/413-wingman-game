/**
 * 
 */
package actors;

import java.util.ArrayList;

import projectiles.Projectile;
import wingman.GameObject;
import enums.AnimationType;
import enums.GameObjectType;

/**
 * @author Anthony Rodriguez
 *
 */
public abstract class Actor extends GameObject {

	private boolean isMovingUp;
	private boolean isMovingDown;
	private boolean isMovingLeft;
	private boolean isMovingRight;
	private boolean canFirePrimary = true;
	private boolean canFireSecondary = true;

	private boolean isAlive;
	private boolean isExploding;

	protected double x_speed;
	protected double y_speed;

	protected ArrayList<Projectile> shots = new ArrayList<Projectile>();

	public Actor(AnimationType image, GameObjectType type, int x_pos, int y_pos) {
		super(image, type, x_pos, y_pos);
		this.isAlive = true;
	}

	/**
	 * @return the isMovingUp
	 */
	public boolean isMovingUp() {
		return isMovingUp && !isExploding;
	}

	/**
	 * @return the isMovingDown
	 */
	public boolean isMovingDown() {
		return isMovingDown && !isExploding;
	}

	/**
	 * @return the isMovingLeft
	 */
	public boolean isMovingLeft() {
		return isMovingLeft && !isExploding;
	}

	/**
	 * @return the isMovingRight
	 */
	public boolean isMovingRight() {
		return isMovingRight && !isExploding;
	}

	/**
	 * @return the canFire
	 */
	public boolean canFirePrimary() {
		return canFirePrimary && !isExploding;
	}

	/**
	 * @return the canFireSecondary
	 */
	public boolean canFireSecondary() {
		return canFireSecondary && !isExploding;
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
	 * @return the isExploding
	 */
	public boolean isExploding() {
		return isExploding;
	}

	/**
	 * @param isExploding the isExploding to set
	 */
	public void setExploding(boolean isExploding) {
		this.isExploding = isExploding;
	}

	/**
	 * @return the isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * @param isAlive the isAlive to set
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * @return the primaryWeaponShots
	 */
	public ArrayList<Projectile> getShots() {
		return shots;
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
