package com.jordan.betcher.sivisoLite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.model.LatLng;
import com.jordan.betcher.sivisoLite.service.SivisoService;
import com.jordan.betcher.sivisoLite.siviso.Siviso;

public class PreferencesForSivisoLite
{
	
	public static final String PREFERENCE_NAME = "SivisoLitePreferences";
	
	public static final String KEY_IS_SERVICE_RUNNING = "keyIsServiceRunning";
	
	public static final String KEY_DEFAULT_SIVISO = "keyDefaultSiviso";
	public static final Siviso DEFAULT_DEFAULT_SIVISO = Siviso.NONE;
	
	public static final String KEY_HOME_EXISTS = "keyHomeExists";
	public static final String KEY_HOME_LATITUDE = "keyHomeLatitude";
	public static final String KEY_HOME_LONGITUDE = "keyHomeLongitude";
	public static final String KEY_HOME_SIVISO = "keyHomeSiviso";
	public static final Siviso DEFAULT_HOME_SIVISO = Siviso.VIBRATE;
	
	
	public static Siviso getDefaultSiviso(Context context)
	{
		SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		
		int defaultValue = Siviso.getPositionOf(DEFAULT_DEFAULT_SIVISO);
		int sivisoAsPosition = prefs
		.getInt(KEY_DEFAULT_SIVISO, defaultValue);
		
		Siviso siviso = Siviso.getFromPosition(sivisoAsPosition);
		
		return siviso;
	}
	
	public static Siviso getHomeSiviso(Context context)
	{
		SharedPreferences prefs = context
		.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		int defaultValue = Siviso.getPositionOf(DEFAULT_HOME_SIVISO);
		int sivisoAsPosition = prefs.getInt(KEY_HOME_SIVISO, defaultValue);
		
		Siviso siviso = Siviso.getFromPosition(sivisoAsPosition);
		return siviso;
	}
	
	public static boolean getIsServiceRunning(Context context)
	{
		SharedPreferences prefs = context
		.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		boolean isServiceRunning = prefs
		.getBoolean(KEY_IS_SERVICE_RUNNING, false);
		
		return isServiceRunning;
	}
	
	public static void setIsServiceRunning(
	Context context, boolean isServiceRunning)
	{
		SharedPreferences prefs = context
		.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		
		prefs
		.edit()
		.putBoolean(KEY_IS_SERVICE_RUNNING, isServiceRunning)
		.apply();
	}
	
	public static boolean getHomeExists(Context context)
	{
		SharedPreferences prefs = context
		.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		boolean doesHomeExist = prefs
		.getBoolean(KEY_HOME_EXISTS, false);
		
		return doesHomeExist;
	}
	
	public static LatLng getHomeLatLng(Context context)
	{
		SharedPreferences prefs = context
		.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		double latitude = Double
		.parseDouble(prefs.getString(KEY_HOME_LATITUDE, "0"));
		double longitude = Double
		.parseDouble(prefs.getString(KEY_HOME_LONGITUDE, "0"));
		
		return new LatLng(latitude, longitude);
	}
	
	public static void setHomeExists(
	Context context, boolean homeExists)
	{
		SharedPreferences prefs = context
		.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		
		prefs
		.edit()
		.putBoolean(KEY_HOME_EXISTS, homeExists)
		.apply();
		
		refreshSivisoService(context);
	}
	
	public static void setHomeLatLng(Context context, LatLng latLng)
	{
		SharedPreferences prefs = context
		.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		
		prefs
		.edit()
		.putString(KEY_HOME_LATITUDE, latLng.latitude + "")
		.apply();
		
		prefs
		.edit()
		.putString(KEY_HOME_LONGITUDE, latLng.longitude + "")
		.apply();
		
		refreshSivisoService(context);
	}
	
	public static void setDefaultSiviso(
	Context context, int sivisoAsInt)
	{
		SharedPreferences prefs = context
		.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		
		prefs
		.edit()
		.putInt(KEY_DEFAULT_SIVISO, sivisoAsInt)
		.apply();
		
		refreshSivisoService(context);
	}
	
	public static void setHomeSiviso(Context context, int sivisoAsInt)
	{
		SharedPreferences prefs = context
		.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		
		prefs
		.edit()
		.putInt(KEY_HOME_SIVISO, sivisoAsInt)
		.apply();
		
		refreshSivisoService(context);
	}
	
	private static void refreshSivisoService(Context context)
	{
		boolean isServiceRunning = getIsServiceRunning(context);
		
		if(isServiceRunning)
		{
			Intent startSivisoService = new Intent(context, SivisoService.class);
			
			ContextCompat
			.startForegroundService(context, startSivisoService);
		}
	}
}
