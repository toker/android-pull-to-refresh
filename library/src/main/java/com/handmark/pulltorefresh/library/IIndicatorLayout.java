package com.handmark.pulltorefresh.library;

import com.handmark.pulltorefresh.library.internal.IndicatorLayout;

/**
 * Indicator layout which react pulling or releasing of Pull To Refresh
 * <br />
 * NOTE : To make new Indicator layout, You have to override {@link IndicatorLayout} instead of IIndicatorLayout. 
 * <br /> Because {@link PullToRefreshAdapterViewBase} uses indicator layouts as a kind of View component. {@link IndicatorLayout} is a descendant class of {@code View}.
 * 
 * @author Wonjun Kim
 */
public interface IIndicatorLayout {
	/**
	 * Show the Indicator Layout 
	 */
	public void show();
	/**
	 * Hide the Indicator Layout 
	 */
	public void hide();
	/**
	 * Check whether this indicator layout is being shown or not
	 * @return true if the indicator layout is visible now 
	 */
	public boolean isVisible();
	/**
	 * Make a Action when "PullToRefresh" event has been fired
	 */
	public void pullToRefresh();
	/**
	 * Make a Action when "ReleaseToRefresh" event has been fired
	 */
	public void releaseToRefresh();
}
