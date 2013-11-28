package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;

interface ILoadingLayoutFactory {
	Class<? extends LoadingLayout> createLoadingLayoutClazz(String clazzName);

	LoadingLayout createLoadingLayout(Class<? extends LoadingLayout> clazz, Context context, Mode mode,
			Orientation orientation, TypedArray attrs);

}
