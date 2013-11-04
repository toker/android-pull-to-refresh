package com.handmark.pulltorefresh.library;

import android.content.Context;

import com.handmark.pulltorefresh.library.internal.IndicatorLayout;
import com.handmark.pulltorefresh.library.internal.DefaultIndicatorLayout;

public class IndicatorLayoutFactory {

	public static IndicatorLayout createIndicatorFactory(Context context, PullToRefreshBase.Mode mode) {
		return new DefaultIndicatorLayout(context, mode);
	}
}
