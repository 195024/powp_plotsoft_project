package edu.iis.powp.shapes;

import edu.iis.powp.command.DrawShapeCommand;

/**
 * Klasa przechowuje aktualnie wybran¹ figurê, aby by³o wiadomo do czego aplikowaæ transformacje.
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
