package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Provides the GUI and encapsulates the DrawPanel It creates 3 buttons undo,
 * redo and clear. It creates 2 combobox colors and shapes. It creates 1
 * checkbox filled to select whether shape is filled or not. Has two private
 * inner classes One for handling button events Another for handling both combo
 * box events and checkbox events
 */
public class ShapesPanel extends JPanel {
	private DrawingPanel panel; // draw panel for the shapes

	private JButton undo; // button to undo last drawn shape
	private JButton redo; // button to redo an undo
	private JButton clear; // button to clear panel

	private JComboBox colors; // combobox with color options

	// array of strings containing color options for JComboBox colors
	private String colorOptions[] = { "Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
			"Orange", "Pink", "Red", "White", "Yellow" };

	// aray of Color objects contating Color constants
	private Color colorArray[] = { Color.BLACK, Color.BLUE, Color.CYAN, Color.darkGray, Color.GRAY, Color.GREEN,
			Color.lightGray, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW };

	private JComboBox shapes; // combobox with shape options

	// array of strings containing shape options for JComboBox shapes
	private String shapeOptions[] = { "Line", "Rectangle", "Oval" };

	private JCheckBox filled; // checkbox to select whether shape is filled or
								// not

	private JPanel widgetJPanel; // holds the widgets: buttons, comboboxes and
									// checkbox
	private JPanel widgetPadder; // encapsulates widgetJPanel and adds padding
									// around the edges

	/**
	 * This constructor sets the name of the JFrame. It also creates a DrawPanel
	 * object that extends JPanel for drawing the shapes and contains a
	 * statuslabel for mouse position. Initializes widgets for buttons,
	 * comboboxes and checkbox It also adds event handlers for the widgets
	 */
	public ShapesPanel(DrawingPanel drawingPanel) {

		this.panel = drawingPanel;

		undo = new JButton("Undo");
		redo = new JButton("Redo");
		clear = new JButton("Clear");

		colors = new JComboBox(colorOptions);
		shapes = new JComboBox(shapeOptions);

		filled = new JCheckBox("Filled");

		widgetJPanel = new JPanel();
		widgetJPanel.setLayout(new GridLayout(1, 6, 10, 10)); // sets padding
																// between
																// widgets in
																// gridlayout

		widgetPadder = new JPanel();
		widgetPadder.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5)); // sets
																			// padding
																			// around
																			// the
																			// edges

		widgetJPanel.add(undo);
		widgetJPanel.add(redo);
		widgetJPanel.add(clear);
		widgetJPanel.add(colors);
		widgetJPanel.add(shapes);
		widgetJPanel.add(filled);

		widgetPadder.add(widgetJPanel);

		add(widgetPadder, BorderLayout.NORTH);
		// add(panel, BorderLayout.CENTER);

		// create new ButtonHandler for button event handling
		ButtonHandler buttonHandler = new ButtonHandler();
		undo.addActionListener(buttonHandler);
		redo.addActionListener(buttonHandler);
		clear.addActionListener(buttonHandler);

		// create handlers for combobox and checkbox
		ItemListenerHandler handler = new ItemListenerHandler();
		colors.addItemListener(handler);
		shapes.addItemListener(handler);
		filled.addItemListener(handler);

	}

	/**
	 * private inner class for button event handling
	 */
	private class ButtonHandler implements ActionListener {
		// handles button events
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals("Undo")) {
				panel.clearLastShape();
			} else if (event.getActionCommand().equals("Redo")) {
				panel.redoLastShape();
			} else if (event.getActionCommand().equals("Clear")) {
				panel.clearDrawing();
			}

		} // end method actionPerformed
	} // end private inner class ButtonHandler

	/**
	 * private inner class for checkbox and combobox event handling
	 */
	private class ItemListenerHandler implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			// process filled checkbox events
			if (event.getSource() == filled) {
				boolean checkFill = filled.isSelected() ? true : false; //
				panel.setCurrentShapeFilled(checkFill);
			}

			// determine whether combo box selected
			if (event.getStateChange() == ItemEvent.SELECTED) {
				// if event source is combo box colors pass in colorArray at
				// index selected.
				if (event.getSource() == colors) {
					panel.setCurrentShapeColor(colorArray[colors.getSelectedIndex()]);
				}

				// else if event source is combo box shapes pass in index
				// selected
				else if (event.getSource() == shapes) {
					panel.setCurrentShapeType(shapes.getSelectedIndex());
				}
			}

		} // end method itemStateChanged
	}

} // end class DrawFrame