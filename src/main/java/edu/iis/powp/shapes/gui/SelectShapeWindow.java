package edu.iis.powp.shapes.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.iis.powp.app.gui.PlotterGUI;
import edu.iis.powp.command.DrawShapeCommand;
import edu.iis.powp.shapes.Circle;
import edu.iis.powp.shapes.CurrentShapeChosen;
import edu.iis.powp.shapes.Point;
import edu.iis.powp.shapes.Rectangle;
import edu.iis.powp.shapes.Triangle;
import edu.iis.powp.window.WindowComponent;

/**
 * Okno wybierania figur do narysowania.
 *
 */
public class SelectShapeWindow extends JFrame implements WindowComponent {
	private static final long serialVersionUID = 1L;
	private JPanel cardPane; // a panel that uses CardLayout

	private Rectangle rectangle;
	private Circle circle;
	private Triangle triangle;
	private DrawShapeCommand drawShapeCommand;

	/**
	 * Ca�e GUI tworzone jest w konstruktorze. Zastosowany jest BorderLayout. Na
	 * g�rze znajduje si� JPanel zawieraj�cy comboBox i pola do podania
	 * wsp�rz�dnych X i Y punktu startowego rysowania figury. Na �rodku jest
	 * JPanel z CardLayout, gdzie ka�dy card to osobny layout dla figury
	 * tworzony w metodach "private JPanel createSHAPENAMECard()". Na dole
	 * BorderLayout istnieje przycisk OK, kt�rego listener wybiera odpowiedni�
	 * metod� "private void parseSHAPENAMEData()" s�u��c� przetworzeniu danych
	 * wej�ciowych.
	 */
	public SelectShapeWindow() {
		this.setTitle("Select shape window");
		this.setResizable(false);

		Container pane = this.getContentPane();

		// TOP
		JPanel topPane = new JPanel();

		// Combo box
		String comboBoxItems[] = { "Rectangle", "Circle", "Triangle" };
		JComboBox<String> cb = new JComboBox<String>(comboBoxItems);
		cb.setEditable(false);
		cb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				CardLayout cl = (CardLayout) (cardPane.getLayout());
				cl.show(cardPane, (String) evt.getItem());
			}
		});
		topPane.add(cb);

		// Starting point
		topPane.add(new JLabel("Starting point (x, y):"));
		JTextField spX = new JTextField("0", 5);
		JTextField spY = new JTextField("0", 5);
		topPane.add(spX);
		topPane.add(spY);

		// MIDDLE
		cardPane = new JPanel(new CardLayout());

		JPanel rectangleCard = createRectangleCard();
		JPanel circleCard = createCircleCard();
		JPanel triangleCard = createTriangleCard();

		cardPane.add(rectangleCard, "Rectangle");
		cardPane.add(circleCard, "Circle");
		cardPane.add(triangleCard, "Triangle");

		// BOTTOM
		JButton okButton = new JButton("Apply");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int startingPointX, startingPointY;

				try {
					startingPointX = Integer.parseInt(spX.getText());
					startingPointY = Integer.parseInt(spY.getText());
					Point startingPoint = new Point(startingPointX, startingPointY);

					switch ((String) cb.getSelectedItem()) {
					case "Rectangle":
						parseRectangleData(rectangleCard, startingPoint);
						break;
					case "Circle":
						parseCircleData(circleCard, startingPoint);
						break;
					case "Triangle":
						parseTriangleData(triangleCard, startingPoint);
						break;
					}
				} catch (NumberFormatException ex) {
					PlotterGUI.showCaughtExceptionInfo("Podaj prawid�owe warto�ci numeryczne!");
					return;
				}
				CurrentShapeChosen.setDrawShapeCommand(drawShapeCommand);
			}
		});

		pane.add(topPane, BorderLayout.PAGE_START);
		pane.add(cardPane, BorderLayout.CENTER);
		pane.add(okButton, BorderLayout.PAGE_END);

		this.pack();
	}

	/**
	 * Przetwarza dane wej�ciowe przekazuj�c je do obiektu Singleton dla
	 * kszta�tu Rectangle i tworzy obiekt DrawShapeCommand dla tego kszta�tu.
	 * 
	 * @param crossCard
	 *            Panel z inputami dla kszta�tu Rectangle.
	 * @param startingPoint
	 *            Punkt startowy rysowania figury.
	 * @throws NumberFormatException
	 */
	private void parseRectangleData(JPanel rectangleCard, Point startingPoint) throws NumberFormatException {
		JTextField recWidth = (JTextField) rectangleCard.getComponent(1);
		JTextField recHeight = (JTextField) rectangleCard.getComponent(3);

		int width = Integer.parseInt(recWidth.getText());
		int height = Integer.parseInt(recHeight.getText());

		rectangle = Rectangle.getInstance();
		rectangle.clearPoints();
		rectangle.setStartingPoint(startingPoint);
		rectangle.setDimensions(width, height);
		drawShapeCommand = new DrawShapeCommand(rectangle);
	}

	/**
	 * Przetwarza dane wej�ciowe przekazuj�c je do obiektu Singleton dla
	 * kszta�tu Circle i tworzy obiekt DrawShapeCommand dla tego kszta�tu.
	 * 
	 * @param crossCard
	 *            Panel z inputami dla kszta�tu Circle.
	 * @param startingPoint
	 *            Punkt startowy rysowania figury.
	 * @throws NumberFormatException
	 */

	private void parseCircleData(JPanel circleCard, Point startingPoint) throws NumberFormatException {
		JTextField circleRadius = (JTextField) circleCard.getComponent(1);

		int rad = Integer.parseInt(circleRadius.getText());

		circle = Circle.getInstance();
		circle.clearPoints();
		Point tempStPoint = new Point(startingPoint);
		tempStPoint.moveY(-rad);
		circle.setStartingPoint(tempStPoint);
		circle.setDimensions(rad);
		drawShapeCommand = new DrawShapeCommand(circle);
	}

	/**
	 * Przetwarza dane wej�ciowe przekazuj�c je do obiektu Singleton dla
	 * kszta�tu Triangle i tworzy obiekt DrawShapeCommand dla tego kszta�tu.
	 * 
	 * @param crossCard
	 *            Panel z inputami dla kszta�tu Triangle.
	 * @param startingPoint
	 *            Punkt startowy rysowania figury.
	 * @throws NumberFormatException
	 */
	private void parseTriangleData(JPanel triangleCard, Point startingPoint) throws NumberFormatException {
		JTextField triSecondX = (JTextField) ((Container) triangleCard.getComponent(0)).getComponent(1);
		JTextField triSecondY = (JTextField) ((Container) triangleCard.getComponent(0)).getComponent(2);
		JTextField triThirdX = (JTextField) ((Container) triangleCard.getComponent(1)).getComponent(1);
		JTextField triThirdY = (JTextField) ((Container) triangleCard.getComponent(1)).getComponent(2);

		int secondPointX = Integer.parseInt(triSecondX.getText());
		int secondPointY = Integer.parseInt(triSecondY.getText());
		int thirdPointX = Integer.parseInt(triThirdX.getText());
		int thirdPointY = Integer.parseInt(triThirdY.getText());

		Point secondPoint = new Point(secondPointX, secondPointY);
		Point thirdPoint = new Point(thirdPointX, thirdPointY);

		triangle = Triangle.getInstance();
		triangle.clearPoints();
		triangle.setStartingPoint(startingPoint);
		triangle.setDimensions(secondPoint, thirdPoint);
		drawShapeCommand = new DrawShapeCommand(triangle);
	}

	/**
	 * Tworzy JPanel dla kszta�tu Rectangle.
	 * 
	 * @return
	 */
	private JPanel createRectangleCard() {
		JPanel rectangle = new JPanel();

		JTextField recWidth = new JTextField("100", 5);
		JTextField recHeight = new JTextField("50", 5);

		rectangle.add(new JLabel("Width: "));
		rectangle.add(recWidth);
		rectangle.add(new JLabel("Height: "));
		rectangle.add(recHeight);

		return rectangle;
	}

	/**
	 * Tworzy JPanel dla kszta�tu Circle.
	 * 
	 * @return
	 */
	private JPanel createCircleCard() {
		JPanel circle = new JPanel();

		JTextField circleRadius = new JTextField("100", 5);

		circle.add(new JLabel("Radius:"));
		circle.add(circleRadius);

		return circle;
	}

	/**
	 * Tworzy JPanel dla kszta�tu Triangle.
	 * 
	 * @return
	 */
	private JPanel createTriangleCard() {
		JPanel triangle = new JPanel();
		triangle.setLayout(new BoxLayout(triangle, BoxLayout.Y_AXIS));

		JPanel secondPointPane = new JPanel();
		JPanel thirdPointPane = new JPanel();

		JTextField triSecondX = new JTextField("75", 5);
		JTextField triSecondY = new JTextField("-100", 5);
		JTextField triThirdX = new JTextField("200", 5);
		JTextField triThirdY = new JTextField("0", 5);

		secondPointPane.add(new JLabel("Second point:"));
		secondPointPane.add(triSecondX);
		secondPointPane.add(triSecondY);

		thirdPointPane.add(new JLabel("Third point:"));
		thirdPointPane.add(triThirdX);
		thirdPointPane.add(triThirdY);

		triangle.add(secondPointPane);
		triangle.add(thirdPointPane);

		return triangle;
	}

	/**
	 * Przy wybraniu okna z menu pojawia si� to okno je�li jeszcze nie by�o lub
	 * znika je�li ju� by�o.
	 */
	@Override
	public void HideIfVisibleAndShowIfHidden() {
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}
}