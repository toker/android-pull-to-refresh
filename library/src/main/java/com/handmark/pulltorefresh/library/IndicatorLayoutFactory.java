package com.handmark.pulltorefresh.library;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.handmark.pulltorefresh.configuration.xml.PullToRefreshXmlConfiguration;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.internal.IndicatorLayout;
import com.handmark.pulltorefresh.library.internal.DefaultIndicatorLayout;
import com.handmark.pulltorefresh.library.internal.Utils;

public class IndicatorLayoutFactory {

	private static final String LOG_TAG = IndicatorLayoutFactory.class.getName();
	/**
	 * 
	 * @param layoutCode
	 * @param context
	 * @param mode
	 * @return
	 */
	public static IndicatorLayout createIndicatorFactory(Integer layoutCode, Context context, PullToRefreshBase.Mode mode) {
		String clazzName = PullToRefreshXmlConfiguration.getInstance().getIndicatorLayoutClazzName(layoutCode);
		Class<? extends IndicatorLayout> clazz = createIndicatorLayoutClazz(clazzName);
		return createIndicatorLayout(layoutCode, context, mode);
	}
	/**
	 * 
	 * @param layoutCode
	 * @return
	 */
	public static Class<? extends IndicatorLayout> createIndicatorLayoutClazz(Integer layoutCode) {
		String clazzName = PullToRefreshXmlConfiguration.getInstance().getIndicatorLayoutClazzName(layoutCode);
		return createIndicatorLayoutClazz(clazzName);
	}
	/**
	 * 
	 * @param clazzName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Class<? extends IndicatorLayout> createIndicatorLayoutClazz(String clazzName) {
		Class<? extends IndicatorLayout> clazz = null;
		if ( clazzName == null) {
			clazz = DefaultIndicatorLayoutFactory.createIndicatorLayoutClazz(clazzName);
			return clazz;
		}
		
 		try {
			clazz = (Class<? extends IndicatorLayout> )Class.forName(clazzName);
			
		} catch (ClassNotFoundException e) {
			Utils.error("Class Not Found. It will be used default IndicatorLayout." +e.getMessage());
			clazz = DefaultIndicatorLayoutFactory.createIndicatorLayoutClazz(clazzName);
			
		}
		
		return clazz;
	}
	/**
	 * 
	 * @param layoutCode
	 * @param context
	 * @param mode
	 * @return
	 */
	public static IndicatorLayout createIndicatorLayout(Integer layoutCode, Context context, PullToRefreshBase.Mode mode) {
		Class<? extends IndicatorLayout> clazz = createIndicatorLayoutClazz(layoutCode);
		return createIndicatorLayout(clazz, context, mode);
	}
	
	/**
	 * 
	 * @param context
	 * @param mode
	 * @param attrs
	 * @comment Need to be refactored!
	 * @return
	 */
	public static IndicatorLayout createIndicatorLayout(
			Class<? extends IndicatorLayout> clazz, Context context, Mode mode) {
		IndicatorLayout layout = null;

		try {
			Constructor<? extends IndicatorLayout> constructor = clazz
					.getConstructor(Context.class, Mode.class);
			layout = (IndicatorLayout) constructor.newInstance(context, mode);

		} catch (IllegalArgumentException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. " + e);
		} catch (InvocationTargetException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. " + e);
		} catch (SecurityException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. " + e);
		} catch (NoSuchMethodException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. " + e);
		} catch (InstantiationException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. " + e);
		} catch (IllegalAccessException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. " + e);
		} catch (NullPointerException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. " + e);
		}

		if (layout == null) {
			layout = DefaultIndicatorLayoutFactory.createIndicatorLayout(clazz, context, mode);
		}

		layout.setVisibility(View.INVISIBLE);
		return layout;
	}	
	/**
	 * 
	 * @author NBP
	 *
	 */
	private static class DefaultIndicatorLayoutFactory {
		public static Class<? extends IndicatorLayout> createIndicatorLayoutClazz(String clazzName) {
			return DefaultIndicatorLayout.class;
		}
		/**
		 * 
		 * @param clazz
		 * @param context
		 * @param mode
		 * @return
		 */
		public static IndicatorLayout createIndicatorLayout(Class<? extends IndicatorLayout> clazz, Context context, PullToRefreshBase.Mode mode) {
			return new DefaultIndicatorLayout(context, mode);
		}
		
	}
}
