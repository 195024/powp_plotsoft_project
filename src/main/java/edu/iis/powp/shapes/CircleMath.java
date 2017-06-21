package edu.iis.powp.shapes;

/**
 * Klasa pomocnicza dla rysowania figury Circle
 *
 */
public class CircleMath {

	public static Point calculateCirclePoint(Point center, int radius, double theta) {
		return new Point((int) Math.floor(radius * Math.cos(theta) + center.getX()),
				(int) Math.floor(radius * Math.sin(theta) + center.getY()));
	}

	public static double degToRad(double deg) {
		return deg * Math.PI / 180;
	}
}