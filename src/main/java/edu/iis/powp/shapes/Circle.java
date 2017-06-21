package edu.iis.powp.shapes;

import java.util.ArrayList;

import static edu.iis.powp.shapes.CircleMath.calculateCirclePoint;
import static edu.iis.powp.shapes.CircleMath.degToRad;

public class Circle extends Shape {
	private static Circle instance;

	public void setDimensions(int radius) {
		Point centerPoint = new Point(points.get(0));
		centerPoint.moveY(radius);

		double deg = -90;
		int n = radius * 2;
		for (int i = 0; i < n; i++) {
			deg += 360.0 / n;
			points.add(calculateCirclePoint(centerPoint, radius, degToRad(deg)));
		}
	}
	
	public void clearPoints() {
		points = new ArrayList<Point>();
	}
	
	public static Circle getInstance(){
        if(instance == null)
        	instance = new Circle();
        return instance;
    }

	@Override
	public String getName() {
		return "Circle";
	}
}