import java.io.File;

import javax.swing.SwingUtilities;

import ui.LoginFrame;

public class Client {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createImageFolders();
				// new MainDashboard(1);
				new LoginFrame();
			}
		});
	}

	private static void createImageFolders() {
		File imageDir = new File("imgs/full");
		File thumbDir = new File("imgs/thumbs");

		if (!imageDir.exists())
			imageDir.mkdirs();

		if (!thumbDir.exists())
			thumbDir.mkdirs();
	}

}
