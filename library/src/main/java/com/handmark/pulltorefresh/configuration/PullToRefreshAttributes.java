package com.handmark.pulltorefresh.configuration;

import android.content.Context;
import android.content.res.TypedArray;

public class PullToRefreshAttributes {

	private Object mMode;

	public PullToRefreshAttributes(Context context, TypedArray a) {
//		if (a.hasValue(R.styleable.PullToRefresh_ptrMode)) {
//			mMode = Mode.mapIntToValue(a.getInteger(R.styleable.PullToRefresh_ptrMode, 0));
//		}
//
//		if (a.hasValue(R.styleable.PullToRefresh_ptrAnimationStyle)) {
//			int loadingLayoutCode = a.getInteger(R.styleable.PullToRefresh_ptrAnimationStyle, 0);
//			PullToRefreshConfiguration configuration = PullToRefreshConfigurationFactory.createConfiguration(context);
//			mLoadingLayoutClazz = configuration.getLoadingLayout(loadingLayoutCode);
//			
//		}
//
//		// Refreshable View
//		// By passing the attrs, we can add ListView/GridView params via XML
//		mRefreshableView = createRefreshableView(context, attrs);
//		addRefreshableView(context, mRefreshableView);
//
//		// We need to create now layouts now
//		mHeaderLayout = createLoadingLayout(context, Mode.PULL_FROM_START, a);
//		mFooterLayout = createLoadingLayout(context, Mode.PULL_FROM_END, a);
//
//		/**
//		 * Styleables from XML
//		 */
//		if (a.hasValue(R.styleable.PullToRefresh_ptrRefreshableViewBackground)) {
//			Drawable background = a.getDrawable(R.styleable.PullToRefresh_ptrRefreshableViewBackground);
//			if (null != background) {
//				mRefreshableView.setBackgroundDrawable(background);
//			}
//		} else if (a.hasValue(R.styleable.PullToRefresh_ptrAdapterViewBackground)) {
//			Utils.warnDeprecation("ptrAdapterViewBackground", "ptrRefreshableViewBackground");
//			Drawable background = a.getDrawable(R.styleable.PullToRefresh_ptrAdapterViewBackground);
//			if (null != background) {
//				mRefreshableView.setBackgroundDrawable(background);
//			}
//		}
//
//		if (a.hasValue(R.styleable.PullToRefresh_ptrOverScroll)) {
//			mOverScrollEnabled = a.getBoolean(R.styleable.PullToRefresh_ptrOverScroll, true);
//		}
//
//		if (a.hasValue(R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled)) {
//			mScrollingWhileRefreshingEnabled = a.getBoolean(
//					R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled, false);
//		}
//
//		// Set Show Indicator to the XML value, or default value
//		mShowIndicator = a.getBoolean(R.styleable.PullToRefresh_ptrShowIndicator, !isPullToRefreshOverScrollEnabled());		
	}
}
