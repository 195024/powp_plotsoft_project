package edu.iis.powp.shapes;

import java.util.ArrayList;

public class Cross extends Shape {
	private static Cross instance;

	public void setDimensions(int width, int thickness) {
		int arm = (width - thickness) / 2;

		Point currentPoint = new Point(points.get(0));

		currentPoint.moveX(thickness);
		points.add(new Point(currentPoint));
		currentPoint.moveY(arm);
		points.add(new Point(currentPoint));
		currentPoint.moveX(arm);
		points.add(new Point(currentPoint));
		currentPoint.moveY(thickness);
		points.add(new Point(currentPoint));
		currentPoint.moveX(-arm);
		points.add(new Point(currentPoint));
		currentPoint.moveY(arm);
		points.add(new Point(currentPoint));
		currentPoint.moveX(-thickness);
		points.add(new Point(currentPoint));
		currentPoint.moveY(-arm);
		points.add(new Point(currentPoint));
		currentPoint.moveX(-arm);
		points.add(new Point(currentPoint));
		currentPoint.moveY(-thickness);
		points.add(new Point(currentPoint));
		currentPoint.moveX(arm);
		points.add(new Point(currentPoint));
	}
	
	public void clearPoints() {
		points = new ArrayList<Point>();
	}
	
	public static Cross getInstance(){
        if(instance == null)
        	instance = new Cross();
        return instance;
    }

	@Override
	public String getName() {
		return "Cross";
	}
}