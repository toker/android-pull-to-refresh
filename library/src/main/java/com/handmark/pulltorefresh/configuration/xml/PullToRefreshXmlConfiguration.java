package com.handmark.pulltorefresh.configuration.xml;


import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.handmark.pulltorefresh.library.R;
import com.handmark.pulltorefresh.library.internal.Utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * 
 * @author NBP
 *
 */
public class PullToRefreshXmlConfiguration {

	private static class InstanceHolder {
		private final static PullToRefreshXmlConfiguration instance = new PullToRefreshXmlConfiguration();
		
		private static PullToRefreshXmlConfiguration getInstance() {
			return instance;
		}
	} 
	
	private static final int XML_PATH_ID = R.xml.pulltorefresh;
	private PullToRefreshNode node = null; 
	private boolean initialized = false;
	
	private PullToRefreshXmlConfiguration() {}
	
	public static PullToRefreshXmlConfiguration getInstance() {
		return InstanceHolder.getInstance();
	}
	
	public void init(Context context) {
		// get resources
		Resources resources = context.getResources();
		// read the file
		XmlPullParser parser = resources.getXml(XML_PATH_ID);
		// parser the xml
		try {
			XmlPullParserWrapper wrapper = new XmlPullParserWrapper(parser);
		
			node = new PullToRefreshConfigXmlParser(wrapper).parse();
		} catch (Exception e) {
			Utils.error("It has failed to parse the xmlpullparser xml.\n " + e.getMessage());
		}
		
		// Intialization can be done whether reading XML has failed or not! 
		initialized = true;
	}
	
	public String getLoadingLayoutClazzName(Integer layoutCode) {
		assertInitialized();
		if ( isNodeNull() ) {
			return null;
		}
		return node.loadingLayoutsNode.getLayoutClazzName(layoutCode);
	}

	public String getIndicatorLayoutClazzName(Integer layoutCode) {
		assertInitialized();
		if ( isNodeNull() ) {
			return null;
		}
		return node.indicatorLayoutsNode.getLayoutClazzName(layoutCode);
	}
	
	private boolean isNodeNull() {
		return node == null;
	}

	private boolean notInitialized() {
		return !initialized;
	}
	
	private void assertInitialized() {
		if ( notInitialized() ) {
			throw new IllegalStateException(PullToRefreshXmlConfiguration.class.getName()+" has not initialized. Call init() method first.");
		}
	}
}
