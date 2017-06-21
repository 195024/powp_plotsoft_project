package edu.iis.powp.shapes;

import java.util.ArrayList;

import static edu.iis.powp.shapes.CircleMath.calculateCirclePoint;
import static edu.iis.powp.shapes.CircleMath.degToRad;

public class Star extends Shape {
	private static Star instance;

	public void setDimensions(int radiusIn, int radiusOut) {
		Point centerPoint = new Point(points.get(0));
		centerPoint.moveY(radiusOut);

		double deg = -90;
		for (int i = 0; i < 10; i++) {
			deg += 360.0 / 10;
			if (i % 2 == 0) {
				points.add(calculateCirclePoint(centerPoint, radiusIn, degToRad(deg)));
			} else {
				points.add(calculateCirclePoint(centerPoint, radiusOut, degToRad(deg)));
			}
		}
	}
	
	public void clearPoints() {
		points = new ArrayList<Point>();
	}
	
	public static Star getInstance(){
        if(instance == null)
        	instance = new Star();
        return instance;
    }

	@Override
	public String getName() {
		return "Star";
	}
}