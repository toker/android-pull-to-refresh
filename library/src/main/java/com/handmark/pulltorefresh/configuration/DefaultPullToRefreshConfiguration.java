package com.handmark.pulltorefresh.configuration;

import java.io.IOException;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.handmark.pulltorefresh.configuration.PullToRefreshConfigXmlParser.Result;
import com.handmark.pulltorefresh.configuration.XmlPullParserUtilsWrapper.DocumentState;
import com.handmark.pulltorefresh.library.R;
import com.handmark.pulltorefresh.library.internal.DefaultIndicatorLayout;
import com.handmark.pulltorefresh.library.internal.IndicatorLayout;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;

public class DefaultPullToRefreshConfiguration extends PullToRefreshConfiguration { //<Integer, Integer>

	private Map<Integer, Class<? extends IndicatorLayout>> indicatorLayoutsMap;
	private Map<Integer, Class<? extends LoadingLayout>> loadingLayoutsMap;

	public DefaultPullToRefreshConfiguration(Context context) {// , AttributesContainer<TypedArray> container
		super(context);//, container
	}

	@Override
	protected void initConfiguration(Context context) {//, AttributesContainer<TypedArray> container
//		TypedArray attributeSet = container.getAttributes();
		
		Resources resources = context.getResources();
		extractLayoutsFromXml(resources);
	}
	
	private void extractLayoutsFromXml(Resources resources) {
		XmlResourceParser parser = resources.getXml(R.xml.pulltorefresh);
		XmlPullParserUtilsWrapper wrapper = new XmlPullParserUtilsWrapper(parser);
		Log.d("extract", "parser" + parser.toString());
		try {
			
			PullToRefreshConfigXmlParser pullToRefreshParser = new PullToRefreshConfigXmlParser(wrapper);
			PullToRefreshConfigXmlParser.Result result = pullToRefreshParser.parseAndGetResult();
			convertResultToProperties(result);
		
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	private void convertResultToProperties(Result result) {
		loadingLayoutsMap = result.loadingLayoutsMap;
		indicatorLayoutsMap = result.indicatorLayoutsMap;
		
		if ( loadingLayoutsMap == null ) {
//			loadingLayoutsMap = 
		}
		
		if ( indicatorLayoutsMap == null ) {
			
		}
	}

	@Override
	public Class<? extends LoadingLayout> getLoadingLayout(Integer layoutCode) {
		Class<? extends LoadingLayout> clazz = loadingLayoutsMap.get(layoutCode);
		return clazz;
	}
	
	@Override
	public Class<? extends IndicatorLayout> getIndicatorLayout(Integer layoutCode) {
		return indicatorLayoutsMap.get(layoutCode);
	}

}
