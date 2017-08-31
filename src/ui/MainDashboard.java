package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import dbservice.UserService;
import model.User;

public class MainDashboard {
	JFrame frame;

	UserService uservice;
	User user;

	DrawingPanel myDrawing;

	public MainDashboard(int userId) {
		uservice = new UserService();
		user = new User();
		user = uservice.getUserDetailsById(userId);
		frame = new JFrame("Welcome " + user.getName());

		myDrawing = new DrawingPanel();
		frame.add(myDrawing, BorderLayout.CENTER);

		LibraryPanel myLibrary = new LibraryPanel(userId, myDrawing);
		frame.add(myLibrary, BorderLayout.EAST);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1080, 720);
	}

}
