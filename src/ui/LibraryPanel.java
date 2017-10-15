package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import dbservice.ImageService;
import dbservice.UserService;

@SuppressWarnings("serial")
class LibraryPanel extends JPanel {

	JFileChooser fileChooser;
	JButton openFileBtn;
	JButton saveFileBtn;

	FileNameExtensionFilter filter;
	BufferedImage originalImg;

	BufferedImage thumbImg;
	BufferedImage fullImg;

	String selectedFileName;

	public LibraryPanel(int userId, DrawingPanel myDrawing) {
		fileChooser = new JFileChooser();
		filter = new FileNameExtensionFilter("JPG only", "jpg");
		fileChooser.setFileFilter(filter);
		openFileBtn = new JButton("Add image");
		saveFileBtn = new JButton("Save image");

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
				@SuppressWarnings("rawtypes")
				JList list = (JList) e.getSource();

				if (e.getClickCount() == 2) {
					selectedFileName = (String) list.getSelectedValue();
					myDrawing.setCurrentImage(selectedFileName);
					myDrawing.repaint();
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

		saveFileBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();

					if (!file.exists()) {
						if (myDrawing.saveImage(file))
							JOptionPane.showMessageDialog(null, "Image saved successfully");

					} else {
						JOptionPane.showMessageDialog(null, "File already exists");
					}
				}
			}

		});

		add(openFileBtn);
		add(saveFileBtn);
		add(imgLbl);
		add(listOfImages);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	}

}
