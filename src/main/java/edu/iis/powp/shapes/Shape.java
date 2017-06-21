package edu.iis.powp.shapes;

import java.util.ArrayList;

/** 
 * Klasa abstrakcyjna Shape z kt�rej dziedzicza wszystkie ksztalty jakie tworzymy, jej istot� jest ArrayList<Point> points, dzi�ki temu niezale�nie od figury mo�emy na nia patrzec jako na zbi�r kolejno
 * polaczonych punkt�w, kt�re mozemy przeksztalcac ta sam� funkcja, niezaleanie od ostatecznego ksztaltu
 *  
 */

public abstract class Shape {
	/**
	 * Zbior polaczonych punktow
	 */
	ArrayList<Point> points = new ArrayList<Point>();	
	/**
	 * Getter do punktow ksztaltu
	 * @return points
	 */
	public ArrayList<Point> getPoints() {
		return points;
	}
	/**
	 * Setter do punktow (przydatny po transformacjach)
	 * @param points
	 */
	@SuppressWarnings("unchecked")
	public void setPoints(ArrayList<Point> points) {		
		this.points = (ArrayList<Point>) points.clone();
	}
	
	public void setStartingPoint(Point startingPoint) {
		points.add(startingPoint);
	}

	public String getName() {
		return "Shape";
	}
	
}
