package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dbservice.UserService;
import model.User;

public class MainDashboard {

	public MainDashboard(int userId) {
		UserService uservice = new UserService();
		User user = new User();
		user = uservice.getUserDetailsById(userId);

		JFrame frame = new JFrame("Welcome " + user.getName());

		JLabel statusLbl = new JLabel("");

		DrawingPanel myDrawing = new DrawingPanel(statusLbl);
		frame.add(myDrawing, BorderLayout.CENTER);

		LibraryPanel myLibrary = new LibraryPanel(userId, myDrawing);
		frame.add(myLibrary, BorderLayout.EAST);

		ShapesPanel shapesPanel = new ShapesPanel(myDrawing);
		frame.add(shapesPanel, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1080, 720);
	}

}
