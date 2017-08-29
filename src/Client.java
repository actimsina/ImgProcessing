import javax.swing.SwingUtilities;

import ui.LoginFrame;

public class Client {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new LoginFrame();
			}
		});
	}

}
