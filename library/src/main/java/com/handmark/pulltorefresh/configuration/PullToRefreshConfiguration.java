package com.handmark.pulltorefresh.configuration;

import com.handmark.pulltorefresh.library.internal.DefaultIndicatorLayout;
import com.handmark.pulltorefresh.library.internal.IndicatorLayout;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;

import android.content.Context;
@Deprecated
public abstract class PullToRefreshConfiguration { //<LT, IT> LT  : Loading Layout Code, IT : Indicator Layout Code
	
	public PullToRefreshConfiguration(Context context) {//, AttributesContainer<AT> container
		initConfiguration(context);
	}
	
	protected abstract void initConfiguration(Context context);
	
	public abstract Class<? extends LoadingLayout> getLoadingLayout(Integer layoutCode);
	public abstract Class<? extends IndicatorLayout> getIndicatorLayout(Integer layoutCode);

}
