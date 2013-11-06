package com.handmark.pulltorefresh.configuration.xml;

import java.util.Map;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import android.util.Log;


@Root(name="PullToRefresh")
public class PullToRefreshNode {
	@Element(name = "LoadingLayouts")
	public LoadingLayoutsNode loadingLayoutsNode;
	@Element(name = "IndicatorLayouts")
	public IndicatorLayoutsNode indicatorLayoutsNode;

}
