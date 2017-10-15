package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import myshapes.LinkedList;
import myshapes.MyLine;
import myshapes.MyOval;
import myshapes.MyRectangle;
import myshapes.MyShape;

public class DrawingPanel extends JPanel {
	private LinkedList<MyShape> myShapes;
	private LinkedList<MyShape> clearedShapes;

	private int currentShapeType;
	private MyShape currentShapeObject;
	private Color currentShapeColor;
	private boolean currentShapeFilled;

	JLabel statusLabel;

	BufferedImage currentImage;

	public DrawingPanel(JLabel statusLabel) {

		myShapes = new LinkedList<MyShape>();
		clearedShapes = new LinkedList<MyShape>();

		currentShapeType = 0;
		currentShapeObject = null;
		currentShapeColor = Color.BLACK;
		currentShapeFilled = false;

		this.statusLabel = statusLabel;

		setLayout(new BorderLayout());

		setBackground(Color.WHITE);
		add(statusLabel, BorderLayout.SOUTH);

		MouseHandler handler = new MouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);
	}

	public void setCurrentImage(String ImageFile) {
		try {
			currentImage = ImageIO.read(new File("imgs/full/" + ImageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if (currentImage != null)
			g2d.drawImage(currentImage, 0, 0, null);

		ArrayList<MyShape> shapeArray = myShapes.getArray();
		for (int counter = shapeArray.size() - 1; counter >= 0; counter--)
			shapeArray.get(counter).draw(g2d);

		if (currentShapeObject != null)
			currentShapeObject.draw(g2d);

	}

	public boolean saveImage(File file) {

		BufferedImage bi = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bi.createGraphics();

		if (currentImage != null)
			g2d.drawImage(currentImage, 0, 0, null);

		ArrayList<MyShape> shapeArray = myShapes.getArray();
		for (int counter = shapeArray.size() - 1; counter >= 0; counter--)
			shapeArray.get(counter).draw(g2d);

		if (currentShapeObject != null)
			currentShapeObject.draw(g2d);

		try {
			ImageIO.write(bi, "jpg", file);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;

	}

	public void setCurrentShapeType(int type) {
		currentShapeType = type;
	}

	public void setCurrentShapeColor(Color color) {
		currentShapeColor = color;
	}

	public void setCurrentShapeFilled(boolean filled) {
		currentShapeFilled = filled;
	}

	public void clearLastShape() {
		if (!myShapes.isEmpty()) {
			clearedShapes.addFront(myShapes.removeFront());
			repaint();
		}
	}

	public void redoLastShape() {
		if (!clearedShapes.isEmpty()) {
			myShapes.addFront(clearedShapes.removeFront());
			repaint();
		}
	}

	public void clearDrawing() {
		myShapes.makeEmpty();
		clearedShapes.makeEmpty();
		repaint();
	}

	private class MouseHandler extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent event) {
			switch (currentShapeType) // 0 for line, 1 for rect, 2 for oval
			{
			case 0:
				currentShapeObject = new MyLine(event.getX(), event.getY(), event.getX(), event.getY(),
						currentShapeColor);
				break;
			case 1:
				currentShapeObject = new MyRectangle(event.getX(), event.getY(), event.getX(), event.getY(),
						currentShapeColor, currentShapeFilled);
				break;
			case 2:
				currentShapeObject = new MyOval(event.getX(), event.getY(), event.getX(), event.getY(),
						currentShapeColor, currentShapeFilled);
				break;

			}
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			currentShapeObject.setX2(event.getX());
			currentShapeObject.setY2(event.getY());

			myShapes.addFront(currentShapeObject);

			currentShapeObject = null;
			clearedShapes.makeEmpty();
			repaint();

		}

		@Override
		public void mouseMoved(MouseEvent event) {
			statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d", event.getX(), event.getY()));
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			currentShapeObject.setX2(event.getX());
			currentShapeObject.setY2(event.getY());

			statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d", event.getX(), event.getY()));

			repaint();

		}

	}

}