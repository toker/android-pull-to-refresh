package com.handmark.pulltorefresh.configuration;

import com.handmark.pulltorefresh.library.internal.IndicatorLayout;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;

import android.content.Context;

public abstract class PullToRefreshConfiguration<LT, IT> {
	
	public PullToRefreshConfiguration(Context context) {//, AttributesContainer<AT> container
		initConfiguration(context);
	}
	
	protected abstract void initConfiguration(Context context);
	
	public abstract Class<? extends LoadingLayout> getLoadingLayout(LT layoutCode);
	public abstract Class<? extends IndicatorLayout> getIndicatorLayout(IT layoutCode);

}
