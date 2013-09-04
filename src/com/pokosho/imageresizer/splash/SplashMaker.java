package com.pokosho.imageresizer.splash;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pokosho.imageresizer.ImageUtil;

/**
 * Splash maker.
 * 
 * @author kaiba
 * 
 */
public class SplashMaker {
	private static final Logger logger = LoggerFactory
			.getLogger(SplashMaker.class);
	private String pathToIconImage;
	private SplashSetting setting;

	/**
	 * コンストラクタ.
	 * 
	 * @param pathToBaseImage
	 * @param backgroundColorString
	 *            #rrggbbaa
	 * @param pathToSetting
	 * @throws IOException
	 * @throws
	 * @throws JSONException
	 */
	public SplashMaker(String pathToBaseImage, String backgroundColorString,
			String pathToSetting) throws JSONException, IOException {
		this.pathToIconImage = pathToBaseImage;
		this.setting = JSON.decode(new FileReader(pathToSetting),
				SplashSetting.class);
	}

	public void createSplashImages() {
		BufferedImage iconImage = null;
		File f = new File(pathToIconImage);
		try {
			iconImage = ImageIO.read(f);
		} catch (IOException e) {
			logger.error("画像の読み込みに失敗.", e);
			return;
		}
		// 出力先ごと
		for (SplashTarget resizeTarget : setting.getSplashTargetList()) {
			if (resizeTarget == null) {
				continue;
			}
			logger.debug("start create splash image");
			BufferedImage backgroundImage = new BufferedImage(
					resizeTarget.getW(), resizeTarget.getH(),
					BufferedImage.TYPE_INT_ARGB);
			ImageUtil.fillBackgroundColor(backgroundImage, Color.blue); // TODO:color
			// アイコンを縮小率を計算
			int targetSize = 0;
			int x = 0, y = 0;
			// 横長の時は横を基準、縦長の時は縦を基準
			if (iconImage.getWidth() > iconImage.getHeight()) {
				targetSize = iconImage.getWidth() / resizeTarget.getW();
				y = (resizeTarget.getH() - iconImage.getHeight()) / 2;
			} else {
				targetSize = iconImage.getHeight() / resizeTarget.getH();
				x = (resizeTarget.getW() - iconImage.getWidth()) / 2;
			}
			// アイコンを縮小
			try {
				iconImage = ImageUtil.resizeImage(pathToIconImage,
						targetSize);
			} catch (IOException e) {
				logger.error("画像の縮小に失敗.", e);
				continue;
			}
			// アイコンを中央に配置する
			Graphics2D g = backgroundImage.createGraphics();
			g.drawImage(iconImage, x, y, null);
			g.dispose();
			// 出力
			String pathOutputImage = f.getParent() + resizeTarget.getDstPath();
			logger.debug("write to:" + pathOutputImage);
			try {
				ImageIO.write(backgroundImage, "png", new File(pathOutputImage));
			} catch (IOException e) {
				logger.error("画像の保存に失敗.", e);
				continue;
			}
		}
	}
}
