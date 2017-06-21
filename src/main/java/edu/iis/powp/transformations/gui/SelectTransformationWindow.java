package edu.iis.powp.transformations.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.iis.powp.app.gui.PlotterGUI;
import edu.iis.powp.command.DrawShapeCommand;
import edu.iis.powp.shapes.CurrentShapeChosen;
import edu.iis.powp.shapes.Shape;
import edu.iis.powp.transformations.Transformer;
import edu.iis.powp.window.WindowComponent;

/**
 * Okno wybierania transformacji do wykonania na uprzednio wybranej figurze.
 *
 */
public class SelectTransformationWindow extends JFrame implements WindowComponent {
	private static final long serialVersionUID = 1L;
	public DrawShapeCommand drawShapeCommand;

	/**
	 * Ca³e GUI tworzone jest w konstruktorze.
	 * Zastosowany jest BorderLayout.
	 * Na górze znajduje siê JPanel zawieraj¹cy comboBox.
	 * Na œrodku s¹ pola parametrów transformacji.
	 * Na dole BorderLayout istnieje przycisk Apply, którego listener
	 * przetwarza dane wejœciowe i wysy³a je do klasy Transformer.java, która aplikuje
	 * wybran¹ transformacjê. Na koniec listenera wywo³ywana jest metoda tworz¹ca polecenie
	 * narysowania przetransformowanej figury.
	 */
	public SelectTransformationWindow() {
		this.setTitle("Select transformation window");
		this.setResizable(false);
		Container pane = this.getContentPane();
		
		//TOP
		JPanel topPane = new JPanel();

		//Combo box
		String comboBoxItems[] = { "Translate", "Rotate", "Scale", "Shear" };
		JComboBox<String> cb = new JComboBox<String>(comboBoxItems);
		cb.setEditable(false);
		topPane.add(cb);

		//MIDDLE
		JPanel midPane = new JPanel();

		JTextField x = new JTextField("100", 5);
		JTextField y = new JTextField("50", 5);
		JTextField angle = new JTextField("0", 5);
		angle.setEnabled(false);
		
		midPane.add(new JLabel("x: "));
		midPane.add(x);
		midPane.add(new JLabel("y: "));
		midPane.add(y);
		midPane.add(new JLabel("Angle: "));
		midPane.add(angle);

		//Combo box listener
		cb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				if(cb.getSelectedItem().equals("Rotate")) {
					angle.setEnabled(true);
					x.setEnabled(false);
					y.setEnabled(false);
				} else {
					angle.setEnabled(false);
					x.setEnabled(true);
					y.setEnabled(true);
				}
			}
		});
		
		//BOTTOM
		JButton okButton = new JButton("Apply");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double xVal, yVal, angleVal;
				
				try {
					xVal = Double.valueOf(x.getText());
					yVal = Double.valueOf(y.getText());
					angleVal = Double.valueOf(angle.getText());
				} catch(NumberFormatException ex) {
					PlotterGUI.showCaughtExceptionInfo("Podaj prawid³owe wartoœci numeryczne!");
					return;
				}
				
				Shape currentShape;
				try {
					currentShape = CurrentShapeChosen.getDrawShapeCommand().getShape();
				} catch(Exception ex2) {
					PlotterGUI.showCaughtExceptionInfo("Nie ma wybranej figury!");
					return;
				}
					switch((String) cb.getSelectedItem()) {
						case "Translate":
							Transformer.translate(currentShape, (int) xVal, (int) yVal);
							break;
						case "Rotate":
							Transformer.rotate(currentShape, angleVal);
							break;
						case "Scale":
							Transformer.scale(currentShape, xVal, yVal);
							break;
						case "Shear":
							Transformer.shear(currentShape, xVal, yVal);
							break;
					}
					CurrentShapeChosen.getDrawShapeCommand().drawShape();
			}
		});

		pane.add(topPane, BorderLayout.PAGE_START);
		pane.add(midPane, BorderLayout.CENTER);
		pane.add(okButton, BorderLayout.PAGE_END);
		
		this.pack();
	}
	
	/**
	 * Przy wybraniu okna z menu pojawia siê to okno jeœli
	 * jeszcze nie by³o lub znika jeœli ju¿ by³o.
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