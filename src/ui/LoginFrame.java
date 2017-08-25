package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dbservice.UserService;
import model.User;

public class LoginFrame {
	JFrame frame;
	JLabel nameLbl;
	JTextField nameFld;
	JLabel pwdLbl;
	JPasswordField pwdFld;
	JButton registerBtn;
	JButton loginBtn;

	User user;

	public LoginFrame() {

		frame = new JFrame();
		frame.setTitle("Register / Login Frame");

		nameLbl = new JLabel("Name:");
		nameFld = new JTextField();
		pwdLbl = new JLabel("Password:");
		pwdFld = new JPasswordField();

		registerBtn = new JButton("Register");
		loginBtn = new JButton("Login");

		registerBtn.addActionListener(new RegisterListener());
		loginBtn.addActionListener(new LoginListener());

		nameLbl.setBounds(50, 50, 100, 30);
		nameFld.setBounds(150, 50, 200, 30);
		pwdLbl.setBounds(50, 130, 100, 30);
		pwdFld.setBounds(150, 130, 200, 30);
		registerBtn.setBounds(50, 210, 100, 30);
		loginBtn.setBounds(250, 210, 100, 30);

		frame.add(nameLbl);
		frame.add(nameFld);
		frame.add(pwdLbl);
		frame.add(pwdFld);
		frame.add(registerBtn);
		frame.add(loginBtn);

		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}

	class RegisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = nameFld.getText();
			String pwd = new String(pwdFld.getPassword());

			UserService us = new UserService();
			int userId = us.registerUser(name, pwd);

			if (userId > 0) {
				JOptionPane.showMessageDialog(null, "User ucessfully registered");
			} else
				JOptionPane.showMessageDialog(null, "Failed to register user");

		}

	}

	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = nameFld.getText();
			String pwd = new String(pwdFld.getPassword());

			UserService us = new UserService();
			int userId = us.loginUser(name, pwd);

			if (userId > 0) {
				JOptionPane.showMessageDialog(null, "Login Success " + userId);
			} else
				JOptionPane.showMessageDialog(null, "Login failed");

		}

	}
}
