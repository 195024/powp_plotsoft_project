package edu.iis.powp.app.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PlotterGUI extends JFrame {
	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -7773430008248070296L;

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private Map<Class<?>, JMenu> menuList = null;

	/*
	 * GUI Objects.
	 */
	/**
	 * Menu bar.
	 */
	private JMenuBar menuBar = null;

	/**
	 * Driver menu.
	 */
	private JMenu driverMenu = null;

	/**
	 * Window menu.
	 */
	private JMenu windowMenu = null;

	/**
	 * Test menu.
	 */
	private JMenu testMenu = null;

	/**
	 * Current driver text Field.
	 */
	private JTextField currentDriverTextField = null;

	/**
	 * Free panel.
	 */
	private JPanel freePanel = null;

	/**
	 * Logger text area.
	 */
	private JTextArea loggerTextArea = null;

	/**
	 * Create the application.
	 */
	public PlotterGUI() {
		initialize();
		setupLogger();
		setupGlobalExceptionHandling();
	}

	public void setupGlobalExceptionHandling() {
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				LOGGER.warning(e.getStackTrace()[0].getClassName() + ":\n      " + e.toString());
			}
		});
	}

	private void setupLogger() {

		Logger logger = Logger.getGlobal();

		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		if (handlers[0] instanceof ConsoleHandler) {
			rootLogger.removeHandler(handlers[0]);
		}

		logger.addHandler(new Handler() {

			@Override
			public void publish(LogRecord record) {
				PlotterGUI.this.loggerTextArea
						.append(record.getSourceClassName() + ":\n   " + record.getMessage() + "\n");
			}

			@Override
			public void flush() {
			}

			@Override
			public void close() throws SecurityException {
			}
		});

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*
		 * Frame window with grid bag layout.
		 */
		this.setTitle("Plotter App");
		this.setBounds(50, 100, 840, 520);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		menuList = new HashMap<Class<?>, JMenu>();

		/*
		 * Menu bar.
		 */
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		/*
		 * Driver menu.
		 */
		driverMenu = new JMenu("Drivers");
		menuBar.add(driverMenu);

		/*
		 * Test menu.
		 */
		testMenu = new JMenu("User Test");
		menuBar.add(testMenu);

		/*
		 * Window menu
		 */
		windowMenu = new JMenu("Windows");
		menuBar.add(windowMenu);

		/*
		 * Main girdBagLayout - left Layout + command history.
		 */
		GridBagLayout mainGridBagLayout = new GridBagLayout();
		mainGridBagLayout.columnWidths = new int[] { 0, 540 };
		mainGridBagLayout.rowHeights = new int[] { 0, 0 };
		mainGridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		mainGridBagLayout.rowWeights = new double[] { 0.0, 1.0 };
		this.getContentPane().setLayout(mainGridBagLayout);

		/*
		 * Current driver textField.
		 */
		currentDriverTextField = new JTextField();
		currentDriverTextField.setEditable(false);
		currentDriverTextField.setColumns(0);
		GridBagConstraints gbcTextField = new GridBagConstraints();
		gbcTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcTextField.gridx = 0;
		gbcTextField.gridy = 0;
		getContentPane().add(currentDriverTextField, gbcTextField);

		/*
		 * Command history scroll pane.
		 */
		JScrollPane commandListScrollPane = new JScrollPane();
		GridBagConstraints gbcCommandListScrollPane = new GridBagConstraints();
		gbcCommandListScrollPane.fill = GridBagConstraints.BOTH;
		gbcCommandListScrollPane.gridheight = 2;
		gbcCommandListScrollPane.gridx = 1;
		gbcCommandListScrollPane.gridy = 0;
		this.getContentPane().add(commandListScrollPane, gbcCommandListScrollPane);

		/*
		 * Command history text area
		 */
		freePanel = new JPanel();
		commandListScrollPane.setViewportView(freePanel);

		/*
		 * Logger scroll pane.
		 */
		JScrollPane loggerScrollPane = new JScrollPane();
		GridBagConstraints gbcLoggerScrollPane = new GridBagConstraints();
		gbcLoggerScrollPane.fill = GridBagConstraints.BOTH;
		gbcLoggerScrollPane.gridx = 0;
		gbcLoggerScrollPane.gridy = 1;
		this.getContentPane().add(loggerScrollPane, gbcLoggerScrollPane);

		/*
		 * Logger text area.
		 */
		loggerTextArea = new JTextArea();
		loggerTextArea.setEditable(false);
		loggerScrollPane.setViewportView(loggerTextArea);

		// updatePlotterInfo();
	}

	public JPanel getFreePanel() {
		return freePanel;
	}

	public void addComponentMenu(Class<?> compClass, String name) {
		addComponentMenu(compClass, name, -1);
	}

	public void addComponentMenu(Class<?> compClass, String name, int position) {
		if (!menuList.containsKey(compClass)) {
			JMenu compMenu = new JMenu(name);
			menuBar.add(compMenu, position);
			menuList.put(compClass, compMenu);
		}
	}

	public void addComponentMenuElement(Class<?> compClass, String label, ActionListener listener) {
		if (menuList.containsKey(compClass)) {
			JMenu compMenu = menuList.get(compClass);

			JMenuItem compMenuElement = new JMenuItem(label);
			compMenuElement.addActionListener(listener);
			compMenu.add(compMenuElement);
		}
	}

	public void addComponentMenuElementWithCheckBox(Class<?> compClass, String label, ActionListener listener,
			boolean checkBoxState) {
		if (menuList.containsKey(compClass)) {
			JMenu compMenu = menuList.get(compClass);

			JCheckBoxMenuItem compMenuElement = new JCheckBoxMenuItem(label, checkBoxState);
			compMenuElement.addActionListener(listener);
			compMenu.add(compMenuElement);
		}
	}

	public JMenu getComponentMenu(Class<?> compClass) {
		return menuList.get(compClass);
	}

	public void addNewDriverMenuListener(ActionListener listener, String label) {
		JMenuItem driverMenuButton = new JMenuItem(label);
		driverMenuButton.addActionListener(listener);
		driverMenu.add(driverMenuButton);
	}

	public void addNewWindowMenuListener(ActionListener listener, String label) {
		JMenuItem windowMenuButton = new JMenuItem(label);
		windowMenuButton.addActionListener(listener);
		windowMenu.add(windowMenuButton);
	}

	public void addNewTestMenuListener(ActionListener listener, String label) {
		JMenuItem testMenuButton = new JMenuItem(label);
		testMenuButton.addActionListener(listener);
		testMenu.add(testMenuButton);
	}

	public void notifyObservers(String message) {

	}

	public void updatePlotterInfo(String plotterInfo) {
		currentDriverTextField.setText(plotterInfo);
	}

	public void flushLoggerTextArea() {
		this.loggerTextArea.setText("");
	}
	
	/**
	 * Metoda wy�wietla wiadomo�� przechwyconego wyj�tku w oknie programu.
	 * @param msg Wiadomo�� do wy�wietlenia w przypadku przechwycenia wyj�tku.
	 */
	public static void showCaughtExceptionInfo(String msg) {
		LOGGER.warning(msg);
	}
}
