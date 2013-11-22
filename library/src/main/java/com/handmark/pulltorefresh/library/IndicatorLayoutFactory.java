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
/**
 * Factory which creates indicator layouts 
 * <br />Indicator layouts must be listed in pulltorefresh.xml as "PullToRefresh/IndicatorLayouts/layout" nodes
 * @author Wonjun Kim
 */
public class IndicatorLayoutFactory {

	private static final String LOG_TAG = IndicatorLayoutFactory.class.getName();
	/**
	 * Create the class token matched by <b>{@code layoutCode}</b>
	 * @param layoutCode Indicator layout code, which must be defined in pulltorefresh.xml 
	 * @return Class token which is matched by {@code layoutCode}, or the class token of {@code DefaultIndicatorLayout} instance if not
	 */
	public static Class<? extends IndicatorLayout> createIndicatorLayoutClazzByLayoutCode(String layoutCode) {
		String clazzName = PullToRefreshXmlConfiguration.getInstance().getIndicatorLayoutClazzName(layoutCode);
		return createIndicatorLayoutClazz(clazzName);
	}
	/**
	 * Create the class token matched by <b>class name</b>
	 * @param clazzName Class name such as "com.handmark.pulltorefresh.library.internal.DefaultIndicatorLayout"
	 * @return Class token if the class matched by class name exists, or the class token of {@code DefaultIndicatorLayout} instance if not  
	 */
	@SuppressWarnings("unchecked")
	public static Class<? extends IndicatorLayout> createIndicatorLayoutClazz(String clazzName) {
		Class<? extends IndicatorLayout> clazz = null;
		if (clazzName == null) {
			clazz = DefaultIndicatorLayoutFactory.createIndicatorLayoutClazz(clazzName);
			return clazz;
		}
		
 		try {
			clazz = (Class<? extends IndicatorLayout> )Class.forName(clazzName);
			
		} catch (ClassNotFoundException e) {
			Log.e(LOG_TAG, "Class Not Found. It will be used default IndicatorLayout.", e);
			clazz = DefaultIndicatorLayoutFactory.createIndicatorLayoutClazz(clazzName);
			
		}
		
		return clazz;
	}
	/**
	 * Create a {@code IndicatorLayout} instance matched by <b>{@code layoutCode}</b> 
	 * @param layoutCode Indicator layout code, which must be defined in pulltorefresh.xml
	 * @param context 
	 * @param mode 
	 * @return {@code IndicatorLayout} instance if the class matched by {@code layoutCode} exists, or {@code DefaultIndicatorLayout} instance if not  
	 */
	public static IndicatorLayout createIndicatorLayout(String layoutCode, Context context, PullToRefreshBase.Mode mode) {
		Class<? extends IndicatorLayout> clazz = createIndicatorLayoutClazz(layoutCode);
		return createIndicatorLayout(clazz, context, mode);
	}
	/**
	 * Create a {@code IndicatorLayout} instance matched by <b>{@code clazz} token</b> 
	 * @param layoutCode Indicator layout code, which must be defined in pulltorefresh.xml
	 * @param context 
	 * @param mode 
	 * @return {@code IndicatorLayout} instance if the class matched by {@code layoutCode} exists, or {@code DefaultIndicatorLayout} instance if not  
	 */
	public static IndicatorLayout createIndicatorLayout(
			Class<? extends IndicatorLayout> clazz, Context context, Mode mode) {
		IndicatorLayout layout = null;

		try {
			Constructor<? extends IndicatorLayout> constructor = clazz
					.getConstructor(Context.class, Mode.class);
			layout = (IndicatorLayout) constructor.newInstance(context, mode);

		} catch (IllegalArgumentException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. ", e);
		} catch (InvocationTargetException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. ", e);
		} catch (SecurityException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. ", e);
		} catch (NoSuchMethodException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. ", e);
		} catch (InstantiationException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. ", e);
		} catch (IllegalAccessException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. ", e);
		} catch (NullPointerException e) {
			Log.e(LOG_TAG, "indicator layout has failed while creating. ", e);
		}

		if (layout == null) {
			layout = DefaultIndicatorLayoutFactory.createIndicatorLayout(clazz, context, mode);
		}

		layout.setVisibility(View.INVISIBLE);
		return layout;
	}	
	/**
	 * Factory which creates a default indicator layout instance. This is used when {@code IndicatorLayoutFactory} fails to create a instance
	 * @author Wonjun Kim
	 *
	 */
	private static class DefaultIndicatorLayoutFactory {
		/**
		 * @param clazzName This class name is being ignored
		 * @return Class token of {@code DefaultIndicatorLayout}
		 */
		public static Class<? extends IndicatorLayout> createIndicatorLayoutClazz(String clazzName) {
			return DefaultIndicatorLayout.class;
		}
		/**
		 * @param clazz Class token is being ignored.
		 * @param context
		 * @param mode
		 * @return {@code DefaultIndicatorLayout} instance
		 */
		public static IndicatorLayout createIndicatorLayout(Class<? extends IndicatorLayout> clazz, Context context, PullToRefreshBase.Mode mode) {
			return new DefaultIndicatorLayout(context, mode);
		}
	}
}
