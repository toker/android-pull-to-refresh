package com.handmark.pulltorefresh.configuration.xml;

import java.util.HashMap;
import java.util.Map;

import com.handmark.pulltorefresh.library.internal.Assert;

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

	public void extendProperties(PullToRefreshNode extendedNode) {
		Assert.notNull(extendedNode, "Extended Node");
		Map<Integer, String> indicatorMap = extendedNode.indicatorLayoutClazzNameMap;
		Map<Integer, String> loadingMap = extendedNode.loadingLayoutClazzNameMap;
		
		indicatorLayoutClazzNameMap.putAll(indicatorMap);
		loadingLayoutClazzNameMap.putAll(loadingMap);
	}

	
}
