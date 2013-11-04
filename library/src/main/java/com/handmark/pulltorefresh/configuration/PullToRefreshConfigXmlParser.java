package com.handmark.pulltorefresh.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.handmark.pulltorefresh.library.internal.IndicatorLayout;
import com.handmark.pulltorefresh.library.internal.DefaultIndicatorLayout;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;

import android.util.Log;

final class PullToRefreshConfigXmlParser extends XmlPullNodeParser {
	private static final String TAG = PullToRefreshConfigXmlParser.class.getName();
	
	
	protected static class Result {
		// TODO: check those classes' scope again.
		public final Map<Integer, Class<? extends LoadingLayout>> loadingLayoutsMap = new HashMap<Integer, Class<? extends LoadingLayout>>();
		public final Map<Integer, Class<? extends IndicatorLayout>> indicatorLayoutsMap = new HashMap<Integer, Class<? extends IndicatorLayout>>();
		
	} 
	
	private Result result = new Result();
	
	public PullToRefreshConfigXmlParser(XmlPullParserUtilsWrapper parser) {
		super(parser);
	}


	private XmlPullNode init() {

		XmlPullNode root = new XmlPullNode("PullToRefresh");
		XmlPullNode loadingLayouts = new XmlPullNode("LoadingLayouts");
		XmlPullNode indicatorLayouts = new XmlPullNode("IndicatorLayouts");
		XmlPullNode loadingLayout = new XmlPullNode("layout",
				new XmlPullNode.XmlPullNodeCallback() {

					@Override
					public void process(XmlPullParser parser) {
						int attributesCount = parser.getAttributeCount();
						String attributeName, attributeValue;
						int layoutKey;
						
						for (int i = 0; i < attributesCount; ++i) {
							attributeName = parser.getAttributeName(i);
							attributeValue = parser.getAttributeValue(i);
							
							if ( "value".equals(attributeName)) {
								// TODO: what if attributeValue has not integer format?
								layoutKey = Integer.valueOf(attributeValue);
								
								// TODO: MUST BE refactored!
								String clazzName = "";
								try {
									clazzName = parser.nextText();
								} catch (XmlPullParserException e1) {
									
								} catch (IOException e1) {
								}
					
								try {
									// add loading layout clazz
									Class<? extends LoadingLayout> clazz = (Class<? extends LoadingLayout>) Class.forName(clazzName);
									
									result.loadingLayoutsMap.put(layoutKey, clazz);
								} catch (ClassNotFoundException e) {
									Log.e(TAG, "Class " + clazzName + " has not found. So that has been passed over from loading layout list." );
								}
							}
							
							
						}
					}
				});

		XmlPullNode indicatorLayout = new XmlPullNode("layout",
				new XmlPullNode.XmlPullNodeCallback() {

					@Override
					public void process(XmlPullParser parser) {
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
								
								// TODO: MUST BE refactored!
								String clazzName = "";
								try {
									clazzName = parser.nextText();
								} catch (XmlPullParserException e1) {
									
								} catch (IOException e1) {
								}
					
								try {
									// add indicator layout clazz
									Class<? extends IndicatorLayout> clazz = (Class<? extends IndicatorLayout>) Class.forName(clazzName);
									
									result.indicatorLayoutsMap.put(layoutKey, clazz);
								} catch (ClassNotFoundException e) {
									Log.e(TAG, "Class " + clazzName + " has not found. So that has been passed over from indicator layout list." );
								}
							}
							
							
						}						
					}
				});

		root.addChildNode(loadingLayouts, 1);
		root.addChildNode(indicatorLayouts, 1);
		loadingLayouts.addChildNode(loadingLayout);
		indicatorLayouts.addChildNode(indicatorLayout);
		return root;
	}

	public Result parseAndGetResult() throws XmlPullParserException, IOException {
		parse();
		return result;
	}
	
	@Override
	protected XmlPullNode generateRootNode() {
		return init();
	}

}
