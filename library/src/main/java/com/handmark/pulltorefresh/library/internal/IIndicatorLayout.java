package com.handmark.pulltorefresh.library.internal;

public interface IIndicatorLayout {
	public void show();
	public void hide();
	public boolean isVisible();
	public void pullToRefresh();
	public void releaseToRefresh();
	
}
