package com.pokosho.imageresizer.splash;

import java.util.List;

/**
 * Splash‰æ‘œ‚Ìİ’èƒtƒ@ƒCƒ‹.
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
