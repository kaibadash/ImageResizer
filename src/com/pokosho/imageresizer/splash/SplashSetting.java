package com.pokosho.imageresizer.splash;

import java.util.List;

/**
 * Splash�摜�̐ݒ�t�@�C��.
 * @author kaiba
 *
 */
public class SplashSetting {
	private String description;
	private List<SplashTarget> splashTargetList;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<SplashTarget> getSplashTargetList() {
		return splashTargetList;
	}
	public void setSplashTargetList(List<SplashTarget> splashTargetList) {
		this.splashTargetList = splashTargetList;
	}
}
