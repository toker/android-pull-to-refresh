package com.handmark.pulltorefresh.library.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import android.util.Log;

public class Utils {

	static final String LOG_TAG = "PullToRefresh";

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
}
