package ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import dbservice.ImageService;
import dbservice.UserService;
import model.User;

public class MainDashboard {
	JFrame frame;

	UserService uservice;
	User user;

	public MainDashboard(int id) {
		uservice = new UserService();
		user = new User();
		user = uservice.getUserDetailsById(id);
		frame = new JFrame("Welcome " + user.getName());

		LibraryPanel myLibrary = new LibraryPanel(id);
		frame.add(myLibrary, BorderLayout.EAST);

		DrawingPanel myDrawing = new DrawingPanel();
		frame.add(myDrawing, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1080, 720);
	}

	class LibraryPanel extends JPanel {

		JFileChooser fileChooser;
		JButton openFileBtn;

		FileNameExtensionFilter filter;
		BufferedImage originalImg;

		BufferedImage thumbImg;
		BufferedImage fullImg;

		String selectedFileName;

		public LibraryPanel(int userId) {
			fileChooser = new JFileChooser();
			filter = new FileNameExtensionFilter("JPG only", "jpg");
			fileChooser.setFileFilter(filter);
			openFileBtn = new JButton("Add image");

			ArrayList<String> fileNames = new UserService().getFileNames(userId);

			DefaultListModel<String> listModel = new DefaultListModel<>();
			for (String str : fileNames) {
				listModel.addElement(str);
			}

			JLabel imgLbl = new JLabel("My Images: ");
			JList<String> listOfImages = new JList<>(listModel);

			listOfImages.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JList list = (JList) e.getSource();

					if (e.getClickCount() == 2) {
						selectedFileName = (String) list.getSelectedValue();
						DrawingPanel myPanel = new DrawingPanel();
						myPanel.setCurrentImage(selectedFileName);
						myPanel.repaint();
					}

				}
			});

			openFileBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int returnVal = fileChooser.showOpenDialog(null);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();

						ImageService.prepareImageFiles(file);

						new UserService().addImage(file.getName(), userId);

						listModel.addElement(file.getName());

					}
				}

			});
			add(openFileBtn);

			add(imgLbl);
			add(listOfImages);

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		}

		public String getSelectedFileName() {
			return selectedFileName;
		}

	}

	// Panel to be used for drawing images
	class DrawingPanel extends JPanel {

		BufferedImage currentImage;

		public void setCurrentImage(String ImageFile) {
			try {
				currentImage = ImageIO.read(new File("imgs/full/" + ImageFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public DrawingPanel() {

			try {
				currentImage = ImageIO.read(new File("imgs/full/fruit.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void paintComponent(Graphics g) {
			if (currentImage != null)
				g.drawImage(currentImage, 0, 0, null);
		}

	}

}
