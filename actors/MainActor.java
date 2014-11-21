/**
 * 
 */
package actors;

import java.applet.AudioClip;
import java.awt.Image;
import java.util.ArrayList;

import projectiles.Projectile;
import shapes.CollisionCircle;
import shapes.CollisionRectangle;
import wingman.Resources;
import animations.Animation;
import enums.AnimationType;
import enums.GameObjectType;

/**
 * @author Anthony Rodriguez
 *
 */
public class MainActor extends Actor {

	private AnimationType primaryWeapon;
	private AnimationType secondaryWeapon;

	private final double MOVEMENT_SPEED = 3.5;

	private int health;

	private Image healthBar;

	private int lives;

	private long score;

	/**
	 * @param image
	 * @param type
	 * @param x_pos
	 * @param y_pos
	 */
	public MainActor(AnimationType image, AnimationType primaryWeapon, AnimationType secondaryWeapon, int x_pos, int y_pos) {
		super(image, GameObjectType.PLAYER, x_pos, y_pos);
		this.primaryWeapon = primaryWeapon;
		this.secondaryWeapon = secondaryWeapon;
		this.lives = 2;
		this.health = 5;
		this.healthBar = Resources.getInstance().health1;
		this.score = 0;
	}

	/*
	 * (non-Javadoc)
	 * @see actors.Actor#update(int, int)
	 */
	@Override
	public void update(int width, int height) {

		if (isMovingLeft() || isMovingRight()) {
			x_pos += x_speed;
		}

		if (isMovingUp() || isMovingDown()) {
			y_pos += y_speed;
		}

		if (x_pos <= -left_edge + GAME_BORDER) {
			x_pos = (int) ( -left_edge + GAME_BORDER );
		}

		if (x_pos >= width - right_edge - GAME_BORDER) {
			x_pos = (int) ( width - right_edge - GAME_BORDER );
		}

		if (y_pos <= top_edge + GAME_BORDER) {
			y_pos = (int) ( top_edge + GAME_BORDER );
		}

		if (y_pos >= height - bottom_edge - GAME_BORDER - HUD) {
			y_pos = (int) ( height - bottom_edge - GAME_BORDER - HUD );
		}

		for (int i = 0; i < getCollisionCircles().size(); i++) {
			getCollisionCircles().get(i).update(x_pos, y_pos);
		}

		for (int i = 0; i < getCollisionRectangles().size(); i++) {
			getCollisionRectangles().get(i).update(x_pos, y_pos);
		}
	}

	@Override
	public void moveUp() {
		y_speed = -MOVEMENT_SPEED;

		setMovingUp(true);
		setMovingDown(false);
		setMovingLeft(false);
		setMovingRight(false);
	}

	@Override
	public void moveDown() {
		y_speed = MOVEMENT_SPEED + 1;

		setMovingUp(false);
		setMovingDown(true);
		setMovingLeft(false);
		setMovingRight(false);
	}

	@Override
	public void moveLeft() {
		x_speed = -MOVEMENT_SPEED;

		setMovingUp(false);
		setMovingDown(false);
		setMovingLeft(true);
		setMovingRight(false);
	}

	@Override
	public void moveRight() {
		x_speed = MOVEMENT_SPEED;

		setMovingUp(false);
		setMovingDown(false);
		setMovingLeft(false);
		setMovingRight(true);
	}

	@Override
	public void moveUpLeft() {
		x_speed = -MOVEMENT_SPEED;
		y_speed = -MOVEMENT_SPEED;

		setMovingUp(true);
		setMovingDown(false);
		setMovingLeft(true);
		setMovingRight(false);
	}

	@Override
	public void moveUpRight() {
		x_speed = MOVEMENT_SPEED;
		y_speed = -MOVEMENT_SPEED;

		setMovingUp(true);
		setMovingDown(false);
		setMovingLeft(false);
		setMovingRight(true);
	}

	@Override
	public void moveDownLeft() {
		x_speed = -MOVEMENT_SPEED;
		y_speed = MOVEMENT_SPEED + 1;

		setMovingUp(false);
		setMovingDown(true);
		setMovingLeft(true);
		setMovingRight(false);
	}

	@Override
	public void moveDownRight() {
		x_speed = MOVEMENT_SPEED;
		y_speed = MOVEMENT_SPEED + 1;

		setMovingUp(false);
		setMovingDown(true);
		setMovingLeft(false);
		setMovingRight(true);
	}

