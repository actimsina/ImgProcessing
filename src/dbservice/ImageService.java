package dbservice;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageService {

	public static BufferedImage resizeToThumbnail(Image img) {
		BufferedImage thumbnailImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = thumbnailImage.createGraphics();
		g2d.drawImage(img, 0, 0, 32, 32, null);
		g2d.dispose();
		return thumbnailImage;
	}

	public static BufferedImage resizeToFull(Image img) {
		BufferedImage fullImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = fullImage.createGraphics();
		g2d.drawImage(img, 0, 0, 800, 600, null);
		g2d.dispose();
		return fullImage;
	}
}
