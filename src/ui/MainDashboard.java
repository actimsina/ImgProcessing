package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import dbservice.UserService;
import model.User;

public class MainDashboard {
	JFrame frame;

	public MainDashboard(User user) {

		frame = new JFrame();
		frame.setTitle("Welcome " + user.getName());

		frame.add(new LibraryPanel(user), BorderLayout.EAST);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 600);
	}

	class LibraryPanel extends JPanel {

		JFileChooser fileChooser;
		JButton openFileBtn;

		FileNameExtensionFilter filter;
		BufferedImage originalImg;

		// BufferedImage thumbnaiImg;
		// BufferedImage fullImg;

		public LibraryPanel(User user) {
			fileChooser = new JFileChooser();
			filter = new FileNameExtensionFilter("JPG & GIF only", "jpg", "gif");
			fileChooser.setFileFilter(filter);
			openFileBtn = new JButton("Open image");
			openFileBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int returnVal = fileChooser.showOpenDialog(null);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						// Read as Buffered image using ImageIO
						try {
							originalImg = ImageIO.read(file);
							UserService us = new UserService();
							us.saveImage(user.getId(), file.getName(), originalImg);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}
				}
			});

			add(openFileBtn);
		}

	}

	// Panel to be used for drawing images
	class DrawingPanel extends JPanel {

	}

}
