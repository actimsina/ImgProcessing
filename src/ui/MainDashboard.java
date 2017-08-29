package ui;

import javax.swing.JFrame;

import dbservice.UserService;
import model.User;

public class MainDashboard {
	JFrame frame;

	User user;
	UserService uservice;

	public MainDashboard(int id) {
		uservice = new UserService();
		user = uservice.getUserDetailsById(id);
		frame = new JFrame("Welcome " + user.getName());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 600);
	}
}
