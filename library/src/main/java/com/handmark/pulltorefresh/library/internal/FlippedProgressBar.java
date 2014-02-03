package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;
/**
 * Right to left ProgressBar
 * @author Wonjun Kim
 *
 */
public class FlippedProgressBar extends ProgressBar {
	public FlippedProgressBar(Context context) {
		super(context);
	}

	public FlippedProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FlippedProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		float centerX = this.getWidth() / 2.0f;
		float centerY = this.getHeight() / 2.0f;
		canvas.scale(-1, 1, centerX /* center of x */, centerY /* center of y */);
		super.onDraw(canvas);
		canvas.restore();
	}
	
}
