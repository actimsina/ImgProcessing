import javax.swing.SwingUtilities;

import dbservice.UserService;
import model.User;
import ui.MainDashboard;

public class Client {
	public static void main(String[] args) {

		UserService us = new UserService();
		User user = us.getUserDetailsById(1);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// new LoginFrame();

				new MainDashboard(user);
			}
		});
	}

}
