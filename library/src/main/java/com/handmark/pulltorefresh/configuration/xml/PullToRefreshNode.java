package com.handmark.pulltorefresh.configuration.xml;

import java.util.HashMap;
import java.util.Map;

import com.handmark.pulltorefresh.library.internal.Assert;

class PullToRefreshNode {

	private final Map<String, String> loadingLayoutClazzNameMap;
	private final Map<String, String> indicatorLayoutClazzNameMap;

	public PullToRefreshNode(Map<String, String> loadingLayoutClazzNameMap,
			Map<String, String> indicatorLayoutClazzNameMap) {
		this.loadingLayoutClazzNameMap = loadingLayoutClazzNameMap;
		this.indicatorLayoutClazzNameMap = indicatorLayoutClazzNameMap;
	}

	public String getIndicatorLayoutClazzName(String layoutCode) {
		return indicatorLayoutClazzNameMap.get(layoutCode);
	}

	public String getLoadingLayoutClazzName(String layoutCode) {
		return loadingLayoutClazzNameMap.get(layoutCode);
	}

	public void extendProperties(PullToRefreshNode extendedNode) {
		Assert.notNull(extendedNode, "Extended Node");
		Map<String, String> indicatorMap = extendedNode.indicatorLayoutClazzNameMap;
		Map<String, String> loadingMap = extendedNode.loadingLayoutClazzNameMap;
		
		indicatorLayoutClazzNameMap.putAll(indicatorMap);
		loadingLayoutClazzNameMap.putAll(loadingMap);
	}

	
}
