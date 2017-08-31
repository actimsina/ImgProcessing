package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class DrawingPanel extends JPanel {

	BufferedImage currentImage;

	public void setCurrentImage(String ImageFile) {
		try {
			currentImage = ImageIO.read(new File("imgs/full/" + ImageFile));
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
