package edu.iis.powp.transformations;

import java.util.ArrayList;

import edu.iis.powp.shapes.Point;
import edu.iis.powp.shapes.Shape;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;

/**
 * Klasa Transformer, ktora przyjmuje do statycznych metod Shape, przeksztalca jego punkty i zwraca przeksztalconego Shape
 * @author socha
 *
 */

public class Transformer {
	/**
	 * Funkcja przesuwajaca dany ksztalt
	 * @param shape	ksztalt do przesuniecia
	 * @param x ilosc jednostek do przesuniecia w poziomie
	 * @param y ilosc jednostek do przesuniecia w pionie
	 * @return przesuniety ksztalt
	 */
	public static Shape translate(Shape shape, int x, int y) {
		ArrayList<Point> points = shape.getPoints();		
		for(Point point : points) {
			point.setX(point.getX() + x);
			point.setY(point.getY() + y);
		}
		shape.setPoints(points);
		return shape;
	}	
	/**
	 * Funkcja obracajaca dany ksztalt
	 * @param shape
	 * @param angle kat o jaki chcemy obrocic ksztalt
	 * @return
	 */
	public static Shape rotate(Shape shape, double angle) {
		Polygon polygon = translateToPolygon(shape);
		polygon.getTransforms().add(new Rotate(angle));
		return translateToShape(polygon, shape);
	}
	/**
	 * Funkcja skalujaca ksztalt
	 * @param shape
	 * @param x ile pierwotnej szerokosci chcemy uzyskac
	 * @param y ile pierwotnej dlugosci chcemy uzyskac
	 * @return
	 */
	public static Shape scale(Shape shape, double x, double y) {		
		Polygon polygon = translateToPolygon(shape);
		polygon.getTransforms().add(new Scale(x, y));
		return translateToShape(polygon, shape);
	}
	/**
	 * Powinowactwo osiowe
	 * @param shape
	 * @param x
	 * @param y
	 * @return
	 */
	public static Shape shear(Shape shape, double x, double y) {		
		Polygon polygon = translateToPolygon(shape);
		polygon.getTransforms().add(new Shear(x, y));
		return translateToShape(polygon, shape);
	}
	/**
	 * Przeksztalca Shape do klasy Polygon z JavaFx, ktory mozemy latwo transformowac
	 * @param shape
	 * @return
	 */
	public static Polygon translateToPolygon(Shape shape) {
		ArrayList<Point> points = shape.getPoints();
		ArrayList<Double> pointsForPolygon = new ArrayList<Double>();
		Polygon polygon = new Polygon();		
		for(Point point : points) {
			pointsForPolygon.add((double)point.getX());
			pointsForPolygon.add((double)point.getY());
		}
		polygon.getPoints().addAll(pointsForPolygon);
		return polygon;
	}
	/**
	 * Przeksztalca Polygon do klasy Shape
	 * @param polygon
	 * @param shape Shape
	 * @return
	 */
	public static Shape translateToShape(Polygon polygon, Shape shape) {
		ArrayList<Point> points = shape.getPoints();
		ArrayList<Point> pointsAfterRotation = new ArrayList<Point>();
		for(Point point : points) {
			Point2D point2D = polygon.localToParent(point.getX(), point.getY());
			pointsAfterRotation.add(new Point((int)point2D.getX(),(int)point2D.getY()));
		}		
		shape.setPoints(pointsAfterRotation);
		return shape;
	}
}
