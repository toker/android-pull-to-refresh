package com.handmark.pulltorefresh.library;
/**
 * Interface for communations with {@code PullToRefreshBase} 
 * @author Wonjun Kim
 *
 */
public interface IPullToRefreshConsumer {
	void reset();
	void refreshing();
	void releaseToRefresh();
	void pullToRefresh();
	void onPull(float scale);
}
