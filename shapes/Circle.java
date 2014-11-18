/**
 * 
 */
package shapes;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * @author arod
 *
 */
public class Circle extends Ellipse2D {

	private int x, y, radius;

	public Circle(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	@Override
	public Rectangle2D getBounds2D() {
		return super.getBounds();
	}

	@Override
	public double getHeight() {
		return radius;
	}

	@Override
	public double getWidth() {
		return radius;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void setFrame(double x, double y, double width, double height) {}

	public void update(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
