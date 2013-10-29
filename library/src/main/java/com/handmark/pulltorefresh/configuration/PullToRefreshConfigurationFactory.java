package com.handmark.pulltorefresh.configuration;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class PullToRefreshConfigurationFactory {

	/**
	 * watch out the warning annotation below.
	 */
	@SuppressWarnings("rawtypes")
	public static PullToRefreshConfiguration createConfiguration(Context context) {// ,AttributesContainer<TypedArray> container
		return new DefaultPullToRefreshConfiguration(context);
	}

}
