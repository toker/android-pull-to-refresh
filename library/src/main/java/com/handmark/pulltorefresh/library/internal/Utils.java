package com.handmark.pulltorefresh.library.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import android.util.AttributeSet;
import android.util.Log;

public class Utils {

	static final String LOG_TAG = "PullToRefresh";
	static final String ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android";
	static final int INVALID_INT_VALUE = -1;
	public static void warnDeprecation(String depreacted, String replacement) {
		Log.w(LOG_TAG, "You're using the deprecated " + depreacted + " attr, please switch over to " + replacement);
	}
	
	public static void error(String message) {
		Log.e(LOG_TAG, message);
	} 
	/**
	 * 
	 * @param is
	 */
	public static void closeSilently(InputStream is) {
		try {
			is.close();
		} catch (IOException e) {
			try {
				is.close();
			} catch (IOException e1) {
			}
		}
	}
	/**
	 * 
	 * @param br
	 */
	public static void closeSilently(Reader br) {
		try {
			br.close();
		} catch (IOException e) {
			try {
				br.close();
			} catch (IOException e1) {
			}
		}		
	}

	public static boolean existAttributeIntValue(AttributeSet attrs, String attribute) {
		return existAttributeIntValue(attrs, ANDROID_NAMESPACE, attribute);
	}
	
	public static boolean existAttributeIntValue(AttributeSet attrs, String namespace, String attribute) {
		return existAttributeIntValue(attrs, namespace, attribute, INVALID_INT_VALUE);
	}
	/**
	 * 
	 * @param attrs
	 * @param namespace
	 * @param attribute
	 * @param invalidValue
	 * @return
	 */
	public static boolean existAttributeIntValue(AttributeSet attrs, String namespace, String attribute, int invalidValue) {
		boolean isExist = true;
		int value = attrs.getAttributeIntValue(namespace, attribute, invalidValue);
		if ( value == invalidValue ) {
			isExist = false;
		}
		return isExist;
	}
}
