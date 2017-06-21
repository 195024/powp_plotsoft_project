package edu.iis.powp.shapes;

import edu.iis.powp.command.DrawShapeCommand;

/**
 * Klasa przechowuje aktualnie wybran� figur�, aby by�o wiadomo do czego aplikowa� transformacje.
 *
 */
public class CurrentShapeChosen {
	private static DrawShapeCommand drawShapeCommand;

	public static DrawShapeCommand getDrawShapeCommand() {
		return drawShapeCommand;
	}

	public static void setDrawShapeCommand(DrawShapeCommand drawShapeCommand) {
		CurrentShapeChosen.drawShapeCommand = drawShapeCommand;
	}
}
