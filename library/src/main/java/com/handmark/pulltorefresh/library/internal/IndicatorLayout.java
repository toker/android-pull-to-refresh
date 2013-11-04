package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public abstract class IndicatorLayout extends FrameLayout implements
		IIndicatorLayout {
	public IndicatorLayout(Context context) {
		super(context);
	}

	public IndicatorLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public IndicatorLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public abstract LayoutParams createApplicableHeaderLayoutParams();
	public abstract LayoutParams createApplicableFooterLayoutParams();

}
