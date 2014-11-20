/**
 * 
 */
package shapes;

import java.awt.geom.Rectangle2D;

/**
 * @author arod
 *
 */
public class CollisionRectangle extends Rectangle2D {

	private long x_pos, y_pos, width, height;
	private long x, y;
	private String type;

	public CollisionRectangle(long x, long y, long width, long height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = "rectangle";
	}

	public String getType() {
		return type;
	}

	@Override
	public Rectangle2D createIntersection(Rectangle2D arg0) {
		return null;
	}

	@Override
	public Rectangle2D createUnion(Rectangle2D arg0) {
		return null;
	}

	@Override
	public int outcode(double arg0, double arg1) {
		return 0;
	}

	@Override
	public Rectangle2D getBounds2D() {
		return super.getBounds2D();
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getX() {
		return x + x_pos;
	}

	@Override
	public double getY() {
		return y + y_pos;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void setRect(double x, double y, double width, double height) {}

	public void update(long x_pos, long y_pos) {
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}
}
