package com.handmark.pulltorefresh.configuration.xml;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.handmark.pulltorefresh.library.internal.Assert;

final class PullToRefreshConfigXmlParser extends XmlPullNodeParser<PullToRefreshNode> {
	private static final String TAG = PullToRefreshConfigXmlParser.class.getName();
	/**
	 * 
	 */ 
	private static final int ONLY_ONE_REPEAT = 1;
	/**
	 * 
	 */
	private PullToRefreshResult result;
	/**
	 * 
	 * @param parser
	 */
	public PullToRefreshConfigXmlParser(XmlPullParserWrapper parser) {
		super(parser);
	}

	/**
	 * 
	 * @return
	 */
	private XmlPullNode init() {
		//
		result = new PullToRefreshResult();
		//
		XmlPullNode root = new XmlPullNode("PullToRefresh");
		XmlPullNode loadingLayouts = new XmlPullNode("LoadingLayouts");
		XmlPullNode indicatorLayouts = new XmlPullNode("IndicatorLayouts");
		XmlPullNode loadingLayout = new XmlPullNode("layout",new LayoutNodeCallback(result.loadingLayoutClazzNameMap));
		XmlPullNode indicatorLayout = new XmlPullNode("layout",new LayoutNodeCallback(result.indicatorLayoutClazzNameMap));
		//
		root.addChildNode(loadingLayouts, ONLY_ONE_REPEAT);
		root.addChildNode(indicatorLayouts, ONLY_ONE_REPEAT);
		loadingLayouts.addChildNode(loadingLayout);
		indicatorLayouts.addChildNode(indicatorLayout);
		//
		return root;
	}
	/**
	 * 
	 */
	@Override
	protected XmlPullNode generateRootNode() {
		return init();
	}
	/**
	 *  
	 */
	@Override
	protected PullToRefreshNode getResult() {
		return new PullToRefreshNode(result.loadingLayoutClazzNameMap, result.indicatorLayoutClazzNameMap);
	}
	
	/**
	 * 
	 * @author NBP
	 *
	 */
	private static class PullToRefreshResult {
		public final Map<Integer, String> loadingLayoutClazzNameMap = new HashMap<Integer, String>();
		public final Map<Integer, String> indicatorLayoutClazzNameMap = new HashMap<Integer, String>();
	}
		
	/**
	 * 
	 * @author NBP
	 *
	 */
	private static class LayoutNodeCallback implements XmlPullNode.XmlPullNodeCallback {
		/**
		 * 
		 */
		private Map<Integer, String> layoutClazzNameMap;
		/**
		 * 
		 * @param layoutClazzNameMap
		 */
		public LayoutNodeCallback(Map<Integer, String> layoutClazzNameMap) {
			Assert.notNull(layoutClazzNameMap, "Class Map");
			this.layoutClazzNameMap = layoutClazzNameMap;
		}
		/**
		 * 
		 */
		@Override
		public void process(XmlPullParser parser) throws XmlPullParserException, IOException {
			// TODO : IMPLEMENT!
			int attributesCount = parser.getAttributeCount();
			String attributeName, attributeValue;
			int layoutKey;
			
			for (int i = 0; i < attributesCount; ++i) {
				attributeName = parser.getAttributeName(i);
				attributeValue = parser.getAttributeValue(i);
				
				if ( "value".equals(attributeName)) {
					// TODO: what if attributeValue has not integer format?
					layoutKey = Integer.valueOf(attributeValue);
					
					String clazzName = parser.nextText();
					
					// Insert new class name 
					layoutClazzNameMap.put(layoutKey, clazzName);
				}
			}
		}
	}
}
