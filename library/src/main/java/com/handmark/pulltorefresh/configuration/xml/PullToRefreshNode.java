package com.handmark.pulltorefresh.configuration.xml;

import java.util.Map;

class PullToRefreshNode {

	private final Map<Integer, String> loadingLayoutClazzNameMap;
	private final Map<Integer, String> indicatorLayoutClazzNameMap;

	public PullToRefreshNode(Map<Integer, String> loadingLayoutClazzNameMap,
			Map<Integer, String> indicatorLayoutClazzNameMap) {
		this.loadingLayoutClazzNameMap = loadingLayoutClazzNameMap;
		this.indicatorLayoutClazzNameMap = indicatorLayoutClazzNameMap;
	}

	public String getIndicatorLayoutClazzName(Integer layoutCode) {
		return indicatorLayoutClazzNameMap.get(layoutCode);
	}

	public String getLoadingLayoutClazzName(Integer layoutCode) {
		return loadingLayoutClazzNameMap.get(layoutCode);
	}

	
}
