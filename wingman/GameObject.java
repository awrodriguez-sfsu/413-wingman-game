/**
 * 
 */
package wingman;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import shapes.CollisionCircle;
import shapes.CollisionRectangle;
import wingman.Resources.ResourceSpec;
import animations.Animation;
import enums.AnimationType;
import enums.GameObjectType;

/**
 * @author Anthony Rodriguez
 *
 */
public abstract class GameObject {

	protected double top_edge, bottom_edge, left_edge, right_edge, front;

	protected final int GAME_BORDER = 5;

	protected int x_pos;
	protected int y_pos;

	private boolean isSolid;

	private GameObjectType type;

	private ResourceSpec specification;

	private ArrayList<CollisionCircle> collisionCircles;
	private ArrayList<CollisionRectangle> collisionRectangles;

	private Animation animation = null;

	public GameObject(AnimationType image, GameObjectType type, int x_pos, int y_pos) {
		Resources.getInstance();

		this.animation = GameBase.animations.get(image);
		this.isSolid = image.isSolid();
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.type = type;

		if (isSolid) {
			collisionCircles = new ArrayList<CollisionCircle>();
			collisionRectangles = new ArrayList<CollisionRectangle>();

			specification = Resources.getSpec(image.getName());
			for (int i = 0; i < specification.shapes.size(); i++) {
				if (specification.shapes.get(i).type.equals("rectangle")) {
					collisionRectangles.add(new CollisionRectangle(specification.shapes.get(i).x, specification.shapes.get(i).y, specification.shapes.get(i).width, specification.shapes.get(i).height));
				} else {
					collisionCircles.add(new CollisionCircle(specification.shapes.get(i).x, specification.shapes.get(i).y, specification.shapes.get(i).width, specification.shapes.get(i).height));
				}
			}

			this.top_edge = specification.top;
			this.bottom_edge = specification.bottom;
			this.left_edge = specification.left;
			this.right_edge = specification.right;
			this.front = specification.front;
		}
	}

	public void draw(Graphics graphics, ImageObserver observer) {
		graphics.drawImage(animation.getImage(), x_pos, y_pos, observer);
		if (isSolid) {
			for (int i = 0; i < collisionRectangles.size(); i++) {
				CollisionRectangle rectangle = (CollisionRectangle) collisionRectangles.get(i);
				graphics.drawRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
			}
			for (int i = 0; i < collisionCircles.size(); i++) {
				CollisionCircle circle = (CollisionCircle) collisionCircles.get(i);
				graphics.drawOval((int) circle.getX(), (int) circle.getY(), (int) circle.getWidth(), (int) circle.getHeight());
			}
		}

		animation.update(16, this);
	}

	/**
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * @param animation the animation to set
	 */
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	/**
	 * @return the isSolid
	 */
	public boolean isSolid() {
		return isSolid;
	}

	/**
	 * @param isSolid the isSolid to set
	 */
	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	/**
	 * @return the type
	 */
	public GameObjectType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(GameObjectType type) {
		this.type = type;
	}

	/**
	 * @return the collisionRectangles
	 */
	public ArrayList<CollisionRectangle> getCollisionRectangles() {
		return collisionRectangles;
	}

	/**
	 * @return the collisionCircles
	 */
	public ArrayList<CollisionCircle> getCollisionCircles() {
		return collisionCircles;
	}

	public void clearCollisions() {
		this.collisionCircles.clear();
		this.collisionRectangles.clear();
	}

	public abstract void update(int width, int height);

	public abstract boolean isVisible();
}
