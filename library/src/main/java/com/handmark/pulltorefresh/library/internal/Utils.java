package com.handmark.pulltorefresh.library.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import android.util.AttributeSet;
import android.util.Log;

public class Utils {

	static final String LOG_TAG = "PullToRefresh";
	/**
	 * Android namespace for Android attributes'-related util methods
	 */
	static final String ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android";
	/**
	 *  Invalid android attribute (temporarily defined, to check android attributes' values)
	 */
	static final int INVALID_INT_VALUE = -1;
	/**
	 * Delegate warn logs at where some deprecated method has been called
	 * @param depreacted Deprecated method name
	 * @param replacement Method name to be able to switch
	 */
	public static void warnDeprecation(String depreacted, String replacement) {
		Log.w(LOG_TAG, "You're using the deprecated " + depreacted + " attr, please switch over to " + replacement);
	}
	/**
	 * Delegate error logs
	 * @param message
	 */
	public static void error(String message) {
		Log.e(LOG_TAG, message);
	} 
	/**
	 * Try to close {@code InputStream} without any exceptions, and ignore if some exception occurs
	 * @param is {@code InputStream} instance to close
	 */
	public static void closeSilently(InputStream is) {
		// If the instance is null, do nothing and return
		if (is == null) {
			return;
		}
		
		try {
			// try to close
			is.close();
		} catch (IOException e) {
			// try to close once more
			try {
				is.close();
			} catch (IOException e1) {
				// do nothing
			}
		}
	}
	/**
	 * Try to close {@code Reader} without any exceptions, and ignore if some exception occurs
	 * @param br {@code Reader} instance to close
	 */
	public static void closeSilently(Reader br) {
		// If the instance is null, do nothing and return
		if (br == null) {
			return;
		}
		try {
			// try to close
			br.close();
		} catch (IOException e) {
			try {
				// try to close once more
				br.close();
			} catch (IOException e1) {
				// do nothing
			}
		}		
	}
	/**
	 * Check whether android {@code attribute} exists and is set in {@code attributeSet} 
	 * @param attrs {@code AttributeSet} where the {@code attribute} is included (if that is set)
	 * @param namespace Namespace where the {@code attribute} is defined 
	 * @return true if the {@code attribute} exists
	 */
	public static boolean existAttributeIntValue(AttributeSet attrs, String attribute) {
		return existAttributeIntValue(attrs, ANDROID_NAMESPACE, attribute);
	}
	/**
	 * Check whether android {@code attribute} exists and is set in {@code attributeSet} 
	 * @param attrs {@code AttributeSet} where the {@code attribute} is included (if that is set)
	 * @param namespace Namespace where the {@code attribute} is defined 
	 * @param attribute Attribute to be checked
	 * @return true if the {@code attribute} exists
	 */
	public static boolean existAttributeIntValue(AttributeSet attrs, String namespace, String attribute) {
		return existAttributeIntValue(attrs, namespace, attribute, INVALID_INT_VALUE);
	}
	/**
	 * Check whether android {@code attribute} exists and is set in {@code attributeSet} 
	 * @param attrs {@code AttributeSet} where the {@code attribute} is included (if that is set)
	 * @param namespace Namespace where the {@code attribute} is defined 
	 * @param attribute Attribute to be checked
	 * @param invalidValue The flag to check that the {@code attribute} is set 
	 * @return true if the {@code attribute} exists
	 */
	public static boolean existAttributeIntValue(AttributeSet attrs, String namespace, String attribute, int invalidValue) {
		Assert.notNull(attrs, "attrs");
		Assert.notNull(attrs, "namespace");
		Assert.notNull(attrs, "attribute");
		
		boolean isExist = true;
		int value = attrs.getAttributeIntValue(namespace, attribute, invalidValue);
		if ( value == invalidValue ) {
			isExist = false;
		}
		return isExist;
	}
}
