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

		// g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
		// RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		// g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
		// RenderingHints.VALUE_DITHER_ENABLE);

		g2d.drawImage(img, 0, 0, 32, 32, null);
		g2d.dispose();
		return thumbnailImage;
	}

	public static BufferedImage resizeToFull(BufferedImage img) {
		BufferedImage fullImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = fullImage.createGraphics();

		// g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
		// RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		// g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
		// RenderingHints.VALUE_DITHER_ENABLE);

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

	public static void drawImage(BufferedImage img, Graphics2D g2d) {

		g2d.drawImage(img, 0, 0, null);

	}

}
