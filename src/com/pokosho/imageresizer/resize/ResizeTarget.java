package com.pokosho.imageresizer.resize;
/**
 * リサイズ設定ターゲット.
 * @author kaiba
 *
 */
public class ResizeTarget {
	private String dstDir;
	private float rate;
	public String getTargertDir() {
		return dstDir;
	}
	public void setDstDir(String s) {
		this.dstDir = s;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
}
