package dbservice;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageService {

	public static BufferedImage resizeToThumbnail(BufferedImage img) {
		BufferedImage thumbnailImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = thumbnailImage.createGraphics();

		g2d.drawImage(img, 0, 0, 32, 32, null);
		g2d.dispose();
		return thumbnailImage;
	}

	public static BufferedImage resizeToFull(BufferedImage img) {
		BufferedImage fullImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = fullImage.createGraphics();

		g2d.drawImage(img, 0, 0, 800, 600, null);
		g2d.dispose();
		return fullImage;
	}

	public static void prepareImageFiles(File file) {

		String fullPath = "imgs/full/" + file.getName();
		String thumbPath = "imgs/thumbs/" + file.getName();

		try {

			BufferedImage originalImg = ImageIO.read(file);

			BufferedImage thumbImg = resizeToThumbnail(originalImg);
			BufferedImage fullImg = resizeToFull(originalImg);

			ImageIO.write(thumbImg, "jpg", new File(thumbPath));
			ImageIO.write(fullImg, "jpg", new File(fullPath));

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
