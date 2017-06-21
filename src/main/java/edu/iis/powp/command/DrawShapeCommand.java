package edu.iis.powp.command;

import java.util.ArrayList;
import java.util.List;

import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.shapes.Shape;

/**
 * Klasa s�u��ca stworzeniu listy komend IPlotterCommand do narysowania przetrzymywanej w klasie figury.
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
	 * Tworzy list� IPlotterCommand, kt�re po wywo�aniu wyrysuj� kszta�t shape.
	 * Punkty tworzone na zasadzie "po��cz kropki".
	 * Na koniec metoda ustawia wytworzon� list� komend jako aktualn� komend� programu.
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