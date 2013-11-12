package com.handmark.pulltorefresh.library;

import java.lang.reflect.Constructor;

import java.lang.reflect.InvocationTargetException;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;

import com.handmark.pulltorefresh.configuration.xml.PullToRefreshXmlConfiguration;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.internal.FlipLoadingLayout;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;
/**
 * 
 * @author NBP
 *
 */
class LoadingLayoutFactory {

	private static final String LOG_TAG = LoadingLayoutFactory.class
			.getSimpleName();
	
	/**
	 * 
	 * @param layoutCode
	 * @return
	 */
	public static Class<? extends LoadingLayout> createLoadingLayoutClazz(Integer layoutCode) {
		String clazzName = PullToRefreshXmlConfiguration.getInstance().getLoadingLayoutClazzName(layoutCode);
		return createLoadingLayoutClazz(clazzName);
	}
	/**
	 * 
	 * @param clazzName
	 * @return
	 */
	public static Class<? extends LoadingLayout> createLoadingLayoutClazz(
			String clazzName) {
		Class<? extends LoadingLayout> loadingLayoutClazz = null;
		if ( clazzName == null ) {
			loadingLayoutClazz = DefaultLoadingLayoutFactory.createLoadingLayoutClazz(clazzName);
			return loadingLayoutClazz;
		}
		
		try {
			// FIXME unchecked
			loadingLayoutClazz = (Class<LoadingLayout>) Class.forName(clazzName);

		} catch (ClassNotFoundException e) {
			Log.e(LOG_TAG,
					"Selected loading layout class has not been found.");
			loadingLayoutClazz = DefaultLoadingLayoutFactory.createLoadingLayoutClazz(clazzName);
		} 

		return loadingLayoutClazz;
	}

	public static LoadingLayout createLoadingLayout(Integer layoutCode, Context context, Mode mode,
			Orientation orientation, TypedArray attrs) {
		Class<? extends LoadingLayout> clazz = createLoadingLayoutClazz(layoutCode);
		return createLoadingLayout(clazz, context, mode, orientation, attrs);
	}
	
	/**
	 * 
	 * @param context
	 * @param mode
	 * @param attrs
	 * @comment Need to be refactored!
	 * @return
	 */
	public static LoadingLayout createLoadingLayout(
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
			layout = DefaultLoadingLayoutFactory.createLoadingLayout(clazz, context, mode, orientation, attrs);
		}

		layout.setVisibility(View.INVISIBLE);
		return layout;
	}
	
	/**
	 * 
	 * @author NBP
	 *
	 */
	private static class DefaultLoadingLayoutFactory {

		public static Class<? extends LoadingLayout> createLoadingLayoutClazz(
				String clazzName) {
			return FlipLoadingLayout.class;
		}

		public static LoadingLayout createLoadingLayout(
				Class<? extends LoadingLayout> clazz, Context context,
				Mode mode, Orientation orientation, TypedArray attrs) {

			return new FlipLoadingLayout(context, mode, orientation, attrs);
		}

	}

}
