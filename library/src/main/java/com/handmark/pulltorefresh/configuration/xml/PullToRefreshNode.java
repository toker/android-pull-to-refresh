package com.handmark.pulltorefresh.configuration.xml;

import java.util.Map;

import com.handmark.pulltorefresh.library.internal.Assert;
/**
 * {@code PullToRefreshNode} has an information in contents of pulltorefresh.xml, <br /> such as loading layout and indicator layout class name
 * @author Wonjun Kim
 *
 */
class PullToRefreshNode {
	/**
	 * Map Storing LoadingLayout class names<br />
	 * Key : layout code (String) <br />
	 * Value : class name (String)
	 */
	private final Map<String, String> loadingLayoutClazzNameMap;
	/**
	 * Map Storing IndicatorLayout class names<br />
	 * Key : layout code (String) <br />
	 * Value : Class name (String)
	 */
	private final Map<String, String> indicatorLayoutClazzNameMap;
	/**
	 * Constructor needs two class name {@code Map}s, which are LoadingLayout class name map and Indicator class name {@code map}.<br />
	 * NOTE: Parameters must go in order. First Parameter : loading layout / Second Parameter : indicator layout  
	 * @param loadingLayoutClazzNameMap LoadingLayout class names
	 * @param indicatorLayoutClazzNameMap
	 */
	public PullToRefreshNode(Map<String, String> loadingLayoutClazzNameMap,
			Map<String, String> indicatorLayoutClazzNameMap) {
		Assert.notNull(loadingLayoutClazzNameMap, "LoadingLayout Class Name Map");
		Assert.notNull(indicatorLayoutClazzNameMap, "Loading Layout Class Name Map");
		this.loadingLayoutClazzNameMap = loadingLayoutClazzNameMap;
		this.indicatorLayoutClazzNameMap = indicatorLayoutClazzNameMap;
	}
	/**
	 * @param layoutCode LoadingLayout layout code
	 * @return LoadingLayout class name
	 */
	public String getIndicatorLayoutClazzName(String layoutCode) {
		return indicatorLayoutClazzNameMap.get(layoutCode);
	}
	/**
	 * @param layoutCode LoadingLayout layout code
	 * @return LoadingLayout class name
	 */
	public String getLoadingLayoutClazzName(String layoutCode) {
		return loadingLayoutClazzNameMap.get(layoutCode);
	}
	/**
	 * Add an information from other {@code PullToRefreshNode} instance
	 * @param extendedNode Other {@code PullToRefresNode} to be combined
	 */
	public void extendProperties(PullToRefreshNode extendedNode) {
		Assert.notNull(extendedNode, "Extended Node");
		Map<String, String> indicatorMap = extendedNode.indicatorLayoutClazzNameMap;
		Map<String, String> loadingMap = extendedNode.loadingLayoutClazzNameMap;
		
		indicatorLayoutClazzNameMap.putAll(indicatorMap);
		loadingLayoutClazzNameMap.putAll(loadingMap);
	}
}
