package edu.iis.powp.shapes;

import java.util.ArrayList;

public class Triangle extends Shape {
	private static Triangle instance;

	private Triangle(){}

	public void setDimensions(Point secondPoint, Point thirdPoint) {
		points.add(secondPoint);
		points.add(thirdPoint);
	}
	
	public void clearPoints() {
		points = new ArrayList<Point>();
	}
	
	public static Triangle getInstance(){
        if(instance == null)
        	instance = new Triangle();
        return instance;
    }

	@Override
	public String getName() {
		return "Triangle";
	}

	@Override
	public ArrayList<Point> getPoints() {
		return points;
	}

}