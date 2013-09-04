package com.pokosho.imageresizer;

import java.io.IOException;

import com.pokosho.imageresizer.resize.ImageResizer;

/**
 * エントリポイント.
 * @author kaiba
 *
 */
public class ImageResizerMain {
	public static void main(String[] args) {
		try {
			//ImageResizer imageResizer = new ImageResizer("/Users/kaiba/Documents/workspace2/ImageResizer/test", "./setting_resize/(iOS)create_non_retina_icon.json");
			ImageResizer imageResizer = new ImageResizer("/Users/kaiba/Documents/workspace2/ImageResizer/test_android/drawable-xxhdpi", "./setting_resize/(Android)create_icons_from_xxhdpi.json");
			imageResizer.resize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
