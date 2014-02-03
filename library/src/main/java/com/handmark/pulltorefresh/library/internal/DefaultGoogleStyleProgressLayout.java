package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.handmark.pulltorefresh.library.GoogleStyleProgressLayout;
import com.handmark.pulltorefresh.library.R;
/**
 * Specific implemented layout of Google style progress layout<br />
 * NOTE : This class doesn't have abstract default class because of no extend points in this class. 
 * @author Wonjun Kim
 *
 */
public class DefaultGoogleStyleProgressLayout extends GoogleStyleProgressLayout {

	private PullingProgressLayout mActionBarHeaderPullingProgressLayout;
	private ProgressBar mActionBarHeaderRefreshingProgressBar;

	public DefaultGoogleStyleProgressLayout(Context context, TypedArray attrs) {
		super(context, attrs);
		initInflate(context, R.layout.pull_to_refresh_progress_google_style);
		initProperties();
		reset();
	}
	/**
	 * Inflate layout by {@code inflateId}
	 * @param context
	 * @param inflateId inflate id value like {@code R.layout...} 
	 */
	private void initInflate(Context context, int inflateId) {
		LayoutInflater.from(context).inflate(inflateId, this);		
	}
	/**
	 * Assign UI Components to fields
	 */
	private void initProperties() {
		mActionBarHeaderPullingProgressLayout = (PullingProgressLayout) findViewById(R.id.pulling_progress);
		mActionBarHeaderRefreshingProgressBar = (ProgressBar) findViewById(R.id.refreshing_progress);
	}
	/**
	 * Show pulling bar and hide refreshing bar
	 */
	@Override
	public void reset() {
		mActionBarHeaderPullingProgressLayout.setVisibility(View.VISIBLE);
		mActionBarHeaderRefreshingProgressBar.setVisibility(View.INVISIBLE);
		mActionBarHeaderPullingProgressLayout.setPercent(0);
	}
	/**
	 * Hide pulling bar and show refreshing bar
	 */
	@Override
	public void refreshing() {
		mActionBarHeaderPullingProgressLayout.setVisibility(View.INVISIBLE);
		mActionBarHeaderRefreshingProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void releaseToRefresh() {
	}

	@Override
	public void pullToRefresh() {
	}
	/**
	 * Set progress of pulling bar
	 */
	@Override
	public void onPull(float scale) {
		int percent = (int) (scale * 100);
		mActionBarHeaderPullingProgressLayout.setPercent(percent);
	}
}
