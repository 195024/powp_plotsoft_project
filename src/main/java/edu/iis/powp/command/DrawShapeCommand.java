package edu.iis.powp.command;

import java.util.ArrayList;
import java.util.List;

import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.shapes.Shape;

/**
 * Klasa s³u¿¹ca stworzeniu listy komend IPlotterCommand do narysowania przetrzymywanej w klasie figury.
 *
 */
public class DrawShapeCommand {
	private Shape shape;
	
	public DrawShapeCommand(Shape shape) {
		super();
		this.shape = shape;
		drawShape();
	}
	
	/**
	 * Tworzy listê IPlotterCommand, które po wywo³aniu wyrysuj¹ kszta³t shape.
	 * Punkty tworzone na zasadzie "po³¹cz kropki".
	 * Na koniec metoda ustawia wytworzon¹ listê komend jako aktualn¹ komendê programu.
	 */
	public void drawShape() {
		List<IPlotterCommand> commands = new ArrayList<IPlotterCommand>();
		commands.add(new SetPositionCommand(shape.getPoints().get(0).getX(), shape.getPoints().get(0).getY()));
		for (int i = 1; i < shape.getPoints().size(); i++) {
			commands.add(new DrawToCommand(shape.getPoints().get(i).getX(), shape.getPoints().get(i).getY()));
			commands.add(new SetPositionCommand(shape.getPoints().get(i).getX(), shape.getPoints().get(i).getY()));
		}
		commands.add(new DrawToCommand(shape.getPoints().get(0).getX(), shape.getPoints().get(0).getY()));

		PlotterCommandManager manager = FeaturesManager.getPlotterCommandManager();
		manager.setCurrentCommand(commands, shape.getName());
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}	
}