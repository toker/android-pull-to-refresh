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
 * Factory which creates loading layouts 
 * <br />Loading layouts must be listed in pulltorefresh.xml as "PullToRefresh/LoadingLayouts/layout" nodes
 * @author Wonjun Kim
 */
class LoadingLayoutFactory {

	private static final String LOG_TAG = LoadingLayoutFactory.class
			.getSimpleName();
	/**
	 * Create the class token matched by <b>{@code layoutCode}</b>
	 * @param layoutCode Loading layout code, which must be defined in pulltorefresh.xml 
	 * @return Class token which is matched by {@code layoutCode}, or the class token of {@code FlipLoadingLayout} instance if not
	 */
	public static Class<? extends LoadingLayout> createLoadingLayoutClazzByLayoutCode(String layoutCode) {
		String clazzName = PullToRefreshXmlConfiguration.getInstance().getLoadingLayoutClazzName(layoutCode);
		return createLoadingLayoutClazz(clazzName);
	}
	/**
	 * Create a {@code LoadingLayout} instance matched by <b>{@code clazz} token</b> 
	 * @param layoutCode Loading layout code, which must be defined in pulltorefresh.xml
	 * @param context 
	 * @param mode 
	 * @return {@code LoadingLayout} instance if the class matched by {@code layoutCode} exists, or {@code FlipLoadingLayout} instance if not  
	 */
	public static Class<? extends LoadingLayout> createLoadingLayoutClazz(
			String clazzName) {
		Class<? extends LoadingLayout> loadingLayoutClazz = null;
		if ( clazzName == null ) {
			loadingLayoutClazz = FlipLoadingLayoutFactory.createLoadingLayoutClazz(clazzName);
			return loadingLayoutClazz;
		}
		
		try {
			// FIXME unchecked
			loadingLayoutClazz = (Class<LoadingLayout>) Class.forName(clazzName);

		} catch (ClassNotFoundException e) {
			Log.e(LOG_TAG,
					"Selected loading layout class has not been found.");
			loadingLayoutClazz = FlipLoadingLayoutFactory.createLoadingLayoutClazz(clazzName);
		} 

		return loadingLayoutClazz;
	}
	/**
	 * Create a {@code LoadingLayout} instance matched by <b>{@code layoutCode}</b> 
	 * @param layoutCode Loading layout code, which must be defined in pulltorefresh.xml
	 * @param context 
	 * @param mode 
	 * @return {@code LoadingLayout} instance if the class matched by {@code layoutCode} exists, or {@code FlipLoadingLayout} instance if not  
	 */
	public static LoadingLayout createLoadingLayout(String layoutCode, Context context, Mode mode,
			Orientation orientation, TypedArray attrs) {
		Class<? extends LoadingLayout> clazz = createLoadingLayoutClazz(layoutCode);
		return createLoadingLayout(clazz, context, mode, orientation, attrs);
	}
	/**
	 * Create a {@code LoadingLayout} instance matched by <b>{@code clazz} token</b> 
	 * @param layoutCode Loading layout code, which must be defined in pulltorefresh.xml
	 * @param context 
	 * @param mode 
	 * @return {@code LoadingLayout} instance if the class matched by {@code layoutCode} exists, or {@code FlipLoadingLayout} instance if not  
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
			layout = FlipLoadingLayoutFactory.createLoadingLayout(clazz, context, mode, orientation, attrs);
		}

		layout.setVisibility(View.INVISIBLE);
		return layout;
	}
	/**
	 * Factory which creates a default loading layout instance. This is used when {@code LoadingLayoutFactory} fails to create a instance
	 * @author Wonjun Kim
	 *
	 */
	private static class FlipLoadingLayoutFactory {
		/**
		 * @param clazzName This class name is being ignored
		 * @return Class token of {@code FlipLoadingLayout}
		 */
		public static Class<? extends LoadingLayout> createLoadingLayoutClazz(
				String clazzName) {
			return FlipLoadingLayout.class;
		}
		/**
		 * @param clazz Class token is being ignored.
		 * @param context
		 * @param mode
		 * @return {@code FlipLoadingLayout} instance
		 */
		public static LoadingLayout createLoadingLayout(
				Class<? extends LoadingLayout> clazz, Context context,
				Mode mode, Orientation orientation, TypedArray attrs) {

			return new FlipLoadingLayout(context, mode, orientation, attrs);
		}

	}

}
