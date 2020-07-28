package com.betcher.jordan.siviso;

import android.content.Context;
import android.content.SharedPreferences;

import com.betcher.jordan.siviso.activities.activity_showAndNavigateSiviso.Home;
import com.betcher.jordan.siviso.siviso.Siviso;

public class Preferences_Siviso
{
	private static final String PREFERENCE_NAME = "com.betcher.jordan.exampleservice.activities.preferences";
	
	private static final String PREFERENCE_KEY_IS_SERVICE_RUNNING = "isServiceRunning";
	
	private static final String PREFERENCE_KEY_DEFAULT_SIVISO = "DefaultSiviso";
	
	public static boolean isServiceRunning(Home home)
	{
		SharedPreferences prefs = home.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		boolean isServiceRunning = prefs.getBoolean(PREFERENCE_KEY_IS_SERVICE_RUNNING, false);
		return isServiceRunning;
	}
	
	public static void saveIsServiceRunning(Context context, boolean newIsServiceRunning)
	{
		SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		prefs.edit().putBoolean(PREFERENCE_KEY_IS_SERVICE_RUNNING, newIsServiceRunning).apply();
	}
	
	public static void saveDefaultSiviso(Context context, Siviso siviso)
	{
		SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		prefs.edit().putString(PREFERENCE_KEY_DEFAULT_SIVISO, siviso.name()).apply();
	}
	
	public static Siviso defaultSiviso(Context context)
	{
		SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		String defaultSivisoName = prefs.getString(PREFERENCE_KEY_DEFAULT_SIVISO, Siviso.None.name());
		Siviso defaultSiviso = Siviso.siviso(defaultSivisoName);
		return  defaultSiviso;
	}
}
