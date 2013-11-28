package com.handmark.pulltorefresh.configuration.xml;

import java.util.List;
import java.util.Map;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

@Root(name="LoadingLayouts")
class LoadingLayoutsNode {

	@ElementMap(entry="layout", key = "value",keyType=Integer.class,valueType=String.class, attribute = true, required = false, inline=false)
	private Map<Integer, String> layoutMap;

	public String getLayoutClazzName(Integer layoutCode) {
		if (null == layoutMap) {
			return null;
		}
		return layoutMap.get(layoutCode);
	}
}
