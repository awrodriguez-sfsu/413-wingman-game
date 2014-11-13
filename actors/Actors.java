package actors;

import java.awt.Image;

import wingman.GameObject;

/**
 * @author Anthony Rodriguez
 *
 */
public abstract class Actors extends GameObject {

	protected boolean isMovingUp;
	protected boolean isMovingDown;
	protected boolean isMovingLeft;
	protected boolean isMovingRight;
	protected boolean canFire;

	// protected int healthMax;
	// protected int healthCurrent;

	/**
	 * @param imageArray
	 * @param x_pos
	 * @param y_pos
	 */
	public Actors(Image[] imageArray, int x_pos, int y_pos) {
		super(imageArray, x_pos, y_pos);
	}

	public boolean isMovingUp() {
		return isMovingUp;
	}

	public boolean isMovingDown() {
		return isMovingDown;
	}

	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	public boolean isMovingRight() {
		return isMovingRight;
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

	@Override
	public abstract void update(int width, int height);

	public abstract void moveUp();

	public abstract void moveDown();

	public abstract void moveLeft();

	public abstract void moveRight();

	public abstract void moveDiagUpLeft();

	public abstract void moveDiagUpRight();

	public abstract void moveDiagDownLeft();

	public abstract void moveDiagDownRight();

	public abstract void stop();

	public abstract void firePrimary();

	// public abstract void fireSecondary();

	public abstract void explode();

}
