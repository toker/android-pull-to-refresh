package com.handmark.pulltorefresh.configuration.xml;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.simpleframework.xml.core.Persister;

import com.handmark.pulltorefresh.library.R;
import com.handmark.pulltorefresh.library.internal.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

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
		InputStream is = resources.openRawResource(XML_PATH_ID);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		// parser the xml
		try {
			node = new Persister().read(PullToRefreshNode.class, br);
			initialized = true;
		} catch (Exception e) {
			Utils.error("It has failed to parse the xmlpullparser xml.\n " + e.getMessage());
		}
		
		Utils.closeSilently(br);
		Utils.closeSilently(is);
	}
	
	public String getLoadingLayoutClazzName(Integer layoutCode) {
		assertInitialized();
		return node.loadingLayoutsNode.getLayoutClazzName(layoutCode);
	}

	public String getIndicatorLayoutClazzName(Integer layoutCode) {
		assertInitialized();
		return node.indicatorLayoutsNode.getLayoutClazzName(layoutCode);
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
