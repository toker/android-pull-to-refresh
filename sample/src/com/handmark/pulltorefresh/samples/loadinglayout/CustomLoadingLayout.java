/*
 * Copyright (C) 2014 Naver Business Platform Corp.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.handmark.pulltorefresh.samples.loadinglayout;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.internal.FlipLoadingLayout;

public class CustomLoadingLayout extends FlipLoadingLayout {

	private static final float MAX_SCALE_SIZE = 3f;

	public CustomLoadingLayout(Context context, Mode mode, Orientation scrollDirection, TypedArray attrs) {
		super(context, mode, scrollDirection, attrs);
	}

	@Override
	protected int getDefaultDrawableResId() {
		// Use the default arrow icon from android R
		return R.drawable.arrow_down_float;
	}

	@Override
	protected void onPullImpl(float scaleOfLayout) {
		super.onPullImpl(scaleOfLayout);
		int color = getHeaderTextColorBy(scaleOfLayout);
		mHeaderText.setTextColor(color);
	}

	private int getHeaderTextColorBy(float scaleOfLayout) {
		// Limit the scale size
		if ( scaleOfLayout > MAX_SCALE_SIZE ) {
			scaleOfLayout = MAX_SCALE_SIZE;
		}
		// Create the color code
		int alpha = (int) ((scaleOfLayout / MAX_SCALE_SIZE) * 255);
		int color = 0x00FFFFFF /* Transparent & White */ | (alpha << 24);
		return color;
	}

	@Override
	protected String loadPullLabel(Context context, TypedArray attrs, Mode mode) {
		return "Custom pull label";
	}

	@Override
	protected String loadRefreshingLabel(Context context, TypedArray attrs, Mode mode) {
		return "Custom refresh label";
	}

	@Override
	protected String loadReleaseLabel(Context context, TypedArray attrs, Mode mode) {
		return "Custom release label";
	}

}
