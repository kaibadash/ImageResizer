package com.pokosho.imageresizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mortennobel.imagescaling.ResampleOp;

public class ImageUtil {	
	/**
	 * éwíËÇµÇΩèkè¨ó¶Ç≈âÊëúÇèkè¨ÇµÇƒï‘Ç∑.
	 * 
	 * @param filePath
	 * @param rate
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage resizeImage(String filePath, float rate)
			throws IOException {
		BufferedImage img = ImageIO.read(new File(filePath));
		int w = (int) (img.getWidth() * rate);
		int h = (int) (img.getHeight() * rate);
		//return resizeWithPoJo(img, w, h);
		return resizeWithLib(img, w, h);
	}
	
	public static BufferedImage resizeImage(String filePath, int targetSize)
			throws IOException {
		BufferedImage img = ImageIO.read(new File(filePath));
		return resizeImage(img, targetSize);
	}
	
	public static BufferedImage resizeImage(BufferedImage img, int targetSize)
			throws IOException {
		int size = Math.max(img.getWidth(), img.getHeight());
		int rate = targetSize / size;
		return resizeWithLib(img, img.getWidth() * rate, img.getHeight() * rate);
	}
	
	
	public static BufferedImage resizeWithLib(BufferedImage img, int w, int h) {
		ResampleOp  resampleOp = new ResampleOp(w, h);
		resampleOp.setFilter(com.mortennobel.imagescaling.ResampleFilters.getBiCubicFilter());
		resampleOp.setUnsharpenMask(com.mortennobel.imagescaling.AdvancedResizeOp.UnsharpenMask.VerySharp);
		return resampleOp.filter(img, null);
	}
		
	public static BufferedImage resizeWithPoJo(BufferedImage img, int w, int h) {
		Image scaledImg = img.getScaledInstance(w, h,
				Image.SCALE_AREA_AVERAGING);

		BufferedImage buffered = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);
		//Graphics g = buffered.getGraphics();
		Graphics2D g = buffered.createGraphics();
		// g.fillRect(0, 0, 100, 100);
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_DITHERING,
				RenderingHints.VALUE_DITHER_ENABLE);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
//		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_NORMALIZE);
		g.drawImage(scaledImg, 0, 0, null);
		return buffered;
	}
	
	/**
	 * îwåiêFÇê›íËÇ∑ÇÈ.
	 * @param image
	 * @param c
	 */
	public static void fillBackgroundColor(BufferedImage image, Color c) {
		Graphics g = image.getGraphics();
		g.setColor(c);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.drawImage(image, 0, 0, null);
	}
	
	public static boolean checkImageSize() {
		// èkè¨Ç…ñ‚ëËÇ»Ç¢ÉTÉCÉYÇ©îªíËÇ∑ÇÈ.
		return false;
	}
}
