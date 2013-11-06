package com.handmark.pulltorefresh.configuration.xml;

import java.util.List;
import java.util.Map;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

@Root(name="IndicatorLayouts")
public class IndicatorLayoutsNode {
	@ElementMap(entry="layout", key = "value",keyType=Integer.class, attribute = true, required = false, inline=true)
	private Map<Integer, String> layoutMap;
	
	public String getLayoutClazzName(Integer layoutCode) {
		if ( null == layoutMap ) {
			return null;
		}
		return layoutMap.get(layoutCode);
	}
}
