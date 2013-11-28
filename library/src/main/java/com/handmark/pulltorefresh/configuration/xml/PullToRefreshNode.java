package com.handmark.pulltorefresh.configuration.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name="PullToRefresh")
class PullToRefreshNode {
	@Element(name = "LoadingLayouts")
	public LoadingLayoutsNode loadingLayoutsNode;
	@Element(name = "IndicatorLayouts")
	public IndicatorLayoutsNode indicatorLayoutsNode;
}
