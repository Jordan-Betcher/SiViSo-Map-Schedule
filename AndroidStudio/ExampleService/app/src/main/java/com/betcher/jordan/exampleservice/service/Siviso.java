package com.betcher.jordan.exampleservice.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class Siviso extends Service
{
	private static final String TAG = "Siviso";
	
	private LocationListener listener;
	private LocationManager locationManager;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		addLocationListener();
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.d(TAG, "onStartCommand: ");
		super.onStartCommand(intent, flags, startId);
		return START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		if (locationManager != null)
		{
			locationManager.removeUpdates(listener);
		}
	}
	
	@Nullable
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
	
	@SuppressLint("MissingPermission")
	public void addLocationListener()
	{
		listener = new LocationListenerSetSiviso(this);
		locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, listener);
	}
}