	@Override
	public void firePrimary() {
		if (canFirePrimary()) {
			Projectile pShot = new Projectile(primaryWeapon, AnimationType.PLAYER1, x_pos, y_pos);
			shots.add(pShot);
			setCanFirePrimary(false);
		}
	}

	@Override
	public void fireSecondary() {
		if (canFireSecondary()) {
			Projectile sShot = new Projectile(secondaryWeapon, AnimationType.PLAYER1, x_pos, y_pos);
			shots.add(sShot);
			setCanFireSecondary(false);
		}
	}

	@Override
	public void explode() {
		setExploding(true);

		AudioClip clip = Resources.getInstance().large_explosion_sound;
		clip.play();

		clearCollisions();

		Animation animation = new Animation(true);
		animation.addFrame(Resources.getInstance().explosion2_1, 250);
		animation.addFrame(Resources.getInstance().explosion2_2, 250);
		animation.addFrame(Resources.getInstance().explosion2_3, 250);
		animation.addFrame(Resources.getInstance().explosion2_4, 250);
		animation.addFrame(Resources.getInstance().explosion2_5, 250);
		animation.addFrame(Resources.getInstance().explosion2_6, 250);
		animation.addFrame(Resources.getInstance().explosion2_7, 250);

		setAnimation(animation);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public boolean inPlay() {
		return true;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void increaseHealth(int health) {
		this.health += health;

		if (getAnimationType() == AnimationType.PLAYER1) {
			switch (this.health) {
				case 5:
					healthBar = Resources.getInstance().health1;
					break;
				case 4:
					healthBar = Resources.getInstance().health1_2;
					break;
				case 3:
					healthBar = Resources.getInstance().health1_3;
					break;
				case 2:
					healthBar = Resources.getInstance().health1_4;
					break;
				case 1:
					healthBar = Resources.getInstance().health1_5;
					break;
				case 0:
					healthBar = Resources.getInstance().health6;
					break;
			}
		} else if (getAnimationType() == AnimationType.PLAYER2) {
			switch (health) {
				case 5:
					healthBar = Resources.getInstance().health1;
					break;
				case 4:
					healthBar = Resources.getInstance().health2_2;
					break;
				case 3:
					healthBar = Resources.getInstance().health2_3;
					break;
				case 2:
					healthBar = Resources.getInstance().health2_4;
					break;
				case 1:
					healthBar = Resources.getInstance().health2_5;
					break;
				case 0:
					healthBar = Resources.getInstance().health6;
					break;
			}
		}
	}

	/**
	 * @return the healthBar
	 */
	public Image getHealthBar() {
		return healthBar;
	}

	/**
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * @param lives the lives to increase
	 */
	public void increaseLives(int lives) {
		this.lives += lives;
	}

	/**
	 * @return the score
	 */
	public String getScore() {
		return String.valueOf(score);
	}

	/**
	 * @param score the score to increase
	 */
	public void increaseScore(long score) {
		this.score += score;
	}

	@Override
	public boolean isColliding(Actor actor) {
		ArrayList<CollisionCircle> playerCircles = getCollisionCircles();
		ArrayList<CollisionRectangle> playerRectangles = getCollisionRectangles();
		ArrayList<CollisionCircle> actorCircles = actor.getCollisionCircles();
		ArrayList<CollisionRectangle> actorRectangles = actor.getCollisionRectangles();

		for (int i = 0; i < playerCircles.size(); i++) {
			for (int j = 0; j < actorCircles.size(); j++) {
				if (playerCircles.get(i).intersects(actorCircles.get(j).getBounds2D())) {
					System.out.println("Player Cicle with Actor Circle");
					return true;
				}
			}

			for (int j = 0; j < actorRectangles.size(); j++) {
				if (playerCircles.get(i).intersects(actorRectangles.get(j))) {
					System.out.println("Player Cicle with Actor Rectangle");
					return true;
				}
			}
		}

		for (int i = 0; i < playerRectangles.size(); i++) {
			for (int j = 0; j < actorCircles.size(); j++) {
				if (playerRectangles.get(i).intersects(actorCircles.get(j).getBounds2D())) {
					return true;
				}
			}

			for (int j = 0; j < actorRectangles.size(); j++) {
				if (playerRectangles.get(i).intersects(actorRectangles.get(j))) {
					return true;
				}
			}
		}

		return false;
	}
}
