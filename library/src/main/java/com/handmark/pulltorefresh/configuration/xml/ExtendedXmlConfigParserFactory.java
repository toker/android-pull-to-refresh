package com.handmark.pulltorefresh.configuration.xml;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.handmark.pulltorefresh.library.internal.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
/**
 * 
 * @author NBP
 *
 */
class ExtendedConfigXmlParserFactory {
	
	private static final String LOG_TAG = ExtendedConfigXmlParserFactory.class.getSimpleName();
	private static final String XML_PATH_IN_ASSETS = "pulltorefresh.xml";
	private static final String DEFAULT_ENCODING_TYPE = "utf-8";
	/**
	 * 
	 * @param context
	 * @return
	 */
	public static XmlPullParser createParser(Context context) {
		AssetManager am = context.getAssets();
		XmlPullParser parser = null;
		try {
			InputStream is = am.open(XML_PATH_IN_ASSETS);
			parser = XmlPullParserFactory.newInstance().newPullParser();
			parser.setInput(is, DEFAULT_ENCODING_TYPE);
			Log.d(LOG_TAG, "parser : " + parser.toString());
			
		} catch (XmlPullParserException e) {
			Log.w(LOG_TAG, "The error occurs below when generating parser.\n"+e.getMessage());
			// explicitly assign null
			parser = null;
		} catch (IOException e) {
			Log.w(LOG_TAG, "Loading "+XML_PATH_IN_ASSETS+" file has failed.\n"+e.getMessage());
			// explicitly assign null
			parser = null;
		}
		
		// NOTE: this code makes a fatal error. so omit the code.
		// am.close();
		
		return parser;
	}
}
