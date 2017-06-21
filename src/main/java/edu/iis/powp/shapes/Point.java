package edu.iis.powp.shapes;

public class Point {
	private int x, y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point(Point point) {
		this.x = point.getX();
		this.y = point.getY();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void moveX(int delta) {
		this.x += delta;
	}

	public void moveY(int delta) {
		this.y += delta;
	}

	public void moveBy(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

}
