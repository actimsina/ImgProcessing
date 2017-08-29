import javax.swing.SwingUtilities;

import ui.MainDashboard;

public class Client {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// new LoginFrame();
				new MainDashboard(1);
			}
		});
	}

}
