package com.handmark.pulltorefresh.library;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.internal.FlipLoadingLayout;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;
@Deprecated
class LoadingLayoutFactory implements ILoadingLayoutFactory {

	private final static LoadingLayoutFactory instance = new LoadingLayoutFactory();
	private static final String LOG_TAG = LoadingLayoutFactory.class
			.getSimpleName();

	/**
	 * 
	 * @return
	 */
	public static ILoadingLayoutFactory getInstance() {
		return instance;
	}
	
	
	public Class<? extends LoadingLayout> createLoadingLayoutClazz(
			String clazzName) {
		Class<? extends LoadingLayout> loadingLayoutClazz = null;
		try {
			// FIXME unchecked
			loadingLayoutClazz = (Class<LoadingLayout>) Class.forName(clazzName);

		} catch (ClassNotFoundException e) {
			Log.e(LOG_TAG,
					"Selected loading layout class has not been founded.");
			loadingLayoutClazz = DefaultLoadingLayoutFactory.getInstance()
					.createLoadingLayoutClazz(clazzName);
		}

		return loadingLayoutClazz;
	}

	/**
	 * 
	 * @param context
	 * @param mode
	 * @param attrs
	 * @comment Need to be refactored!
	 * @return
	 */
	public LoadingLayout createLoadingLayout(
			Class<? extends LoadingLayout> clazz, Context context, Mode mode,
			Orientation orientation, TypedArray attrs) {
		LoadingLayout layout = null;

		try {
			Constructor<? extends LoadingLayout> constructor = clazz
					.getConstructor(Context.class, Mode.class,
							Orientation.class, TypedArray.class);
			layout = (LoadingLayout) constructor.newInstance(context, mode,
					orientation, attrs);

		} catch (IllegalArgumentException e) {
			Log.e(LOG_TAG, "loading layout has failed while creating. " + e);
		} catch (InvocationTargetException e) {
			Log.e(LOG_TAG, "loading layout has failed while creating. " + e);
		} catch (SecurityException e) {
			Log.e(LOG_TAG, "loading layout has failed while creating. " + e);
		} catch (NoSuchMethodException e) {
			Log.e(LOG_TAG, "loading layout has failed while creating. " + e);
		} catch (InstantiationException e) {
			Log.e(LOG_TAG, "loading layout has failed while creating. " + e);
		} catch (IllegalAccessException e) {
			Log.e(LOG_TAG, "loading layout has failed while creating. " + e);
		} catch (NullPointerException e) {
			Log.e(LOG_TAG, "loading layout has failed while creating. " + e);
		}

		if (layout == null) {
			layout = DefaultLoadingLayoutFactory.getInstance()
					.createLoadingLayout(clazz, context, mode, orientation,
							attrs);
		}

		// old
		// LoadingLayout layout =
		// mLoadingAnimationStyle.createLoadingLayout(context, mode,
		// getPullToRefreshScrollDirection(), attrs);

		layout.setVisibility(View.INVISIBLE);
		return layout;
	}

	private static class DefaultLoadingLayoutFactory implements
			ILoadingLayoutFactory {

		private static final DefaultLoadingLayoutFactory instance = new DefaultLoadingLayoutFactory();

		public static ILoadingLayoutFactory getInstance() {
			return instance;
		}

		@Override
		public Class<? extends LoadingLayout> createLoadingLayoutClazz(
				String clazzName) {
			return FlipLoadingLayout.class;
		}

		@Override
		public LoadingLayout createLoadingLayout(
				Class<? extends LoadingLayout> clazz, Context context,
				Mode mode, Orientation orientation, TypedArray attrs) {

			return new FlipLoadingLayout(context, mode, orientation, attrs);
		}

	}

}
