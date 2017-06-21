package edu.iis.powp.shapes;

import java.util.ArrayList;

/** 
 * Klasa abstrakcyjna Shape z której dziedzicza wszystkie ksztalty jakie tworzymy, jej istot¹ jest ArrayList<Point> points, dziêki temu niezale¿nie od figury mo¿emy na nia patrzec jako na zbiór kolejno
 * polaczonych punktów, które mozemy przeksztalcac ta sam¹ funkcja, niezaleanie od ostatecznego ksztaltu
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
