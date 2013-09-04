package com.pokosho.imageresizer.resize;

import java.util.List;

public class ResizeSetting {
	private String targetRegexpStr;
	private String filterRegexpStr;
	private String renamedBackReference;
	private List<ResizeTarget> resizeTargetList;

	public String getRenamedBackReference() {
		return renamedBackReference;
	}

	public void setRenamedBackReference(String renamedBackReference) {
		this.renamedBackReference = renamedBackReference;
	}

	public String getTargetRegexpStr() {
		return targetRegexpStr;
	}

	public void setTargetRegexpStr(String targetRegexpStr) {
		this.targetRegexpStr = targetRegexpStr;
	}

	public String getFilterRegexpStr() {
		return filterRegexpStr;
	}

	public void setFilterRegexpStr(String filterRegexpStr) {
		this.filterRegexpStr = filterRegexpStr;
	}

	public List<ResizeTarget> getResizeTargetList() {
		return resizeTargetList;
	}

	public void setResizeTargetList(List<ResizeTarget> list) {
		this.resizeTargetList = list;
	}

}
