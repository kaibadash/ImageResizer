package com.pokosho.imageresizer.resize;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pokosho.imageresizer.ImageUtil;

public class ImageResizer {
	private static final Logger logger = LoggerFactory
			.getLogger(ImageResizer.class);
	private String baseDir;
	private ResizeSetting setting;

	public ImageResizer(String dir, String pathToSetting) throws JSONException, IOException {
		this.baseDir = dir;
		this.setting = JSON.decode(new FileReader(pathToSetting),
				ResizeSetting.class);
	}

	public void resize() {
		Pattern targetPattern = Pattern.compile(setting.getTargetRegexpStr());
		Pattern filterPattern = Pattern.compile(setting.getFilterRegexpStr());
		// dirからファイル一覧を取得
		File baseDirFile = new File(baseDir);
		File files[] = baseDirFile.listFiles();
		// 出力先ごと
		for (ResizeTarget resizeTarget : setting.getResizeTargetList()) {
			if (resizeTarget == null) {
				continue;
			}
			// 各ファイルごとに
			for (File f : files) {
				if (f.isDirectory()) {
					continue;
				}
				Matcher targetMatcher = targetPattern.matcher(f.getName());
				if (!targetMatcher.matches()
						|| filterPattern.matcher(f.getName()).matches()) {
					// 対象ファイルではない
					continue;
				}
				BufferedImage resizedImage = null;
				try {
					logger.debug("start resize:" + f.getName());
					resizedImage = ImageUtil.resizeImage(f.getAbsolutePath(),
							resizeTarget.getRate());
					// 出力ファイル名の取得
					String outFileName = setting.getRenamedBackReference(); // 後方参照を使って@2xに対応する
					if (outFileName.length() == 0) {
						// renamedBackReferenceの指定がないときは入力ファイル名と同じとする
						outFileName = f.getName();
					} else {
						for (int i = 1, j = targetMatcher.groupCount(); i <= j; i++) {
							outFileName = outFileName.replace("$" + i,
									targetMatcher.group(i));
						}
					}
					logger.debug("write to:" + outFileName);
					ImageIO.write(resizedImage, "png", new File(baseDir + "/"
							+ resizeTarget.getTargertDir() + "/" + outFileName));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
