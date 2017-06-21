package edu.iis.powp.shapes;

import java.util.ArrayList;

public class Rectangle extends Shape {
	private static Rectangle instance;
		
	public void setDimensions(int dimensionX, int dimensionY) {
		Point startingPoint = points.get(0);
		points.add(new Point(startingPoint.getX() + dimensionX, startingPoint.getY()));
		points.add(new Point(startingPoint.getX() + dimensionX, startingPoint.getY() + dimensionY));
		points.add(new Point(startingPoint.getX(), startingPoint.getY() + dimensionY));
	}
	
	public void clearPoints() {
		points = new ArrayList<Point>();
	}
	
	public static Rectangle getInstance(){
        if(instance == null)
        	instance = new Rectangle();
        return instance;
    }

	@Override
	public String getName() {
		return "Rectangle";
	}
}