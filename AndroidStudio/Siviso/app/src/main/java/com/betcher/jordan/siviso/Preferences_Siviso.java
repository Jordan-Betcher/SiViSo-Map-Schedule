package com.betcher.jordan.siviso;

import android.content.Context;
import android.content.SharedPreferences;

import com.betcher.jordan.siviso.siviso.SivisoRingmode;

public class Preferences_Siviso
{
	private static final String PREFERENCE_NAME = "com.betcher.jordan.exampleservice.activities.preferences";
	
	private static final String PREFERENCE_KEY_IS_SERVICE_RUNNING = "isServiceRunning";
	
	private static final String PREFERENCE_KEY_DEFAULT_SIVISO = "DefaultSiviso";
	
	public static boolean isServiceRunning(
	Context context)
	{
		SharedPreferences prefs = context
		.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		boolean isServiceRunning = prefs.getBoolean(PREFERENCE_KEY_IS_SERVICE_RUNNING, false);
		return isServiceRunning;
	}
	
	public static void saveIsServiceRunning(Context context, boolean newIsServiceRunning)
	{
		SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		prefs.edit().putBoolean(PREFERENCE_KEY_IS_SERVICE_RUNNING, newIsServiceRunning).apply();
	}
	
	public static void saveDefaultSiviso(Context context, SivisoRingmode sivisoRingmode)
	{
		SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		prefs.edit().putString(PREFERENCE_KEY_DEFAULT_SIVISO, sivisoRingmode
		.name()).apply();
	}
	
	public static SivisoRingmode defaultSiviso(Context context)
	{
		SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		String defaultSivisoName = prefs.getString(PREFERENCE_KEY_DEFAULT_SIVISO, SivisoRingmode.None.name());
		SivisoRingmode defaultSivisoRingmode = SivisoRingmode
		.siviso(defaultSivisoName);
		return defaultSivisoRingmode;
	}
}
