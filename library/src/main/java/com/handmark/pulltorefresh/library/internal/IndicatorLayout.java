package com.handmark.pulltorefresh.library.internal;

import com.handmark.pulltorefresh.library.internal.IndicatorLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
/**
 * @see IIndicatorLayout
 * @author Wonjun Kim
 */
public abstract class IndicatorLayout extends FrameLayout implements IIndicatorLayout {
	
	public IndicatorLayout(Context context) {
		super(context);
	}

	public IndicatorLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public IndicatorLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	/**
	 * Create a specific {@code LayoutParams}.<br /> 
	 * Pull To Refresh will add this layout with applying this {@code LayoutParams} to the layout     
	 * @return {@code LayoutParams} which is applied if this indicator layout is a header of Pull To Refresh
	 */
	public abstract LayoutParams createApplicableHeaderLayoutParams();
	/**
	 * Create a specific {@code LayoutParams}.<br /> 
	 * Pull To Refresh will add this layout with applying this {@code LayoutParams} to the layout     
	 * @return {@code LayoutParams} which is applied if this indicator layout is a footer of Pull To Refresh 
	 */
	public abstract LayoutParams createApplicableFooterLayoutParams();

}
